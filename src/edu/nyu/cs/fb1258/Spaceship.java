package edu.nyu.cs.fb1258;

import java.awt.event.ActionListener;
import java.util.Timer;

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
	
	private final static String GAME_OVER = "Game Over";
	
	private final static String RESUME_MESSAGE= "Enter to Resume!!";
	
	private final static String NUM_LIVES =  "Num Lives Left!!";
	
	private static int numlives = 3;
	

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
	
	public void kill() {
		if(numlives == 1) {
			setAlive(false);
			this.getApp().textSize(30);
			this.getApp().text(GAME_OVER, getX(), getY());
			this.getApp().stopDraw(true);
		}
		else 
		{
			
			
			numlives--;
			this.getApp().textSize(30);
			this.getApp().text(numlives+ NUM_LIVES, getX(), getY());
			this.getApp().text(RESUME_MESSAGE, 400,200);
			this.getApp().stopDraw(true);
			
		}
		

		
		
	}
	

}