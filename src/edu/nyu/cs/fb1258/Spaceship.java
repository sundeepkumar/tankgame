package edu.nyu.cs.fb1258;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * This class represents the spaceship.
 * @author Sanchit Kumar
 * @version 0.1
 *
 */
public class Spaceship  extends GameObject {

	//will hold a reference to the App object, which inherits from PApplet and therefore handles all the  Processing-specific stuff
	
	
	//make sure the image file is in the src  folder
	private final static String SPACESHIP_IMAGE_PATH = "spaceship.png"; // image file
	

	public int  setInitialX() {
		return (int) (this.getApp().width / 2); //x  position centered on screen
	}
	
	public int  setInitialY() {
		return  getApp().height - App.APP_MARGIN; // y position close to bottom of screen
	}
	
	
	public String  getImage()
	{
		return SPACESHIP_IMAGE_PATH;
	}
	public Spaceship(PApplet app) {
		super(app);	
	}
	
	/**
	 * Shoots a bullet out of the spaceship.
	 */
	public void shoot() {
		//create a new bullet object positioned  at the center of this spaceship
		int x = (int) (getX() + (this.getWidth()  / 2)); //the center x position of this  spaceship
		int y = (int) (getY() - (this. getHeight() / 2)); //the center y  position of this spaceship

		//create bullet object
		Bullet bullet = new Bullet(x, y, this.getApp());
		
	}
	

}