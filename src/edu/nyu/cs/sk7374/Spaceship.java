package edu.nyu.cs.sk7374;

import processing.core.PApplet;


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
	//private final static String SPACESHIP_IMAGE_PATH = "spaceship.png"; // image file
	private final static String SPACESHIP_IMAGE_PATH = "TankU.png"; // image file
	
	private final static String SPACESHIP_IMAGE_PATH_UP = "TankU.png"; // image file
	
	private final static String SPACESHIP_IMAGE_PATH_LEFT = "TankL.png"; // image file
	
	private final static String SPACESHIP_IMAGE_PATH_DOWN = "TankD.png"; // image file
	
	private final static String SPACESHIP_IMAGE_PATH_RIGHT = "TankR.png"; // image file
	
	private final static String GAME_OVER = "Game Over";
	
	private final static String RESUME_MESSAGE= "Enter to Resume!!";
	
	private final static String NUM_LIVES =  " Lives Left!!";
	
	private  int numlives = 3;
	
	//private int direction = 0;
	
	public void goTurn()
	{
		this.setDirection((this.getDirection()+1)%4); 
		setImg(getApp().loadImage(getImage()));
	}

	public int  setInitialX() {
		return (int) (this.getApp().width / 2); //x  position centered on screen
	}
	
	public int  setInitialY() {
		return  getApp().height - App.APP_MARGIN; // y position close to bottom of screen
	}
	
	
	public String  getImage()
	{
		if(this.getDirection()== 0 )
			return SPACESHIP_IMAGE_PATH_UP;
		else if(this.getDirection() == 1 )
			return SPACESHIP_IMAGE_PATH_LEFT;
		else if(this.getDirection() == 2 )
			return SPACESHIP_IMAGE_PATH_DOWN;
		else 
			return SPACESHIP_IMAGE_PATH_RIGHT;
	}
	public Spaceship(PApplet app) {
		super(app);	
	}
	
	/**
	 * Shoots a bullet out of the spaceship.
	 */
	public void shoot() {
		//create a new bullet object positioned  at the center of this spaceship
		int x=getX();
		int y= getY();
		if(getDirection()== 0 ) {
			x = (int) (getX() + (this.getWidth()  / 2)); //the center x position of this  spaceship
			y = (int) (getY() - (this. getHeight() / 2)); //the center y  position of this spaceship
		}
		else if(getDirection()== 1 ) {
			y = (int) (getY() + (this. getHeight() / 2)); //the center y  position of this spaceship
		}
		else if(getDirection()== 2 ) {
			x = (int) (getX() + (this.getWidth()  / 2)); //the center x position of this  spaceship
			y = (int) (getY() + (this. getHeight() / 2)); //the center y  position of this spaceship
		}
		else if(getDirection()== 3 ) {
			x = (int) (getX() + (this.getWidth() )); //the center x position of this  spaceship
			y = (int) (getY() + (this. getHeight() / 2));
		}
		//create bullet object
		Bullet bullet = new Bullet(x, y, this.getApp(),getDirection(),false);
		
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