package main;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {

	public Frame() {
		setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 50),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 150));

		getContentPane().setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void paintPests() {
		repaint();
		getContentPane().setBackground(Color.BLACK);
		Graphics g = getGraphics();

		for (Pest p : Main.pestList) {
			drawPest(g);
		}
	}

	private void drawPest(Graphics g) {
		g.setColor(new Color((int) Main.random(256) - 1, (int) Main.random(256) - 1, (int) Main.random(256) - 1));
		int x = (int) (Main.random((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 50)));
		int y = (int) (Main.random((int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 150)));
		g.drawOval(x, y, Constants.PEST_SIZE, Constants.PEST_SIZE);
	}

}
