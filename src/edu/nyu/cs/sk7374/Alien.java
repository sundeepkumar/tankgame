package edu.nyu.cs.sk7374;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The alien class
 * @author Sanchit Kumar
 * @version 0.1
 */
public class Alien extends GameObject {

	
	//make sure the image file is in the src  folder
	private final static String ALIEN_IMAGE_PATH  = "EnemyTank.png"; //alien image file
	//private PImage img; //will hold the image to  use for this alien
	
	private final static int MAX_SPEED = 4; // maximum speed the alien will move in any  direction

	
	public String  getImage()
	{
		return ALIEN_IMAGE_PATH ;
	}

	public Alien(int x, int y, PApplet app) {
		super(app,x,y);
	
		setSpeedX(this.getRandomSpeed());
		setSpeedY(this.getRandomSpeed());		
		
	}

	
	/**
	 * Get a random speed.
	 * @return random int between min and max  speed settings
	 */
	public int getRandomSpeed() {
		//generate a random int between the  alien's -MAX_SPEED and +MAX_SPEED
		int speed = (int) ((Math.random() * Alien.MAX_SPEED * 2) - Alien.MAX_SPEED);
		return speed;
	}
	
	public boolean canShoot() {
		//generate a random int between the  alien's -MAX_SPEED and +MAX_SPEED
		int num = (int) (Math.random() * 500) ;
		if(num < 2 ) 
			return true;
		return false;
	}


	/**
	 * Moves this alien to a new position.
	 * Setter for x and y properties.
	 */
	public void setPosition(int x, int y) {
		setX(x);
		setY(y);
	}
	
	/**
	 * Sets by how much this alien moves each  frame.
	 * Setter for speedX and speedY properties.
	 */
	public void setSpeed(int speedX, int speedY)  {
		setSpeedX(speedX);
		setSpeedY(speedY);
	}
	
	/**
	 * Kill this alien.
	 * Simply sets this alien's isAlive  attribute to false and removes it from the  PApplet's list of aliens;
	 */
	public void kill() {
		setAlive(false);
		this.getApp().getAliens().remove(this); //remove  this alien from the list of aliens
	}
	

	/**
	 * Move this alien to a randomly-generated new position
	 */
	public void move() {
		int speedX = this.getRandomSpeed(); // new speed in x direction
		int speedY = this.getRandomSpeed(); // new speed in y direction

		this.setSpeed(speedX, speedY); //update  internal speed info
		
		
		int newX = getX()+ getSpeedX(); // calculate new x position
		int newY = getY()+ getSpeedY(); // calculate new y position

		//keep this alien within the borders!
		if (newX < 0) newX = this.getApp().width; //if  it went too far to the left, wrap it to  the right
		else if (newX > this.getApp().width) newX = 0; //if it went too far to the right, wrap  it to the left
		if (newY < 0) newY = getY(); //if it  went too far up, don't let it go any  further
		else if (newY + this.getHeight() > this.getApp().width) newY = getY(); //if it went too  far down, don't let it go any further
		//update to the new position
		this.setPosition(newX, newY);
		if(canShoot())
			shoot();
		
	}
	
	public void shoot() {
		//create a new bullet object positioned  at the center of this spaceship
		int x = (int) (getX() + (this.getWidth()  / 2)); //the center x position of this  spaceship
		int y = (int) (getY() - (this. getHeight() / 2)); //the center y  position of this spaceship

		//create bullet object
		Bullet bullet = new Bullet(x, y-100, this.getApp(),2,true);
		
	}
	
}
