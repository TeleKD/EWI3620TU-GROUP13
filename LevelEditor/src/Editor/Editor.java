package Editor;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import java.awt.event.MouseMotionListener;
import java.util.Arrays;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.sun.opengl.util.Animator;


public class Editor extends JFrame implements GLEventListener, MouseListener, MouseMotionListener {

	//Graphic related variables
	private GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private int screenWidth = gd.getDisplayMode().getWidth();
	private int screenHeight =  gd.getDisplayMode().getHeight();
	private GLCanvas canvas;
	
	//Maze related variables
	private int mazeX = 10;						//number of X-squares, 10 entered for testing
	private int mazeY = mazeX;
	private int nlevels = 6;									//number of levels in the maze
	private int buttonRow = 10;
	private float mazeL = ((screenWidth-screenHeight)/3*2);					//Left bound of mazeDrawingWindow
	private float mazeR = screenWidth-((screenWidth-screenHeight)/3);		//Right bound of mazeDrawingWindow
	
	//creates a level with walls on the borders
	//This is only for testing purposes TODO create a level in the JFrame popup when the user
	//clicks the "new level" button, then find a way to correspond that level to 1 of the 10 levels buttons
	Level level = new Level(mazeX,mazeY);
	
	private float gridBorder = (((mazeR-mazeL)-mazeX*level.buttonsizex)/2);
	private Level levels[] = new Level[nlevels];

	//Button related
	private Button btn[];
	private Button btnr[];		

	
	public Editor() {
		super("Level Editor v0.2");
		setSize(screenWidth, screenHeight);
		//setBackground(new Color(0.5f, 0.4f, 0.1f)); //doet niks
		GLCapabilities caps = new GLCapabilities();
		caps.setDoubleBuffered(true);
		caps.setHardwareAccelerated(true);
		canvas = new GLCanvas(caps);
		add(canvas);
		canvas.addGLEventListener(this);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		Animator anim = new Animator(canvas);
		anim.start();
		setUndecorated(true);
		setResizable(false);
		setVisible(true);
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
		
		for (int i = 0; i < buttonRow*3; i++){
			btn[i].draw(gl);
		}
		
		//left row of buttons
		for (int i = 0; i < buttonRow; i++){
			btnr[i].draw(gl);
		}
	

		
	}
	
	public int getButton(int x,int y){
		int i = -1;
		for(int it = 0;it<buttonRow*3;it++){
			if(btn[it].getX() < x && x < btn[it].getSizex()+btn[it].getX() && btn[it].getY() < y &&
					y < btn[it].getSizey()+btn[it].getY()){
				i = it;
				break;
			}
		}
		return i;
	}
	
	public int getButtonR(int x,int y){
		int i = -1;
		for(int it = 0; it<buttonRow;it++){
			if(btnr[it].getX() < x && x < btnr[it].getSizex()+btnr[it].getX() && btnr[it].getY() < y &&
					y < btnr[it].getSizey()+btnr[it].getY()){
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
		gl.glClearColor(75/255f, 94/255f, 127/255f, 1);
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
		
		//Creating the left buttonmenu
		float buttonsizex = mazeL/4;
    	float spacingx = buttonsizex/4;
    	float buttonsizey = screenHeight/(buttonRow+1);
    	float spacingy = buttonsizey/(buttonRow);
		btn = new Button[buttonRow*3];
		btnr = new Button[buttonRow];
		
		//Colors for the buttons on the left
		float colors[][] = new float[3][buttonRow*3];
		//color of the walldraw button
		colors[0][0] = 87/255f; colors[1][0] = 84/255f; colors[2][0] = 83/255f;
		
		//color of the second button
		colors[0][1] = 8/255f; colors[1][1] = 84/255f; colors[2][1] = 83/255f;
		
		//color of the other buttons (temporary)
		for (int i = 0; i < 3; i++){
			for (int j = 2; j < 30; j++){
				colors[i][j] = /*(float) Math.random();*/0.5f;
			}
		}
		
		//Texts for the buttons on the left
		String text[] = new String[30];
		for (int i = 3; i < 30; i++){
			text[i] = "TEMPORARY";
		}
		text[0] = "Wall"; text[1] = "Stairs"; text[2] = "Torch"; text[3] = "Door"; text[4] = "Chest"; text[5] = "Food";
		text[27] = "print 0"; text[28] = "print 1"; text[29] = "Clear";
		
		//Create the buttons on the left
	   	int index = 0;
	   	for(int row = 0;row<buttonRow;row++){
	   		for(int col = 0;col<3;col++){
	   			btn[index] = new Button(gl, spacingx+col*spacingx+col*buttonsizex, screenHeight - (row+1)*(spacingy+buttonsizey), 
	   					buttonsizex, buttonsizey, colors[0][index], colors[1][index], colors[2][index], text[index]);
	   			index++;
	   		 }
	   	 }
	   	//Texts for the buttons on the right
	   	String textr[] = new String[10];
	   	for (int i = 3; i < 9; i++){
	   		textr[i] = "Level " + (i-2);
	   	}
	   	textr[0] = "New Maze"; textr[1] = "Load Maze"; textr[2] = "Save Maze"; textr[9] = "Exit";
	   	
	   	//Create the buttons on the right
	   	int indexr = 0;
	   	for (int i = 0; i < buttonRow; i++){
	   		btnr[indexr] = new Button(gl, mazeR+spacingx, screenHeight - (spacingy+spacingy*(i)+(i+1)*buttonsizey), 
	   				(screenWidth-mazeR)-2*spacingx, buttonsizey, 0.5f, 0.5f, 0.5f, textr[indexr]);
	   		indexr++;
	   	}
	   	
	   	for (int k = 0; k < nlevels; k++){
	   		levels[k] = new Level(mazeX,mazeY);
	   	}
	   	level = levels[0];
	   	btnr[3].setSelected(true);
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
			for(int j = 0; j < buttonRow*3; j++){
				if (j != i){
					btn[j].setSelected(false);
				}
			}
		}
		int k = 0;
		k = getButtonR(me.getX(),screenHeight-me.getY());
		
		System.out.println(k);
		//set selected button to true
		
		if(k >= 3){
			btnr[k].setSelected(true);
			//set selected for other buttons to false
			for(int j = 3; j < buttonRow-1; j++){
				if (j != k){
					btnr[j].setSelected(false);
				}
			}
		}
		else if(k>=0){
	   		btnr[k].setSelected(true);;
	   		for(int j = 0; j<3; j++){
	   			if (j!=k){
	   				btnr[j].setSelected(false);
	   			}
	   		}
	   	}

		System.out.println("Dit is knop "+ k + " aan de rechter kant");
		//Exit!!!!!! KAY, EXIT!!
		if(k == 9){
			System.exit(0);
		}
	
		
		for (k = 3; k < nlevels+3; k++){
			if (btnr[k].selected){
				level = levels[k-3];
			}
		}
		if (btnr[0].selected){
			for(int j = 3; j < buttonRow-1; j++){
				if (btnr[j].selected){
					levels[j-3] = new Level(mazeX,mazeY);
					break;
				}
			} 
	  		for(int j = 0; j < buttonRow*3; j++){
	  			btn[j].setSelected(false);
	  		}
		}
	}

	@Override
	public void mousePressed(MouseEvent me) {
		double squareX = Math.floor(((me.getX() - (mazeL + gridBorder))/level.buttonsizex)+mazeX/2);   //waarom mazeX/2?
		double squareY = Math.floor(((me.getY())/level.buttonsizex));
		int X = (int) squareX;
		int Y = mazeY - (int) squareY;
		for(int j = 0; j<3; j++){
   			btnr[j].setSelected(false);
   		}
		if (btn[0].selected == true && squareX >= 0 && squareX < mazeX && squareY < mazeY && squareY >= 0){
			System.out.println("Hier kan getekent worden");
			level.level[X][Y-1] = 1;
		}
		
		if(SwingUtilities.isRightMouseButton(me)){
			level.level[X][Y-1] = 0;
		}
		
		//TEST
		if (btn[28].selected == true){
			System.out.println(levels[1].toString());
		}
		if (btn[27].selected == true){
			System.out.println(levels[0].toString());
		}
		
	}
	
	@Override
	public void mouseDragged(MouseEvent me){
		//System.out.println("Versleept");
		if(btn[0].selected || SwingUtilities.isRightMouseButton(me)){
			mousePressed(me);
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {/*NOT USED*/}
	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {/*NOT USED*/}
	@Override
	public void mouseClicked(MouseEvent me) {/*NOT USED*/}
	@Override
	public void mouseEntered(MouseEvent arg0) {/*NOT USED*/}
	@Override
	public void mouseExited(MouseEvent arg0) {/*NOT USED*/}
}
