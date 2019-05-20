package com.github.anthonywww.mcbot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Random;
import java.util.concurrent.Executors;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class BotGUI extends JFrame {
	
	private boolean closed;
	private boolean resizable;
	private Dimension size;
	private Dimension minimumSize;
	private JLabel statusLabel;
	
	
	
	public BotGUI() {
		super();
		closed = false;
		resizable = false;
		size = new Dimension(400, 300);
		minimumSize = new Dimension(400, 300);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		initialize();
	}
	
	private void initialize() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setVisible(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(MCBot.NAME + " v" + MCBot.VERSION + " - Status Control Panel");
		this.setResizable(resizable);
		this.setMinimumSize(minimumSize);
		this.setSize(size);
		this.setLocationByPlatform(true);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel labelPanel = new JPanel();
		this.getContentPane().add(labelPanel, BorderLayout.WEST);
		labelPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Bot State: IDLE");
		labelPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Coordinates: (X=0, Y=0, Z=0)");
		labelPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		labelPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		labelPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		labelPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		labelPanel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		labelPanel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		labelPanel.add(lblNewLabel_7);
		
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(null);
		this.getContentPane().add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		
		JProgressBar statusProgressBar = new JProgressBar();
		statusProgressBar.setIndeterminate(true);
		statusProgressBar.setFont(new Font("Dialog", Font.PLAIN, 8));
		statusPanel.add(statusProgressBar);
		
		JLabel debugLabel = new JLabel("d:0 s:2");
		debugLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		statusPanel.add(debugLabel);
		
		statusLabel = new JLabel("Status: ");
		statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		statusLabel.setOpaque(true);
		statusLabel.setBackground(new Color(0, 0, 0));
		statusLabel.setForeground(new Color(255, 255, 255));
		statusLabel.setToolTipText("Current bot status");
		statusPanel.add(statusLabel);
		
		JPanel canvas = new JPanel();
		canvas.setToolTipText("Graphical Representation");
		this.getContentPane().add(canvas, BorderLayout.EAST);
		this.setVisible(true);
		
		
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	public void setStatus(String status) {
		statusLabel.setText("Status: " + status);
	}
	
	public void setStatusLabelBackgroundColor(Color background) {
		statusLabel.setBackground(background);
	}
	
	public void setStatusLabelForegroundColor(Color foreground) {
		statusLabel.setForeground(foreground);
	}
	
	public Color getStatusLabelBackgroundColor() {
		return statusLabel.getBackground();
	}
	
	public Color getStatusLabelForegroundColor() {
		return statusLabel.getBackground();
	}
	
	public boolean isClosed() {
		return closed;
	}
	
	
	
	
	
	public static void main(String[] args) {
		final BotGUI gui = new BotGUI();
		final Random random = new Random();
		final String[] statuses = {"okay", "ready!", "idle", "IDLE", "ok", "attacking", "defending ...", "Connecting ..."};
		
		gui.setStatusLabelBackgroundColor(new Color(0, 0, 0));
		gui.setStatusLabelForegroundColor(new Color(0, 255, 0));
		
		Executors.newSingleThreadExecutor().execute(() -> {
			while(true) {
				
				String nextStatusMessage = statuses[random.nextInt(statuses.length)];
				gui.setStatus(nextStatusMessage);
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {}
			}
		});
	}
	
	
}
