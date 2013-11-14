package gamestate;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import mazerunner.MazeRunner;
import mazerunner.UserInput;

import com.sun.opengl.util.Animator;

/**
 * De GameStateManager holds the main function of the game and the main display method. Also it provides
 * the switching functionality between game states
 * 
 * gameStates
 * 
 * MENU:	the game is in the menu
 * INGAME: 	the game is running
 * PAUSE: 	the game is paused (INGAME displaying is frozen)
 * 
 */
public class GameStateManager extends Frame implements GLEventListener{
	private static final long serialVersionUID = 4002514451736607431L;
	
	
	/*
	 * **********************************************
	 * *		attributes and constructor			*
	 * **********************************************
	 */
	
	private int screenWidth = 600, screenHeight = 600;		// Screen size.
	GLCanvas canvas;										// canvas for drawing
	GameState gameState;									// current GameState
	
	MazeRunner mazeRunner;									// INGAME functionality
	Pause pause;											// Pause functionality
	UserInput input;										// Mouse and Keyboard input functionality

	/**
	 * Initialises the complete game.
	 * <p>
	 * GSM extends Java AWT Frame, to function as the window. It creats a canvas on 
	 * itself where JOGL will be able to paint the OpenGL graphics. It then initializes all 
	 * game components and initializes JOGL, giving it the proper settings to accurately 
	 * display MazeRunner. Finally, it adds itself as the OpenGL event listener, to be able 
	 * to function as the view controller.
	 */
	public GameStateManager() {
		// Make a new window
		super("MazeRunner");
		
		// Set the window settings
		setSize( screenWidth, screenHeight);
		setBackground( Color.white );
		setLocationRelativeTo(null);
		
		// Set window closing action
		this.addWindowListener( new WindowAdapter()
		{
			public void windowClosing( WindowEvent e )
			{
				System.exit(0);
			}
		});
			
		// Initialise JOGL
		initJOGL();
		
		// Initialise and set a UserInput Object
		input = new UserInput(canvas);
		
		// Initialise a MazeRunner Object (INGAME)
		mazeRunner = new MazeRunner(canvas, input);
		// Initialise a Pause object (PAUSE)
		pause = new Pause();
		
		// set visible
		setVisible(true);
	}
	
	
	/*
	 * **********************************************
	 * *		Initialisation methods				*
	 * **********************************************
	 */
	
	/**
	 * initJOGL() sets up JOGL to work properly.
	 * <p>
	 * It sets the capabilities we want for MazeRunner, and uses these to create the GLCanvas upon which 
	 * MazeRunner will actually display our screen. To indicate to OpenGL that is has to enter a 
	 * continuous loop, it uses an Animator, which is part of the JOGL api.
	 */
	private void initJOGL()	{
		// First, we set up JOGL. We start with the default settings.
		GLCapabilities caps = new GLCapabilities();
		
		// Then we make sure that JOGL is hardware accelerated and uses double buffering.
		caps.setDoubleBuffered(true);
		caps.setHardwareAccelerated(true);

		// Now we add the canvas, where OpenGL will actually draw for us. We'll use settings we've just defined. 
		canvas = new GLCanvas(caps);
		add(canvas);
		
		/* We need to add a GLEventListener to interpret OpenGL events for us. Since MazeRunner implements
		 * GLEventListener, this means that we add the necesary init(), display(), displayChanged() and reshape()
		 * methods to this class.
		 * These will be called when we are ready to perform the OpenGL phases of MazeRunner. 
		 */
		canvas.addGLEventListener(this);
		
		/* We need to create an internal thread that instructs OpenGL to continuously repaint itself.
		 * The Animator class handles that for JOGL.
		 */
		Animator anim = new Animator(canvas);
		anim.start();
	}
	
	
	/*
	 * **********************************************
	 * *		OpenGL event handlers				*
	 * **********************************************
	 */

	/**
	 * init(GLAutodrawable) is called to initialise the OpenGL context
	 * <p>
	 * Implemented through GLEventListener. 
	 * <p> 
	 * It is <b>very important</b> to realize that there should be no drawing at all in this method.
	 */
	public void init(GLAutoDrawable drawable) {
		drawable.setGL( new DebugGL(drawable.getGL() )); 			// We set the OpenGL pipeline to Debugging mode.
	}
	
	/**
	 * display(GLAutoDrawable) is called upon whenever OpenGL is ready to draw a new frame and handles 
	 * all of the drawing.
	 * <p>
	 * Implemented through GLEventListener. 
	 * 
	 * The display function should be used to pick the right sub display function depending on the gameState
	 */
	public void display(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();
		
		// update the game status
		updateGameState(gl);

		// pick the right display function
		switch (gameState) {
		case INGAME: 
			mazeRunner.display(gl);
			break;
		case MENU:
			// TODO: implement menu state
			break;
		case PAUSE:
			// TODO: implement menu state
			break;
		default: 
			System.out.println("default case display loop");
		}
	}
	
	/**
	 * displayChanged(GLAutoDrawable, boolean, boolean) is called upon whenever the display mode changes.
	 * <p>
	 * Implemented through GLEventListener. 
	 * Seeing as this does not happen very often, we leave this unimplemented.
	 */
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}
	
	/**
	 * reshape(GLAutoDrawable, int, int, int, int, int) is called upon whenever the viewport changes shape, 
	 * to update the viewport setting accordingly.
	 * <p>
	 * Implemented through GLEventListener. 
	 * This mainly happens when the window changes size, thus changin the canvas (and the viewport 
	 * that OpenGL associates with it). It adjust the projection matrix to accomodate the new shape.
	 */
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL gl = drawable.getGL();
		GLU glu = new GLU();
		
		// Setting the new screen size and adjusting the viewport.
		screenWidth = width;
		screenHeight = height;
		gl.glViewport( 0, 0, screenWidth, screenHeight );
		
		// Set the new projection matrix.
		gl.glMatrixMode( GL.GL_PROJECTION );
		gl.glLoadIdentity();
		glu.gluPerspective( 60, screenWidth/screenHeight, .1, 200 );
		gl.glMatrixMode( GL.GL_MODELVIEW );
	}

	
	/*
	 * **********************************************
	 * *			updateGameState					*
	 * **********************************************
	 */
	
	/**	
	 * updateGameState(GL gl) updates the game status. If the gameState is null (start of the program)
	 * it initialises gameState.
	 */
	private void updateGameState(GL gl) {
		
		// gameState initialisation 
		if (gameState == null) {
			
			// TODO: ! change initialisation to MENU !
			mazeRunner.init(gl, screenWidth, screenHeight);
			gameState = GameState.INGAME;
		}
		
		/* 
		 * check the gameState and if a switch is required 
		 * and then switch the gameState
		 */
		else {
			switch(gameState) {
			case INGAME:
				if (input.isPause()) {
					gameState = GameState.PAUSE;}
				break;
			case PAUSE:
				if (!input.isPause()) {
					gameState = GameState.INGAME;}
				break;
			case MENU:
				break;
			}
		}
	}
	
	
	/*
	 * **********************************************
	 * *				  Main						*
	 * **********************************************
	 */
	
	/**
	 * Program entry point
	 */
	public static void main(String[] args) {
		// Create and run MazeRunner.
		new GameStateManager();
	}
}
