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
	
	private static final String BACKGROUND_IMAGE_PATH = "Background.bmp"; 
	private static final String HELP_IMAGE_PATH = "help.jpg"; 
	private static final String WELCOME_IMAGE_PATH = "Welcome.jpg"; 
	private static final String YOU_WIN_PATH = "youwin.jpg"; 
	
	
	// Help and Welcome Messages 
	private static final String WELCOME_MESSAGE ="Welcome to Sanchit's Tank Commander";
	
	private static final String GAME_CONTROLS_MESSAGE = "Game Controls";
	private static final String ARROW_KEY_MESSAGE = "Press Arrow Keys to move the Tank ";
	private static final String SPACE_KEY_MESSAGE ="Press Space to Shoot at the Enemy ";
	private static final String T_KEY_MESSAGE ="Press T to move the Tank around";
	private static final String W_KEY_MESSAGE ="Press W to Enter Tank Straight Line move mode";
	private static final String O_KEY_MESSAGE ="Press O to Enter Tank random move mode (Default)";
	private static final String R_KEY_MESSAGE ="Press R to Restart the game";
	private static final String S_KEY_MESSAGE ="Press S to Change the Skin";
	private static final String E_KEY_MESSAGE ="Press E to Toggle Enemy Tank Movement Mode";
	private static final String Q_KEY_MESSAGE ="Press Q to Quit the Game";
	private static final String H_KEY_MESSAGE ="Press H anytime for Help";
	
	private static final String ENTER_KEY_MESSAGE = "Please press Enter to Continue!!";
	
	// Common Colors 
	public final int BLACK = this.color(0,0,0);
	public final int WHITE = this.color(255,255,255);
	
	// Common Keys 
	
	public final char KEY_s = 's';
	public final char KEY_S = 'S';
	public final char KEY_e = 'e';
	public final char KEY_E = 'E';
	public final char KEY_h = 'h';
	public final char KEY_H = 'H';
	public final char KEY_w = 'w';
	public final char KEY_W = 'W';
	public final char KEY_o = 'o';
	public final char KEY_O = 'O';
	public final char KEY_q = 'q';
	public final char KEY_Q = 'Q';
	public final char KEY_r = 'r';
	public final char KEY_R = 'R';
	public final char KEY_t = 't';
	public final char KEY_T = 'T';
	
	//Tank spacing constants 
	public final static int ENEMY_TANK_SPACING = 20; 
	public final static int APP_MARGIN = 60; 
	public final static int NUM_ENEMY_TANKS = 20;
	
	private static boolean welcomeShown= false; // Welcome Screen
	
	private boolean stopDraw = false; // Stop Drawing the screen 
	
	
	
	//variable to hold the tank
	private Tank tank;
	
	// Background Image for the game - used for skinning
	private  PImage  bgImage; 
	
	// Help Screen Image 
	private PImage  helpImage;
	
	
	// Welcome Screen Image 
	private PImage  welcomeImage;
	
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
		this.bgImage = this.loadImage(BACKGROUND_IMAGE_PATH);
		
		this.helpImage = this.loadImage(HELP_IMAGE_PATH);
		
		this.welcomeImage = this.loadImage(WELCOME_IMAGE_PATH);
		
		
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
		if(!TankWorld.welcomeShown)
			showWelcome();
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
			showSplash(YOU_WIN_PATH);
			textSize(30);
			text(TankWorld.R_KEY_MESSAGE, 100, 500);
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
	 * Show Help Messages 
	 * x , y move the message on the screen
	 */
	private void showHelpMessages(int x , int y )
	{
		this.textSize(20);
		this.text(TankWorld.GAME_CONTROLS_MESSAGE, x,y+170);
		this.text(TankWorld.ARROW_KEY_MESSAGE, x,y+190);
		this.text(TankWorld.SPACE_KEY_MESSAGE, x,y+210);
		this.text(TankWorld.T_KEY_MESSAGE, x,y+230);
		this.text(TankWorld.W_KEY_MESSAGE, x,y+250);
		this.text(TankWorld.O_KEY_MESSAGE, x,y+270);
		this.text(TankWorld.R_KEY_MESSAGE, x,y+290);
		this.text(TankWorld.S_KEY_MESSAGE, x,y+310);
		this.text(TankWorld.E_KEY_MESSAGE, x,y+330);
		this.text(TankWorld.Q_KEY_MESSAGE, x,y+350);
		this.text(TankWorld.H_KEY_MESSAGE, x,y+370);
		this.textSize(30);
		this.text(TankWorld.ENTER_KEY_MESSAGE, x,y+500);
	}
	/**
	 * Show Help
	 */
	private void showHelp()
	{
		this.image(this.helpImage,0,0,w,h);
		showHelpMessages(100,-100);
		this.stopDraw(true);
	}
	/**
	 * Show Welcome Splash
	 */
	private void showWelcome()
	{
		this.image(this.welcomeImage,0,0,w,h);
		this.textSize(30);
		this.text(WELCOME_MESSAGE, 100,100);
		showHelpMessages(100,0);
		this.stopDraw(true);
		welcomeShown = true;
	}
	
	public void showSplash(String image)
	{
		PImage pimage = this.loadImage(image);
		this.image(pimage,0,0,w,h);
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
		else if(key == KEY_t || key == KEY_T) // Turn the Tank - will allow for 360 Degree  Operation 
		{
			this.tank.goTurn();  //Turn the Tank
		}
		else if(key == KEY_w || key == KEY_W) // Set to Straight movement mode for the Tank 
		{
			this.tank.setMovementType(0);
		}
		else if(key == KEY_o || key == KEY_O) // Set to a Random movement mode for the Tank 
		{
			this.tank.setMovementType(1); 
		}		
		else if(key == KEY_r || key == KEY_R) // Reset and Restart the game 
		{
			setup(); // Call setUp to reset 
			stopDraw = false; // Start Drawing again
		}
		else if(key == KEY_s || key == KEY_S) // Toggle the Skin 
		{
			if(skin == 0) 
				skin = 1;
			else 
				skin = 0;
		}
		else if(key == KEY_e || key == KEY_E) //Change the Enemy Tank Mode of movement 
		{
			if(EnemyTank.getMoveMode() == 0) 
				EnemyTank.setMoveMode(1);
			else 
				EnemyTank.setMoveMode(0);
		}
		else if ( key == KEY_h || key == KEY_H) // Show Help Screen n 
		{
			showHelp();
		}
		else if ( key == KEY_q || key == KEY_Q) // Quit
		{
			System.exit(0);
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
