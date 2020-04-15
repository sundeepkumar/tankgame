package edu.nyu.cs.sk7374;
import processing.core.PApplet;
import processing.core.PImage;


/**
 * 
 * This class represents a game object.
 * @author Sanchit Kumar
 * @version 0.1
 *
 */

public class GameObject {
		//will hold a reference to the TankWorld object, which inherits from PApplet and therefore handles all the  Processing-specific stuff
		//PApplet app;
		
		private TankWorld app;
		
		private int movementType = 1; // 0 = left right or up down 1 =  Whole battle field 
		private int direction = 0;
		private boolean isAlive = true; //flag to  hold living status of this alien
		
		private PImage img; //will hold the image to  use for this GameObject
				
		private int x, y; //position
		private int speedX = 5; //speed in x  direction
		private int speedY = 5; //speed in Y direction
	
		/**
		 * Get the instance of the App 
		 */
		public TankWorld getApp() {
			return  this.app;
		}
		
		/**
		 * Constructor for the GameObject.
		 * Setter for the App property.
		 */
		public GameObject(PApplet app) {
			//set up initial properties for this Game Object
			this.app = (TankWorld)app; //keep a reference to  the PApplet class to handle all  Processing-specific functions and  variables

			//position it on screen
			this.x = setInitialX();
			this.y = setInitialY();
			
			//load the image and store in PImage  variable
			this.img = this.app.loadImage(getImage());
		}
		/**
		 * Constructor for the GameObject.
		 * Setter for the App and x, y properties.
		 */
		public GameObject(PApplet app , int x, int y ) {
			//set up initial properties for this  GameObject
			this.app = (TankWorld)app; //keep a reference to  the PApplet class to handle all  Processing-specific functions and  variables

			//position it on screen
			this.x = x;
			this.y = y;
			
			//load the image and store in PImage  variable
			this.img = this.app.loadImage(getImage());
		}
		
		/**
		 * getter for the movementType property.
		 */
		public int getMovementType()
		{
			return this.movementType;
		}
		/**
		 * Setter for the movementType property.
		 */
		public void setMovementType(int movementType)
		{
			this.movementType = movementType;
		}
		
		/**
		 * getter for the img property
		 */
		public PImage getImg() {
			return img;
		}
		
		/**
		 * setter for the img property
		 */
		public void setImg(PImage img) {
			this.img = img;
		}
		
		/**
		 * getter for the isAlive property
		 */
		public boolean isAlive() {
			return isAlive;
		}
		/**
		 * setter for the isAlive property
		 */
		public void setAlive(boolean isAlive) {
			this.isAlive = isAlive;
		}
		
		/**
		 * getter for the direction property
		 */
		public int getDirection()
		{
			return this.direction;
		}
		
		/**
		 * setter for the direction property
		 */
		public void setDirection(int direction)
		{
			this.direction = direction;
		}

		/**
		 * gets the initial x for the derived Object - Overridden by the Derived class
		 */
		public int setInitialX() {
			return 0;
		}
		/**
		 * gets the initial Y for the derived Object - Overridden by the Derived class
		 */
		public int setInitialY() {
			return 0;
		}
		
		/**
		 * getter for the x property
		 */
		public int getX() {
			return x;
		}
		/**
		 * setter for the x property
		 */
		public void setX(int x) {
			this.x = x;
		}
		
		/**
		 * getter for the y property
		 */
		public int getY() {
			return y;
		}
		/**
		 * getter for the y property
		 */
		public void setY(int y) {
			this.y = y;
		}
		
		/**
		 * getter for the speedX property
		 */
		public int getSpeedX() {
			return speedX;
		}
		/**
		 * getter for the speedX property
		 */
		public void setSpeedX(int speedX) {
			this.speedX = speedX;
		}
		
		/**
		 * getter for the speedY property
		 */
		public int getSpeedY() {
			return speedY;
		}
		/**
		 * getter for the y property
		 */
		public void setSpeedY(int speedY) {
			this.speedY = speedY;
		}
		
		/**
		 * get the Image - Overridden by the Derived class 
		 */
		public String  getImage()
		{
			return null;
		}
		

		/**
		 * Get the width of this GameObject, based on  the width of its image.
		 */
		public int getWidth() {
			return this.img.width;
		}
		
		/**
		 * Get the height of this GameObject, based  on the width of its image.
		 */
		public int getHeight() {
			return this.img.height;
		}
		
		/**
		 * Slide the GameObject  further to the right  or left, depending upon the current  direction it's moving.
		 */
		public void move() {
			if((getMovementType() ==1 ) || (getDirection()== 0 )|| (getDirection() == 2)) {
				int newX = getX() + getSpeedX(); // calculate move by whichever amount is  specified in speedX variable.
				
				//check bounds
				boolean outOfBoundsToTheLeft = newX < 0  + TankWorld.APP_MARGIN; //too far to the left?
				boolean outOfBoundsToTheRight = newX >  this.getApp().width - this.getWidth() - TankWorld. APP_MARGIN; //too far to the right?
		
				//if out of bounds...
				if (outOfBoundsToTheRight ||  outOfBoundsToTheLeft) {
					 //reverse direction
					this.toggleDirection(); //inverts  the sign of speed
					newX = this.getX() + this.getSpeedX(); // calculate move in new direction
				}
				
				//make update to position
				setX(newX);
			}
			if((getMovementType() ==1 ) || (getDirection()== 1 )|| (getDirection() == 3)) {
			
				int newY = this.getY() + this.getSpeedY(); // calculate move by whichever amount is  specified in speedY variable.
				//check bounds
				boolean outOfBoundsToTheTop = newY > getApp().getHeight()  - TankWorld.APP_MARGIN; //too far to the Top?
				boolean outOfBoundsToTheBottom = newY <  0 + TankWorld. APP_MARGIN; //too far to the bottom?
		
				//if out of bounds...
				if (outOfBoundsToTheTop ||  outOfBoundsToTheBottom ) {
					 //reverse direction
					this.toggleDirectionY(); //inverts  the sign of speed
					newY = this.getY() + this.getSpeedY();// calculate move in new direction
				}
				
				//make update to position
				setY(newY);	
			}
	    }
		
		/**
		 * Sets by how much this GameObject moves  each frame.
		 * Setter for speedX property.
		 */
		public void setSpeed(int speedX) {
			this.speedX = speedX;
		}
		
		/**
		 * Draws this alien to the PApplet screen.   Each alien draws itself to the main app  screen in this way.
		 */
		public void draw() {
			//draw the image using PApplet's image  method
			if (isAlive()) {
				//draw the image using PApplet's  image method
				this.getApp().image(this.getImg(), getX(), getY());
			}
		}

		/**
		 * Move this GameObject in the opposite  direction from which it is currently moving
		 */
		public void toggleDirection() {
			this.speedX = -this.speedX; //invert the  sign of the speed it's currently moving
		}
		
		/**
		 * Move this GameObject in the opposite  direction from which it is currently moving
		 */
		public void toggleDirectionY() {
			this.speedY = -this.speedY; //invert the  sign of the speed it's currently moving
		}
		
		/**
		 * Set speed such that the GameObject moves  to the right.
		 */
		public void goRight() {
			//set speed to a positive value
			 this.speedX = Math.abs(this.speedX);
			
			
		}

		/**
		 * Set speed such that the GameObject moves to the right.
		 */
		public void goLeft() {
			//set speed to a negative value
			this.speedX = -Math.abs(this.speedX);
		}

		/**
		 * Set speed such that the GameObject moves to up.
		 */
		public void goUp() {
			//set speed to a negative value
			this.y = this.y - Math.abs(this.speedY);
			
			//check bounds
			boolean isTooFarUp = this.y < 0 + this.getHeight();

			//if the GameObject is too far up...
			if (isTooFarUp) {
				//reposition this GameObject at the bottom of the screen
				this.y = app.height - this.getHeight();
			}
		}
		
		/**
		 * Set speed such that the GameObject moves to up.
		 */
		public void goDown() {
			//set speed to a negative value
			this.y = this.y + Math.abs(this.speedY);

			//check bounds
			boolean isTooFarDown = this.y > this.app.height - this.getHeight();

			//if the GameObject is too far up...
			if (isTooFarDown) {
				//reposition this GameObject at the bottom of the screen
				this.y = 0;
			}
		}
		

}
