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
	//will hold a reference to the App object, which inherits from PApplet and therefore handles all the  Processing-specific stuff
		//PApplet app;
		private App app;
		
		public PImage getImg() {
			return img;
		}
		public void setImg(PImage img) {
			this.img = img;
		}
		
		public boolean isAlive() {
			return isAlive;
		}
		public void setAlive(boolean isAlive) {
			this.isAlive = isAlive;
		}

		private boolean isAlive = true; //flag to  hold living status of this alien

		//make sure the image file is in the src  folder
		//private final static String SPACESHIP_IMAGE_PATH = "spaceship.png"; // image file
		private PImage img; //will hold the image to  use for this spaceship
		
		private int x, y; //position
		private int speedX = 5; //speed in x  direction... start out going to the right
		private int speedY = 5; //speed in Y direction... the y position is changed by this amount every time the ship goes up or down
	
		
		public App getApp() {
			return  this.app;
		}
		public int setInitialX() {
			return 0;
		}
		
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getSpeedX() {
			return speedX;
		}
		public void setSpeedX(int speedX) {
			this.speedX = speedX;
		}
		public int getSpeedY() {
			return speedY;
		}
		public void setSpeedY(int speedY) {
			this.speedY = speedY;
		}
		public int setInitialY() {
			return 0;
		}
		
		public String  getImage()
		{
			return null;
		}
		public GameObject(PApplet app) {
			//set up initial properties for this  alien
			this.app = (App)app; //keep a reference to  the PApplet class to handle all  Processing-specific functions and  variables

			//position it on screen
			this.x = setInitialX();
			this.y = setInitialY();
			
			//load the image and store in PImage  variable
			this.img = this.app.loadImage(getImage());
		}
		
		public GameObject(PApplet app , int x, int y ) {
			//set up initial properties for this  alien
			this.app = (App)app; //keep a reference to  the PApplet class to handle all  Processing-specific functions and  variables

			//position it on screen
			this.x = x;
			this.y = y;
			
			//load the image and store in PImage  variable
			this.img = this.app.loadImage(getImage());
		}

		/**
		 * Get the width of this spaceship, based on  the width of its image.
		 */
		public int getWidth() {
			return this.img.width;
		}
		
		/**
		 * Get the height of this spaceship, based  on the width of its image.
		 */
		public int getHeight() {
			return this.img.height;
		}
		
		/**
		 * Slide the spaceship further to the right  or left, depending upon the current  direction it's moving.
		 */
		public void move() {
			int newX = this.x + this.speedX; // calculate move by whichever amount is  specified in speedX variable.
			
			//check bounds
			boolean outOfBoundsToTheLeft = newX < 0  + App.APP_MARGIN; //too far to the left?
			boolean outOfBoundsToTheRight = newX >  this.app.width - this.getWidth() - App. APP_MARGIN; //too far to the right?

			//if out of bounds...
			if (outOfBoundsToTheRight ||  outOfBoundsToTheLeft) {
				 //reverse direction
				this.toggleDirection(); //inverts  the sign of speed
				newX = this.x + this.speedX; // calculate move in new direction
			}
			
			//make update to position
			this.x = newX;
			
		}
		
		/**
		 * Sets by how much this spaceship moves  each frame.
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
			//this.app.image(this.img, this.x, this.y);
			if (isAlive()) {
				//draw the image using PApplet's  image method
				//this.app.image(this.img, this.x, this.y);
				this.getApp().image(this.getImg(), getX(), getY());
			}
		}

		/**
		 * Move this spaceship in the opposite  direction from which it is currently moving
		 */
		public void toggleDirection() {
			this.speedX = -this.speedX; //invert the  sign of the speed it's currently moving
		}
		
		/**
		 * Set speed such that the spaceship moves  to the right.
		 */
		public void goRight() {
			//set speed to a positive value
			this.speedX = Math.abs(this.speedX);
		}

		/**
		 * Set speed such that the spaceship moves to the right.
		 */
		public void goLeft() {
			//set speed to a negative value
			this.speedX = -Math.abs(this.speedX);
		}

		/**
		 * Set speed such that the spaceship moves to up.
		 */
		public void goUp() {
			//set speed to a negative value
			this.y = this.y - Math.abs(this.speedY);
			
			//check bounds
			boolean isTooFarUp = this.y < 0 + this.getHeight();

			//if the spaceship is too far up...
			if (isTooFarUp) {
				//reposition this spaceship at the bottom of the screen
				this.y = app.height - this.getHeight();
			}
		}
		
		/**
		 * Set speed such that the spaceship moves to up.
		 */
		public void goDown() {
			//set speed to a negative value
			this.y = this.y + Math.abs(this.speedY);

			//check bounds
			boolean isTooFarDown = this.y > this.app.height - this.getHeight();

			//if the spaceship is too far up...
			if (isTooFarDown) {
				//reposition this spaceship at the bottom of the screen
				this.y = 0;
			}
		}
		
		

}
