package com.github.anthonywww.mcbot;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class STTManager {

	private static boolean enabled;
	private static ExecutorService executor = Executors.newSingleThreadExecutor();

	private STTManager() {
	}

	private static void initialize() {
		enabled = true;
		MCBot.getInstance().log(Level.INFO, "Starting STT Manager ...");
		executor.submit(new SphinxManager());
	}

	private static void shutdown() {
		enabled = false;
		MCBot.getInstance().log(Level.INFO, "Shutting down STT Manager ...");
		executor.shutdownNow();
	}

	public static boolean isEnabled() {
		return enabled;
	}

	public static void setEnabled(boolean shouldBeEnabled) {
		if (!enabled && shouldBeEnabled) {
			initialize();
		}
		if (enabled && !shouldBeEnabled) {
			shutdown();
		}
	}

	private static class SphinxManager implements Runnable {
		@Override
		public void run() {
			try {
				Logger cmRootLogger = Logger.getLogger("default.config");
				cmRootLogger.setLevel(Level.OFF);
				String conFile = System.getProperty("java.util.logging.config.file");
				if (conFile == null) {
					System.setProperty("java.util.logging.config.file", "ignoreAllSphinx4LoggingOutput");
				}

				Configuration configuration = new Configuration();

				configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
				configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
				configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

				// Record from microphone
				LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);

				// Start recognition process pruning previously cached data.
				recognizer.startRecognition(true);

				MCBot.getInstance().log(Level.INFO, "SST Initialized.");

				SpeechResult result;
				while ((result = recognizer.getResult()) != null && enabled) {
					MCBot.getInstance().log(Level.WARNING, "Hypothesis: " + result.getHypothesis());
					
					if (result.getHypothesis().equalsIgnoreCase("exit")) {
						MCBot.getInstance().shutdown();
					}
				}

				recognizer.stopRecognition();
			} catch (Exception e) {
				MCBot.getInstance().getTerminal().handleException(e);
			}

			MCBot.getInstance().log(Level.INFO, "SST Terminated.");
		}
	}

}
