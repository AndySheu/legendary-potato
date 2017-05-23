package main;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {

	public Frame() {
		setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));

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
		drawTime(g);
	}

	private void drawPest(Graphics g) {
		g.setColor(new Color((int) Main.random(256) - 1, (int) Main.random(256) - 1, (int) Main.random(256) - 1));
		int x = (int) (Main.random((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth())));
		int y = (int) (Main.random((int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight())));
		g.drawOval(x, y, Constants.PEST_SIZE, Constants.PEST_SIZE);
	}
	
	private void drawTime(Graphics g){
		g.setColor(new Color(255,255,255));
		g.drawString("GEN:"+Main.generation+"\tALIVE:"+Main.pestList.size(), 100, 100);
		if(Main.generation%Constants.RESEARCH_TIME==0){
			g.drawString("Pesticide Effectiveness = "+Main.effectiveness+"%", 100, 115);
		}
	}

}
