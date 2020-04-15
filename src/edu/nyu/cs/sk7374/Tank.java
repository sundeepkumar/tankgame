package edu.nyu.cs.sk7374;

import processing.core.PApplet;


/**
 * 
 * This class represents the Tank.
 * @author Sanchit Kumar
 * @version 0.1 
 *
 */
public class Tank  extends GameObject {
	
	private final static String TANK_IMAGE_PATH_UP = "TankU.png"; // image file
	
	private final static String TANK_IMAGE_PATH_LEFT = "TankL.png"; // image file
	
	private final static String TANK_IMAGE_PATH_DOWN = "TankD.png"; // image file
	
	private final static String TANK_IMAGE_PATH_RIGHT = "TankR.png"; // image file
	
	
	private final static String GAME_OVER_PATH = "gameover.png"; // image file
	
	private final static String RESUME_MESSAGE= "Enter to Resume!!";
	
	private final static String RESTART_MESSAGE= "Enter  R to Restart or Q to Quit!!";
	
	private final static String NUM_LIVES =  " Lives Left!!";
	
	private  int numlives = 3; // Three lives allowed
	
	/**
	 * Turn the Tank 
	 */
	public void goTurn()
	{
		this.setDirection((this.getDirection()+1)%4); 
		setImg(getApp().loadImage(getImage()));
	}

	/**
	 * Set the initial x position 
	 */
	public int  setInitialX() {
		return (int) (this.getApp().width / 2); //x  position centered on screen
	}
	

	/**
	 * Set the initial y position 
	 */
	public int  setInitialY() {
		return  getApp().height - TankWorld.APP_MARGIN; // y position close to bottom of screen
	}
	
	/**
	 * Overridden by the Derived class  to return current image
	 */
	public String  getImage()
	{
		if(this.getDirection()== 0 )
			return TANK_IMAGE_PATH_UP;
		else if(this.getDirection() == 1 )
			return TANK_IMAGE_PATH_LEFT;
		else if(this.getDirection() == 2 )
			return TANK_IMAGE_PATH_DOWN;
		else 
			return TANK_IMAGE_PATH_RIGHT;
	}
	

	/**
	 * Tank Constructor
	 */
	public Tank(PApplet app) {
		super(app);	
	}
	
	/**
	 * Shoots a bullet out of the Tank.
	 */
	public void shoot() {
		//create a new bullet object positioned  at the center of this Tank
		int x=getX();
		int y= getY();
		if(getDirection()== 0 ) {
			x = (int) (getX() + (this.getWidth()  / 2)); //the center x position of this  Tank
			y = (int) (getY() - (this. getHeight() / 2)); //the center y  position of this Tank
		}
		else if(getDirection()== 1 ) {
			y = (int) (getY() + (this. getHeight() / 2)); //the center y  position of this Tank
		}
		else if(getDirection()== 2 ) {
			x = (int) (getX() + (this.getWidth()  / 2)); //the center x position of this  Tank
			y = (int) (getY() + (this. getHeight() / 2)); //the center y  position of this Tank
		}
		else if(getDirection()== 3 ) {
			x = (int) (getX() + (this.getWidth() )); //the center x position of this  Tank
			y = (int) (getY() + (this. getHeight() / 2));
		}
		//create bullet object
		Bullet bullet = new Bullet(x, y, this.getApp(),getDirection(),false);
		
	}
	

	/**
	 * Kill the Tank
	 */
	public void kill() {
		if(numlives == 1) {
			setAlive(false);
			getApp().showSplash(GAME_OVER_PATH);
			this.getApp().textSize(30);
			this.getApp().text(RESTART_MESSAGE,  100, 500);		
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