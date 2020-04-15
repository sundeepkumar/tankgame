package edu.nyu.cs.sk7374;

import processing.core.PApplet;
import processing.core.PImage;

public class Bullet extends GameObject{

	
	private final static String BULLET_IMAGE_PATH = "bullet.png"; //image  file
	private final static String BULLET_IMAGE_PATH_LEFT = "bulletL.png"; //image  file
	
	public boolean enemy; // Enemy Bullet 
	

	/**
	 * Constructor for the Bullet Class 
	 * Sets the x, y  and app properties
	 */
	public Bullet(int x, int y, PApplet app ) {
		super(app);
		
		this.setX(x);
		this.setY(y);
		
		setSpeedX(-10);
		setSpeedY(-10);
		
		setDirection(1);
		this.enemy = false;
		
		//add this Bullet object to the  PApplet's list of bullets
		setImg(getApp().loadImage(getImage()));
		getApp().getBullets().add(this);

	}
	
	
	/**
	 * Constructor for the Bullet Class 
	 * Sets the x, y , App , direction and enemy properties
	 */
	public Bullet(int x, int y, PApplet app , int direction , boolean enemy ) {
		super(app);
		// Set the speed of the bullet
		setSpeedX(-10); 
		setSpeedY(-10);
		//position it on screen
		this.setX(x);
		this.setY(y);
		
		setDirection(direction); // Set the Direction of the Bullet 
		if(this.getDirection() == 2) { // 0 = Up , 1 = Left , 2 = Down 3 = Right 
			this.setSpeedY(  -1* this.getSpeedY());
		}
		else if(this.getDirection() == 3) {
			this.setSpeedX(  -1* this.getSpeedX());
		}
		
		this.enemy = enemy; // Set the Enemy property 
				
		setImg(getApp().loadImage(getImage())); // Set the Image based on the Direction 
		getApp().getBullets().add(this); // Add itself to the Bullets Array

	}
	
	
	/**
	 * Called by the Base class to get the current image to Draw - Overridden by the Derived class.
	 */
	public String  getImage()
	{
		if(this.getDirection() == 0   ||  this.getDirection() == 2)
			return Bullet.BULLET_IMAGE_PATH;
		else 
			return Bullet.BULLET_IMAGE_PATH_LEFT;
	}
	
	
	
	
	/**
	 * Slide the bullet further up the screen.
	 */
	public void move() {
		    if(this.getDirection()== 0 || this.getDirection() == 2) {
		    	int newY = getY() + this.getSpeedY(); // calculate move by whichever amount is  specified in speedY variable.
				//check bounds
				boolean outOfBoundsToTheTop = newY < 0 +  this.getHeight(); //too far up the  screen?
		
				//if out of bounds...
				if (outOfBoundsToTheTop) {
					 //remove it from the array of  bullets
					getApp().getBullets().remove(this);
				}
				
				//make update to position
				this.setY(newY);
		    }
		    else {
		    	 int newX = getX() + this.getSpeedX(); // calculate move by whichever amount is  specified in speedY variable.
			//check bounds
		    	boolean outOfBoundsToTheLeft = newX < 0 +  this.getWidth(); //too far up the  screen?
	
			//if out of bounds...
		    	if (outOfBoundsToTheLeft) {
				 //remove it from the array of  bullets
		    		getApp().getBullets().remove(this); 
		    	}
			
		    	//make update to position
		    	this.setX(newX);
		    }
		
	}
	


	/**
	 * Kill this bullet.
	 * Removes this bullet from PApplet's list of bullets;
	 */
	public void kill() {
		getApp().getBullets().remove(this); //remove this  bullet from the list 
	}
	
	
	/**
	 * Static method to check collision between any bullet and an Enemy Tank
	 */
	public static boolean isCollision(Bullet  bullet, EnemyTank enemyTank) {
		boolean collision = false; //flag to  indicate whether a collision has been  detected
		if (bullet.getX() >= enemyTank.getX() && bullet.getX() +  bullet.getWidth() <= enemyTank.getX() + enemyTank. getWidth()) {
			if (bullet.getY() >= enemyTank.getY() && bullet.getY()  + bullet.getHeight() <= enemyTank.getY() +  enemyTank.getHeight()) {
				collision = true;
			}
		}
		
		return collision;
	}
	
	public static boolean isCollision(Bullet  bullet, Tank tank) {
		boolean collision = false; //flag to  indicate whether a collision has been  detected
		
		//check whether bullet is within the box  representing the Tank
		if (bullet.getX() >= tank.getX() && bullet.getX() +  bullet.getWidth() <= tank.getX() + tank. getWidth()) {
			if (bullet.getY() >= tank.getY() && bullet.getY()  + bullet.getHeight() <= tank.getY() +  tank.getHeight()) {
				collision = true;
			}
		}
		
		return collision;
	}
	

}