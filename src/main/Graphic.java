package main;

import java.awt.*;
import javax.swing.*;

public class Graphic extends JFrame {
	
	int height = Constants.MANUAL_JFRAME_HEIGHT;
	int width = Constants.MANUAL_JFRAME_WIDTH;
	
	Color color;
	int horiz;
	int vert;
	
	
	public Graphic(){
		this.setPreferredSize(new Dimension(100,100));
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
	
	public void paintPest(int horiz, int vert, Color color){
		this.color=color;
		this.horiz=horiz;
		this.vert=vert;
		System.out.println(horiz+" "+vert);
		repaint();
	}
	

	protected void paintComponent(Graphics g){
		super.paint(g);
		g.setColor(color);
		g.drawLine(horiz, vert, horiz, vert);
		
	}
	
	
}
