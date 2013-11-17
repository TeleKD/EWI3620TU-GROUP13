package Editor;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import java.util.Arrays;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.swing.JFrame;

import com.sun.opengl.util.Animator;




public class Editor extends JFrame implements GLEventListener, MouseListener {

	//Graphic related variables
	private GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int screenWidth = gd.getDisplayMode().getWidth();
	private int screenHeight =  gd.getDisplayMode().getHeight();
	private GLCanvas canvas;
	
	//Maze related variables
	private int mazeX = 50;						//number of X-squares, 10 entered for testing
	private int mazeY = mazeX;
	private int levels;									//number of levels in the maze
	private int knoplaag = 7;
	private float mazeL = screenWidth/8;					//Left bound of mazeDrawingWindow
	private float mazeR = screenWidth-screenWidth/16;		//Right bound of mazeDrawingWindow
	//creates a level with walls on the borders
	//This is only for testing purposes TODO create a level in the JFrame popup when the user
	//clicks the "new level" button, then find a way to correspond that level to 1 of the 10 levels buttons
	Level level = new Level(mazeX,mazeY);
		
	//Button related
	private Button btn[];
	private Button back[];
		
	
	public Editor() {
		super("Level Editor v0.001");
		setSize(screenWidth, screenHeight);
		setBackground(new Color(0.5f, 0.4f, 0.1f));
		GLCapabilities caps = new GLCapabilities();
		caps.setDoubleBuffered(true);
		caps.setHardwareAccelerated(true);
		canvas = new GLCanvas(caps);
		add(canvas);
		canvas.addGLEventListener(this);
		canvas.addMouseListener(this);
		Animator anim = new Animator(canvas);
		anim.start();
		setUndecorated(true);
		setResizable(false);
		setVisible(true);			//even niet
	}
	
	public static void main(String[] args){
		
		new Editor();
		System.out.println("Level editor started\nGenerating maze...\n");		
	}
	
	
	public void drawLevel(GL gl){
		level.draw(gl, mazeL, 0, mazeR-mazeL, screenHeight);
	}
	
	//Button definition
	public void Buttons(GL gl){
		
		back[0].draw(gl);
		back[1].draw(gl);
		
		for(int i=0;i<knoplaag*2-1;i++){
			btn[i].draw(gl);
		}

		
	}
	
	public int getButton(int x,int y){
		int i = -1;
		for(int it = 0;it<knoplaag*2-1;it++){
			if(btn[it].getX() < x && x < btn[it].getSizex()+btn[it].getX() && btn[it].getY() < y && y < btn[it].getSizey()+btn[it].getY()){
				i = it;
				break;
			}
		}
		return i;
	}
	
	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {

		GL gl = drawable.getGL();

		// Set the clear color and clear the screen.
		gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);

		// Draw the buttons.
		Buttons(gl);
		
		// Draw the level
		drawLevel(gl);

		// Flush the OpenGL buffer, outputting the result to the screen.
		gl.glFlush();
	}

	@Override
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
		// TODO Auto-generated method stub
		
	}
	
	// TODO nog te optimaliseren en volledig te begrijpen
	@Override
	public void init(GLAutoDrawable drawable) {

		//Retrieve the OpenGL handle, this allows us to use OpenGL calls.
		GL gl = drawable.getGL();

		// Set the matrix mode to GL_PROJECTION, allowing us to manipulate the
		// projection matrix
		gl.glMatrixMode(GL.GL_PROJECTION);

		// Always reset the matrix before performing transformations, otherwise
		// those transformations will stack with previous transformations!
		gl.glLoadIdentity();

		/*
		* glOrtho performs an "orthogonal projection" transformation on the
		* active matrix. In this case, a simple 2D projection is performed,
		* matching the viewing frustum to the screen size.
		*/
		
		gl.glOrtho(0, screenWidth, 0, screenHeight, -1, 1);

		// Set the matrix mode to GL_MODELVIEW, allowing us to manipulate the
		// model-view matrix.
		gl.glMatrixMode(GL.GL_MODELVIEW);

		// We leave the model view matrix as the identity matrix. As a result,
		// we view the world 'looking forward' from the origin.
		gl.glLoadIdentity();

		// We have a simple 2D application, so we do not need to check for depth
		// when rendering.
		gl.glDisable(GL.GL_DEPTH_TEST);	
		
		//print the level for reference, should be turned off eventually
		System.out.println(level.toString());
		
		
		float buttonsize = screenWidth/24; 
		float spacing = screenWidth/72;
		
		btn = new Button[knoplaag*2];
		
		for(int i = 0;i<knoplaag;i++){
			btn[i*2] = new Button(gl, screenWidth/72, screenHeight - (3*(spacing+buttonsize)+(i+1)*(spacing+buttonsize)), buttonsize, buttonsize, 0.8f, 0.9f, 0f);
		}
		int nexter = 0;
		for(int i = 1;i<knoplaag;i++){
			btn[i+nexter] = new Button(gl, buttonsize+2*screenWidth/72, screenHeight - (3*(spacing+buttonsize)+i*(spacing+buttonsize)), buttonsize, buttonsize, 0.8f, 0.9f, 0f);
			nexter = nexter + 1;
		}
		
		back = new Button[2];
		//background for the buttons at the right side		
		back[0] = new Button(gl, mazeR, 0, screenWidth/16, screenHeight, 0.8f, 0.03f, 0f);		
		//background for the buttons at the left side		
		back[1] = new Button(gl, 0, 0, mazeL, screenHeight, 0.8f, 0.03f, 0f);
				
				
	}
	
	@Override
	public void mouseReleased(MouseEvent me) {
		int i;
		System.out.println(me.getX() + " " + me.getY());
		i = getButton(me.getX(),screenHeight-me.getY());
		//set selected button to true
		if(i>=0){
			btn[i].setSelected(true);
			//set selected for other buttons to false
			for(int j = 0; j < knoplaag*2-1; j++){
				if (j != i){
					btn[j].setSelected(false);
				}
			}
			
		}
		System.out.println("Dit is knop "+ i);
		if(i == (knoplaag-1)*2){
			System.exit(0);
		}
				
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if (btn[0].selected == true){
			//TODO check if the mouse is in a square in the level and if so, change the level[x][y] to 1!
		}
		
	}

}
