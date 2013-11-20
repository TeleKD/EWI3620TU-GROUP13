package mazerunner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import Loot.LootController;

/** 
 * MazeRunner is the class representing the INGAME GameState
 * 
 * @author Bruno Scheele, revised by Mattijs Driel
 */
public class MazeRunner {
	static final long serialVersionUID = 7526471155622776147L;

	
	/*
	 * **********************************************
	 * *		attributes and constructor			*
	 * **********************************************
	 */
	
	private ArrayList<VisibleObject> visibleObjects;						// A list of objects that will be displayed on screen.
	private Player player;													// the player
	private Enemy enemy;													// an enemy
	private Camera camera;													// the camera
	private UserInput input;												// user input object controls the game.
	private Maze maze; 														// the maze
	private long previousTime = Calendar.getInstance().getTimeInMillis(); 	// Used to calculate elapsed time.
	private LootController lootController;
	


	/*
 * **********************************************
 * *		Initialization methods				*
 * **********************************************
 */
	/**
	 * Initialises the the INGAME part of the game.
	 */
	public MazeRunner(UserInput input) {
		// set input
		this.input = input;
		
		// Initialise all the objects
		initObjects();
	}
	
	/**
	 * initializeObjects() creates all the objects needed for the game to start normally.
	 * <p>
	 * This includes the following:
	 * <ul>
	 * <li> the default Maze
	 * <li> the Player
	 * <li> the Camera
	 * <li> the User input
	 * </ul>
	 * <p>
	 * Remember that every object that should be visible on the screen, should be added to the
	 * visualObjects list of MazeRunner through the add method, so it will be displayed 
	 * automagically. 
	 */
	private void initObjects()	{
		// We define an ArrayList of VisibleObjects to store all the objects that need to be
		// displayed by MazeRunner.
		visibleObjects = new ArrayList<VisibleObject>();
		
		// Add the maze that we will be using.
		maze = new Maze();
		visibleObjects.add(maze);

		// Initialise the player.
		player = new Player(6 * Maze.SQUARE_SIZE + Maze.SQUARE_SIZE / 2, 	// x-position
							Maze.SQUARE_SIZE / 2,							// y-position
							5 * Maze.SQUARE_SIZE + Maze.SQUARE_SIZE / 2, 	// z-position
							90, 0,100);											// horizontal and vertical angle

		// initialise an enemy and add
		enemy = new Enemy(2 * Maze.SQUARE_SIZE + Maze.SQUARE_SIZE / 2, 		// x-position
						  Maze.SQUARE_SIZE / 2,								// y-position
						  4 * Maze.SQUARE_SIZE + Maze.SQUARE_SIZE / 2, 		// z-position
						  45,50);												// horizontal angle
		visibleObjects.add(enemy);
		
		lootController = new LootController();
		visibleObjects.add(lootController);
			
		
		// set up a camera
		camera = new Camera( player.getLocationX(), player.getLocationY(), player.getLocationZ(), 
				             player.getHorAngle(), player.getVerAngle() );
		
		// set player and enemy control
		player.setControl(input);
		enemy.setControl(new EnemyAI(enemy, player));
		
		
		
		
//		///// TESTING EDITMAZE /////
//		Thread mazeUpdate = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (true) {
//					try {
//						Thread.sleep(1000);
//						maze.editMaze(7, 2);
//					} 
//					catch (InterruptedException e) {e.printStackTrace();}	
//				}
//			}
//		});
//		mazeUpdate.start();
	}

	
/*
 * **********************************************
 * *		Initialise and draw methods 		*
 * **********************************************
 */

	/**
	 * init(GL) is called to setup GL for INGAME display.
	 * <p>
	 * It sets up most of the OpenGL settings for the viewing, as well as the general lighting.
	 * <p> 
	 * It is <b>very important</b> to realize that there should be no drawing at all in this method.
	 */
	public void init(GL gl, int screenWidth, int screenHeight) {
        GLU glu = new GLU();
        
        gl.glClearColor(0, 0, 0, 0);								// Set the background color.
        
        // Now we set up our viewpoint.
        gl.glMatrixMode( GL.GL_PROJECTION );						// We'll use orthogonal projection.
        gl.glLoadIdentity();										// Reset the current matrix.
        glu.gluPerspective(60, screenWidth, screenHeight, 200);		// Set up the parameters for perspective viewing. 
        gl.glMatrixMode( GL.GL_MODELVIEW );
        
        // Enable back-face culling.
        gl.glCullFace( GL.GL_BACK );
        gl.glEnable( GL.GL_CULL_FACE );
        
        // Enable Z-buffering.
        gl.glEnable( GL.GL_DEPTH_TEST );
        
        // Set and enable the lighting.
        float lightPosition[] = { 0.0f, 50.0f, 0.0f, 1.0f }; 			// High up in the sky!
        float lightColour[] = { 1.0f, 1.0f, 1.0f, 0.0f };				// White light!
        gl.glLightfv( GL.GL_LIGHT0, GL.GL_POSITION, lightPosition, 0 );	// Note that we're setting Light0.
        gl.glLightfv( GL.GL_LIGHT0, GL.GL_AMBIENT, lightColour, 0);
        gl.glEnable( GL.GL_LIGHTING );
        gl.glEnable( GL.GL_LIGHT0 );
        
        // Set the shading model.
        gl.glShadeModel( GL.GL_SMOOTH );
	}
	
	/**
	 * display(GL) is called upon by the GameStateManager when it is ready to draw a frame and the
	 * GameState is INGAME.
	 * <p>
	 * In order to draw everything needed, it iterates through MazeRunners' list of visibleObjects. 
	 * For each visibleObject, this method calls the object's display(GL) function, which specifies 
	 * how that object should be drawn. The object is passed a reference of the GL context, so it 
	 * knows where to draw.
	 */
	public void display(GL gl) {
		GLU glu = new GLU();
		
		// set the viewing transformation
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT );
		gl.glLoadIdentity();
        glu.gluLookAt( camera.getLocationX(), camera.getLocationY(), camera.getLocationZ(), 
 			   camera.getVrpX(), camera.getVrpY(), camera.getVrpZ(),
 			   camera.getVuvX(), camera.getVuvY(), camera.getVuvZ() );

        // Display all the visible objects of MazeRunner.
        for( Iterator<VisibleObject> it = visibleObjects.iterator(); it.hasNext(); ) {
        	it.next().display(gl);
        }

        gl.glLoadIdentity();
        // Flush the OpenGL buffer.
        gl.glFlush();
	}

	
/*
 * **********************************************
 * *			   update methods				*
 * **********************************************
 */

	/**
	 * update() updates the mazerunner game using the past time since the previous frame
	 */
	public void update() {
		// Calculating time since last frame.
		Calendar now = Calendar.getInstance();		
		long currentTime = now.getTimeInMillis();
		int deltaTime = (int)(currentTime - previousTime);
		previousTime = currentTime;
		
		// Update any movement since last frame.
		updateMovement(deltaTime);
		
		// Update Loot
		lootController.update(deltaTime,player.getLocationX(),player.getLocationZ());
		
		// Set camera according to the players position
		updateCamera();
	}
	
	/**
	 * updateMovement(int) updates the position of all objects that need moving.
	 * This includes rudimentary collision checking and collision reaction.
	 */
	private void updateMovement(int deltaTime)
	{
		// Update the player
		updatePlayerMovement(deltaTime);
		
		// Update the enemy
		updateEnemyMovement(deltaTime);
	}
	
	/**
	 * updatePlayerMovement(int) updates the player position and oriention
	 */
	private void updatePlayerMovement(int deltaTime) {
		// save current coordinates
		double previousX = player.getLocationX();
		double previousZ = player.getLocationZ();
		
		// update
		player.update(deltaTime);
		
		// check if a wall was hit
		boolean hitWall = maze.isWall(player.getLocationX(), player.getLocationZ());
		
		// set player back if a wall was hit
		if (hitWall){
			player.locationX = previousX;
			player.locationZ = previousZ;
		}
		
	}	
	
	/**
	 * updateEnemyMovement(int) updates the enemys position and orientation
	 */
	private void updateEnemyMovement(int deltaTime) {
		// save current coordinates
		double previousX = enemy.getLocationX();
		double previousZ = enemy.getLocationZ();
		
		// update
		enemy.update(deltaTime);
		
		// check if a wall was hit
		boolean hitWall = maze.isWall(enemy.getLocationX(), enemy.getLocationZ());
		
		// set player back if a wall was hit
		if (hitWall){
			enemy.locationX = previousX;
			enemy.locationZ = previousZ;
		}
		
	}
	
	/**
	 * updateCamera() updates the camera position and orientation.
	 * <p>
	 * This is done by copying the locations from the Player, since MazeRunner runs on a first person view.
	 */
	private void updateCamera() {
		camera.setLocationX( player.getLocationX() );
		camera.setLocationY( player.getLocationY() );  
		camera.setLocationZ( player.getLocationZ() );
		camera.setHorAngle( player.getHorAngle() );
		camera.setVerAngle( player.getVerAngle() );
		camera.calculateVRP();
	}
	
	
/*
 * **********************************************
 * *				  setters					*
 * **********************************************
 */

	/**
	 * sets the previousTime variable to the current time
	 */
	public void setPreviousTime() {
		this.previousTime = Calendar.getInstance().getTimeInMillis();
	}
}