package com.github.anthonywww.mcbot;

import java.io.IOException;
import java.util.concurrent.Executors;

import com.fazecast.jSerialComm.SerialPort;

import arduino.Arduino;

class ArduinoStatus {
	
	private static final char DELIMITER = '\0';
	private static final int BAUD_RATE = 19200;
	private static final String[] DEVICES = {
		"/dev/ttyACM0", // Linux
		"/dev/ttyACM1",
		"/dev/ttyUSB0",
		"/dev/tty.usbserial-A9007UX1", // Mac OS X
		"COM3", // Windows
		"COM4",
		"COM5"
	};
	private Arduino arduino;
	private boolean connected;
	private boolean blinking;

	ArduinoStatus() {
		blinking = false;
		arduino = new Arduino();
		
		for (int i=0; i<DEVICES.length; i++) {
			arduino.setPortDescription(DEVICES[i]);
			connected = arduino.openConnection();
			if (connected) {
				break;
			}
		}
		
		arduino.setBaudRate(BAUD_RATE);
		arduino.getSerialPort().setFlowControl(SerialPort.FLOW_CONTROL_DISABLED);
		arduino.getSerialPort().setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
	}

	public synchronized void shutdown() {
		if (connected) {
			blinking = false;
			setColor(10, 10, 0);
			arduino.closeConnection();
		}
	}
	
	
	
	
	
	
	public void setColor(int red, int green, int blue) {
		blinking = false;
		color(red, green, blue);
	}
	
	private void color(int red, int green, int blue) {
		if (connected) {
			synchronized (arduino) {
				arduino.serialWrite((char) red);
				arduino.serialWrite((char) green);
				arduino.serialWrite((char) blue);
				arduino.serialWrite('\0');
				try {
					arduino.getSerialPort().getOutputStream().flush();
				} catch (IOException e) {}
			}
		}
	}
	
	public synchronized void setBlinkingColor(int red, int green, int blue, int interval) {
		Executors.newSingleThreadExecutor().execute(() -> {
			blinking = true;
			try {
				while (connected && blinking) {
					color(red, green, blue);
					Thread.sleep(interval);
					color(0, 0, 0);
					Thread.sleep(interval);
				}
			} catch (InterruptedException e) {}
		});
	}

}
