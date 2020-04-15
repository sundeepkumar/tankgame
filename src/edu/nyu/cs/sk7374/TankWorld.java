package edu.nyu.cs.sk7374;

import java.util.ArrayList;

import edu.nyu.cs.sk7374.EnemyTank;
import edu.nyu.cs.sk7374.Bullet;
import edu.nyu.cs.sk7374.Tank;
import processing.core.*;

/**
 * Basic controller for Space Invaders
 * @author Sanchit Kumar
 * @version 0.1
 */
public class TankWorld extends PApplet {
	
	//window size of this app
	private final int w = 800;
	private final int h = 600;
	
	private static final String WIN_MESSAGE = "You Win!!";
	//make constants for some common colors... it's ok to make constants public
	public final int BLACK = this.color(0,0,0);
	public final int WHITE = this.color(255,255,255);
	
	//make constants for some common spacing... it's ok to make constants public
	public final static int ENEMY_TANK_SPACING = 20; 
	public final static int APP_MARGIN = 60; 
	public final static int NUM_ENEMY_TANKS = 20;
	
	private boolean stopDraw = false;
	
	//variable to hold the tank
	private Tank tank;
	
	// Background Image for the game - used for skinning
	private  PImage  bgImage; 
	
	// Skin number 
	private int skin; 
	
	
	//an array to hold our friendly enemyTanks
	private ArrayList<EnemyTank> enemyTanks = new  ArrayList<EnemyTank>(); //will hold a bunch of  enemyTanks

	//an array list that will hold any bullets  that are currently on screen.
	private ArrayList<Bullet> bullets = new  ArrayList<Bullet>();
	
	/**
	 * Stop Drawing the Screen 
	 * set the stopDraw property
	 */
	public void stopDraw(boolean stopDraw)
	{
		this.stopDraw = stopDraw;
	}

	//setters and getters
	
	
	/**
	 * Getter for the ArrayList of EnemyTank objects currently on the screen
	 * @return ArrayList of EnemyTank objects
	 */
	public ArrayList<EnemyTank> getEnemyTanks() {
		return this.enemyTanks;
	}

	/**
	 * Setter for the ArrayList of EnemyTank objects currently on the screen.
	 * @param enemyTanks An ArrayList of EnemyTank objects
	 */
	public void setEnemyTanks(ArrayList<EnemyTank> enemyTanks) {
		this.enemyTanks = enemyTanks;
	}

	/**
	 * Getter for the ArrayList of Bullet objects currently on the screen
	 * @return ArrayList of Bullet objects
	 */
	public ArrayList<Bullet> getBullets() {
		return this.bullets;
	}

	/**
	 * Setter for the ArrayList of Bullet objects currently on the screen.
	 * @param enemyTanks An ArrayList of Bullet objects
	 */
	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}

	
	/**
	 * Called once to set up window
	 */
	public void settings() {
		this.size(this.w, this.h); //set window size	
	}
	
	/**
	 * get the Width w of the Applet
	 */
	public int getWidth()
	{
		return w;
	}
	
	/**
	 * get the height h of the Applet
	 */
	public int getHeight()
	{
		return h;
	}
	
	/**
	 * Called once on load. Used to create the  window and "global" settings.
	 */
	public void setup() {
		skin = 0;
		this.background(this.BLACK); //set background  color
		this.bgImage = this.loadImage("Background.bmp");
		
		
		//initialize tank
		this.tank = new Tank(this); // pass reference to this TankWorld object
		
		//initialize all enemyTanks
		int x = TankWorld.APP_MARGIN; //x position of  first Enemy Tank
		int y = TankWorld.APP_MARGIN; //y position of  first Enemy Tank

		//loop as many times as there are enemyTanks
		for (int i=0; i<TankWorld.NUM_ENEMY_TANKS; i++) {
			
			//create a new Enemy Tank for each  element of the array
			EnemyTank enemyTank = new EnemyTank(x, y, this);  //pass the x,y coords and a  reference to this TankWorld class
			this.enemyTanks.add(enemyTank); //add this  Enemy Tank to the array list

			//update x so the  next Enemy Tank we draw appears further  to the right
			x += enemyTank.getWidth() + TankWorld.ENEMY_TANK_SPACING; 
			
			//stay within bounds of the screen
			if (x > this.width - enemyTank.getWidth() - TankWorld.APP_MARGIN) {
				//move down to the next line  before drawing next Enemy Tank
				x = TankWorld.APP_MARGIN; //reset back  to left side of screen
				y += enemyTank.getHeight() + TankWorld.ENEMY_TANK_SPACING; //update y so the  next row of enemyTanks appears  further down the screen
			}
			
		}
	}
	
	/**
	 * Called repeatedly approximately 24 times  per second - Draw the main screen
	 */
	public void draw() {
		//wipe the screen blank or load a Skin 
		if(stopDraw)
			return;
		if(skin == 0)  // Skin type = 0 
			this.background(this.BLACK);
		else
			this.image(this.bgImage,0,0,w,h);
		
		
		//draw the tank - this represents the player 
		this.tank.move(); //have the tank  move itself to a new location
		this.tank.draw(); // and then the tank draws itself
		
		//Draw all the bullets 
		for (int i=0; i<this.bullets.size(); i++) {
			Bullet bullet = this.bullets.get(i);
			bullet.move(); //have the bullet  move itself to a new location
			bullet.draw(); //have the bullet  draw itself to the screen
		}
		
		//Draw all the Tanks 
		for (int i=0; i<this.enemyTanks.size(); i++) {
			EnemyTank enemyTank = this.enemyTanks.get(i);
			enemyTank.move(); //have each enemy tank move  itself to a new location
			enemyTank.draw(); //have each enemy tank draw  itself to the screen
		}
		
		// Detect collisions between bullets fired by the Player's Tank 
		ArrayList<EnemyTank> enemyTanksToRemove = new  ArrayList<EnemyTank>(); //will hold the next  generation of enemyTanks
		ArrayList<Bullet> bulletsToRemove = new  ArrayList<Bullet>(); //will hold the  next generation of bullets
		for (Bullet bullet : this.bullets) {
			for (EnemyTank enemyTank : this.enemyTanks) {
				//our Bullet class has a static  method that checks whether there  is a collision
				if (Bullet.isCollision(bullet,  enemyTank) && !bullet.enemy) {
					//if there is a collision,  remove the bullet and the  Enemy Tank from the screen
					enemyTanksToRemove.add(enemyTank); // add this enemyTank to the list  that we will remove
					bulletsToRemove.add(bullet);  //add this bullet to the  list that we will remove
				}
			}
		}
		
		// Detect collision between the  Enemy bullets and the Player's Tank 
		ArrayList<Bullet> enemyBulletsToRemove = new  ArrayList<Bullet>(); //will hold the  next generation of bullets
		boolean removeTank = false;
		for (Bullet bullet : this.bullets) {
			//our Bullet class has a static  method that checks whether there  is a collision
			if (Bullet.isCollision(bullet,  this.tank) && bullet.enemy) {
				//if there is a collision,  remove the bullet and the  Enemy Tank from the screen
				removeTank = true; // add this Enemy Tank to the list  that we will remove
				enemyBulletsToRemove.add(bullet);  //add this bullet to the  list that we will remove
				break;
			}
		}
		
		if(removeTank)
			this.tank.kill(); //tell the Tank to  kill itself
		
		
		
		//enter all enemyTanks we earmarked as  removable into removal proceedings
		for (EnemyTank enemyTank : enemyTanksToRemove) {
			enemyTank.kill(); //tell the Enemy Tank to  kill itself
		}
		
		int numEnemyTanks = this.enemyTanks.size();
		if(numEnemyTanks == 0 )// All the enemies are dead so the  Player wins 
		{
			textSize(30);
			text(TankWorld.WIN_MESSAGE, w/2, h/2);
			stopDraw(true);
		}
		else 
		{
			textSize(30); // Show the number of Enemy Tanks on the Screen
			text(numEnemyTanks+"", w/15, h/8);
		}
		
		//remove all bullets that have been marked for removal
		for (Bullet bullet:bulletsToRemove) {
			bullet.kill(); //tell the bullet to  kill itself
		}
		
		//remove all the enemy bullets that have been marked for removal
		for (Bullet bullet:enemyBulletsToRemove) {
			bullet.kill(); //tell the bullet to  kill itself
		}

	}

	/**
	 * Called whenever a key is pressed by the  user.  Move  the Game Objects based on the keys and also control other aspects of the Game 
	 */
	public void keyPressed() {
		//the variable key holds a char  representing the key that was pressed.
		//processing has a few constants that  come in handy in determining which key  was pressed

		//left and right keys have codes
		if (this.key == PConstants.CODED) {
			switch (this.keyCode) {
				case PConstants.LEFT:
					this.tank.goLeft(); //turn left
					break;
				case PConstants.RIGHT:
					this.tank.goRight(); //turn right
					break;
				case PConstants.UP:
					this.tank.goUp(); //turn up
					break;
				case PConstants.DOWN:
					this.tank.goDown(); //turn down
					break;
			}
		}
		//space key does not have a code
		else if (key == ' ') {
			//handle space key
			this.tank.shoot();
		}
		else if ( key == PConstants.ENTER) // Restart Drawing  again 
		{
			stopDraw(false);
		}
		else if(key == 't' || key == 'T') // Turn the Tank - will allow for 360 Degree  Operation 
		{
			this.tank.goTurn();  //Turn the Tank
		}
		else if(key == 'q' || key == 'Q') // Set to Straight movement mode for the Tank 
		{
			this.tank.setMovementType(0);
		}
		else if(key == 'p' || key == 'P') // Set to a Random movement mode for the Tank 
		{
			this.tank.setMovementType(1); 
		}		
		else if(key == 'r' || key == 'R') // Reset and Restart the game 
		{
			setup(); // Call setUp to reset 
			stopDraw = false; // Start Drawing again
		}
		else if(key == 's' || key == 'S') // Toggle the Skin 
		{
			if(skin == 0) 
				skin = 1;
			else 
				skin = 0;
		}
		else if(key == 'e' || key == 'E') //Change the Enemy Tank Mode of movement 
		{
			if(EnemyTank.getMoveMode() == 0) 
				EnemyTank.setMoveMode(1);
			else 
				EnemyTank.setMoveMode(0);
		}
	}	
	
	
	/**
	 * Called Automatically - Main function
	 * @param args Command-line arguments (ignored)
	 */
	public static void main(String[] args) {
		PApplet.main("edu.nyu.cs.sk7374.TankWorld");
	}
	
}
