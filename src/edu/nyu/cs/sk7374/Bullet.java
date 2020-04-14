package edu.nyu.cs.sk7374;

import processing.core.PApplet;
import processing.core.PImage;

public class Bullet {

	//will hold a reference to the App object, which inherits from PApplet and therefore handles all the  Processing-specific stuff
	private App app;
	
	//make sure the image file is in the src  folder
	private final static String BULLET_IMAGE_PATH = "bullet.png"; //image  file
	private PImage img; //will hold the image to  use for this bullet
	
	public int x, y; //position
	private int speedY = -10; //speed in y  direction... going up-screen
	
	private int speedX = -10; //speed in x  direction... going side ways 
	
	private int direction;
	
	
	public boolean enemy;
	
	public Bullet(int x, int y, PApplet app ) {
		//set up initial properties for this  bullet
		this.app = (App) app; //keep a reference  to the PApplet class to handle all  Processing-specific functions and  variables

		//position it on screen
		this.x = x;
		this.y = y;
		this.direction = 1;
		this.enemy = false;
		
		
		//load the image and store in PImage  variable
		this.img = app.loadImage(Bullet.BULLET_IMAGE_PATH);
		
		//add this Bullet object to the  PApplet's list of bullets
		this.app.getBullets().add(this);

	}
	
	public Bullet(int x, int y, PApplet app , int direction , boolean enemy ) {
		//set up initial properties for this  bullet
		this.app = (App) app; //keep a reference  to the PApplet class to handle all  Processing-specific functions and  variables

		//position it on screen
		this.x = x;
		this.y = y;
		
		this.direction = direction;
		if(this.direction == 2) {
			this.speedY = -1* this.speedY;
		}
		else if(this.direction == 3) {
			this.speedX = -1* this.speedX;
		}
		
		this.enemy = enemy;
		
		
		//load the image and store in PImage  variable
		this.img = app.loadImage(Bullet.BULLET_IMAGE_PATH);
		
		//add this Bullet object to the  PApplet's list of bullets
		this.app.getBullets().add(this);

	}

	/**
	 * Get the width of this bullet, based on  the width of its image.
	 */
	public int getWidth() {
		return this.img.width; //return the PImage object's width property
	}
	
	/**
	 * Get the height of this bullet, based on  the width of its image.
	 */
	public int getHeight() {
		return this.img.height; //return the PImage object's height property
	}
	
	/**
	 * Slide the bullet further up the screen.
	 */
	public void move() {
		    if(direction== 0 || direction == 2) {
				int newY = this.y + this.speedY; // calculate move by whichever amount is  specified in speedY variable.
				
				//check bounds
				boolean outOfBoundsToTheTop = newY < 0 +  this.getHeight(); //too far up the  screen?
		
				//if out of bounds...
				if (outOfBoundsToTheTop) {
					 //remove it from the array of  bullets
					this.app.getBullets().remove(this); //aren't  ArrayLists great?
				}
				
				//make update to position
				this.y = newY;
		    }
		    else {
		    	int newX = this.x + this.speedX; // calculate move by whichever amount is  specified in speedY variable.
			
			//check bounds
		    	boolean outOfBoundsToTheLeft = newX < 0 +  this.getWidth(); //too far up the  screen?
	
			//if out of bounds...
		    	if (outOfBoundsToTheLeft) {
				 //remove it from the array of  bullets
		    		this.app.getBullets().remove(this); //aren't  ArrayLists great?
		    	}
			
		    	//make update to position
		    	this.x = newX;
		    }
		
	}
	
	/**
	 * Sets by how much this spaceship moves  each frame.
	 * Setter for speedX property.
	 */
	public void setSpeed(int speedY) {
		this.speedY = speedY;
	}

	/**
	 * Kill this bullet.
	 * Simply removes this bullet from the  PApplet's list of bullets;
	 */
	public void kill() {
		this.app.getBullets().remove(this); //remove this  bullet from the list of aliens
	}
	

	/**
	 * Draws this alien to the PApplet screen.   Each alien draws itself to the main app  screen in this way.
	 */
	public void draw() {
		//draw the image using PApplet's image  method
		this.app.image(this.img, this.x, this.y);
	}
	
	/**
	 * Static method to check collision between any bullet and any alien
	 */
	public static boolean isCollision(Bullet  bullet, Alien alien) {
		boolean collision = false; //flag to  indicate whether a collision has been  detected
		
		//check whether bullet is within the box  representing the alien
		if (bullet.x >= alien.getX() && bullet.x +  bullet.getWidth() <= alien.getX() + alien. getWidth()) {
			if (bullet.y >= alien.getY() && bullet.y  + bullet.getHeight() <= alien.getY() +  alien.getHeight()) {
				collision = true;
			}
		}
		
		return collision;
	}
	
	public static boolean isCollision(Bullet  bullet, Spaceship ship) {
		boolean collision = false; //flag to  indicate whether a collision has been  detected
		
		//check whether bullet is within the box  representing the alien
		if (bullet.x >= ship.getX() && bullet.x +  bullet.getWidth() <= ship.getX() + ship. getWidth()) {
			if (bullet.y >= ship.getY() && bullet.y  + bullet.getHeight() <= ship.getY() +  ship.getHeight()) {
				collision = true;
			}
		}
		
		return collision;
	}
	

}