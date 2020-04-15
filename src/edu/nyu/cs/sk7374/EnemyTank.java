package edu.nyu.cs.sk7374;

import processing.core.PApplet;

/**
 * The Enemy Tank
 * @author Sanchit Kumar
 * @version 0.1
 */
public class EnemyTank extends GameObject {

	
	//make sure the image file is in the src  folder
	private final static String ENEMY_TANK_IMAGE_PATH  = "EnemyTank.png"; //Enemy Tank image file
	
	private final static int MAX_SPEED = 4; // maximum speed the Enemy Tank will move in any  direction

	private static int moveMode  = 1 ; // 0 = Random in X AXIS , 1  = Total Random 
	

	/**
	 * constructor for the Enemy Tank -- sets the x, y and App properties
	 */
	public EnemyTank(int x, int y, PApplet app) {
		super(app,x,y);
	
		setSpeedX(this.getRandomSpeedX());
		setSpeedY(this.getRandomSpeedY());		
		
	}
	
	/**
	 * getter for moveMode Property
	 */
	public static int getMoveMode()
	{
		return moveMode;
	}
	
	/**
	 * setter for moveMode Property
	 */
	public static void setMoveMode(int mode)
	{
		moveMode  = mode;
	}
	
	/**
	 * Overwritten by the Derived class to return the drawn image
	 */
	public String  getImage()
	{
		return ENEMY_TANK_IMAGE_PATH ;
	}

	

	
	/**
	 * Get a random speed in the X direction 
	 * @return random int between min and max  speed settings
	 */
	public int getRandomSpeedX() {
		int speed = getSpeedX();
		if(EnemyTank.moveMode == 1) {
			int num = (int) (Math.random() * 500) ;
			if(num < 50 ) 
			//generate a random int between the  Enemy Tank's -MAX_SPEED and +MAX_SPEED
				speed = (int) ((Math.random() * EnemyTank.MAX_SPEED * 2) - EnemyTank.MAX_SPEED);
		}
		else 
			speed = (int) ((Math.random() * EnemyTank.MAX_SPEED * 2) - EnemyTank.MAX_SPEED);
		return speed;
	}
	

	/**
	 * Get a random speed in the Y direction
	 * @return random int between min and max  speed settings
	 */
	public int getRandomSpeedY() {
		int speed = getSpeedY();
		if(EnemyTank.moveMode == 1) {
			int num = (int) (Math.random() * 500) ;
			if(num < 50 ) 
			//generate a random int between the  Enemy Tank's -MAX_SPEED and +MAX_SPEED
				speed = (int) ((Math.random() * EnemyTank.MAX_SPEED * 2) - EnemyTank.MAX_SPEED);
		}
		else 
			speed = (int) ((Math.random() * EnemyTank.MAX_SPEED * 2) - EnemyTank.MAX_SPEED);
		return speed;
	}
	

	/**
	 * Check if Enemy Tank Can shoot
	 * @return random boolean if Enemy Tank can shoot
	 */
	public boolean canShoot() {
		int num = (int) (Math.random() * 500) ;
		if(num < 2 ) 
			return true;
		return false;
	}


	/**
	 * Moves this Enemy Tank to a new position.
	 * Setter for x and y properties.
	 */
	public void setPosition(int x, int y) {
		setX(x);
		setY(y);
	}
	
	/**
	 * Sets by how much this Enemy Tank moves each  frame.
	 * Setter for speedX and speedY properties.
	 */
	public void setSpeed(int speedX, int speedY)  {
		setSpeedX(speedX);
		setSpeedY(speedY);
	}
	
	/**
	 * Kill this Enemy Tank.
	 * Simply sets this Enemy Tank's isAlive  attribute to false and removes it from the  PApplet's list of Enemy Tanks;
	 */
	public void kill() {
		setAlive(false);
		this.getApp().getEnemyTanks().remove(this); //remove  this Enemy Tank from the list of Enemy Tanks
	}
	

	/**
	 * Move this Enemy Tank to a randomly-generated new position
	 */
	public void move() {
		int speedX = this.getRandomSpeedX(); // new speed in x direction
		int speedY = this.getRandomSpeedY(); // new speed in y direction

		this.setSpeed(speedX, speedY); //update  internal speed info
		
		
		int newX = getX()+ getSpeedX(); // calculate new x position
		int newY = getY()+ getSpeedY(); // calculate new y position

		//keep this Enemy Tank within the borders!
		if (newX < 0) newX = this.getApp().width; //if  it went too far to the left, wrap it to  the right
		else if (newX > this.getApp().width) newX = 0; //if it went too far to the right, wrap  it to the left
		if (newY < 0) newY = getY(); //if it  went too far up, don't let it go any  further
		else if (newY + this.getHeight() > this.getApp().width) newY = getY(); //if it went too  far down, don't let it go any further
		//update to the new position
		this.setPosition(newX, newY);
		if(canShoot())
			shoot();
		
	}
	
	/**
	 * Shoot at the Player 
	 */
	public void shoot() {
		//create a new bullet object positioned  at the center of this Tank
		int x = (int) (getX() + (this.getWidth()  / 2)); //the center x position of this  Tank
		int y = (int) (getY() - (this. getHeight() / 2)); //the center y  position of this Tank

		//create bullet object
		Bullet bullet = new Bullet(x, y-100, this.getApp(),2,true);
		
	}
	
}
