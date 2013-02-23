package uk.co.stutton.games.question.mathsy1.gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimpleAnimation {
	int x = 70;
	int y = 70;
	
	public static void main(String[] args){
		SimpleAnimation gui = new SimpleAnimation();
		gui.go();
	}
	
	public void go(){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyDrawPanel drawPanel = new MyDrawPanel();
		
		frame.getContentPane().add(drawPanel);
		frame.setSize(550, 500);
		frame.setVisible(true);
		
		for (int i=0; i<130; i++){
			x++;
			y++;
			drawPanel.repaint();
			
			try{
				Thread.sleep(50);
			} catch (Exception ex){
			
			}
		}
	}
	
	class MyDrawPanel extends JPanel{
		public void paintComponent(Graphics g){
//			g.setColor(Color.white);
//			g.fillRect(0, 0, this.getWidth(), this.getHeight());
//			g.setColor(Color.green);
//			g.fillOval(x, y, 40, 40);
			Graphics2D g2d = (Graphics2D) g;
			GradientPaint gradient = new GradientPaint(0, 0, Color.ORANGE,  this.getWidth(), this.getHeight(), Color.RED);
			g2d.setPaint(gradient);
			g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			g2d.setColor(Color.green);
			g2d.fillOval(x, y, 40, 40);
		
		}
	}
}
