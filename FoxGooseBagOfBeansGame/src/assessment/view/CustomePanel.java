package assessment.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CustomePanel extends JPanel{
	
	private Image bg;
	/**
	 * We set the Image field bg that represents the background image equal to the image found in path supplied as the String parameter to the constructor
	 * @param path
	 */
	public CustomePanel(String path){
		bg= new ImageIcon(getClass().getResource(path)).getImage();
	}
	
	/**
	 * Calls the paintComponent from the super class and uses the Graphics object g to draw the Image 
	 */
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		// The getWidth and getHeight methods make sure that the image covers the whole panel on which it is placed
		g.drawImage(bg,0,0,getWidth(),getHeight(),null);
	}
	
}