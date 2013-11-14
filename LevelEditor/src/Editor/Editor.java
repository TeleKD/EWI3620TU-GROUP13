package Editor;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.swing.JFrame;

import org.omg.CORBA.portable.InputStream;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.texture.TextureData;
import com.sun.opengl.util.texture.TextureIO;



public class Editor extends JFrame implements GLEventListener, MouseListener {
	
	private int breedte = 0;
	private int lengte = 0;
	private int lagen;
	
	
	
	private int screenWidth = 1000;
	private int screenHeight = 600;
	
	//private int knop = screenWidth/100;
	
	private GLCanvas canvas;
		
	public Editor() {
		super("Level Editor v0.001");

		setSize(screenWidth, screenHeight);
		setBackground(new Color(0.5f, 0.4f, 0.1f));
		
		//Sluiten mogelijk maken
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		
		GLCapabilities caps = new GLCapabilities();
		caps.setDoubleBuffered(true);
		caps.setHardwareAccelerated(true);

		canvas = new GLCanvas(caps);
		add(canvas);
		
		canvas.addGLEventListener(this);
		
		canvas.addMouseListener(this);

		Animator anim = new Animator(canvas);
		anim.start();

		setVisible(true);
	}
	
	//Teken knoppen
	public void drawButtons(GL gl){
		
		int knoplaag = 9;
		
		//achtergrond van de knoppen
		gl.glColor3f(0.5f, 0.094f, 0f);
		boxOnScreen(gl, screenWidth-120f, 10f, 110f, screenHeight-20f);
		
		//Create Level
		gl.glColor3f(0.8f, 0.03f, 0f);
		boxOnScreen(gl, screenWidth-110f, screenHeight-60f, 90f, 40f);
		
		//X-waarde invullen
		gl.glColor3f(0.8f, 0.03f, 0f);
		boxOnScreen(gl, screenWidth-110f, screenHeight-110f, 40f, 40f);
		
		//Y-waarde invullen
		gl.glColor3f(0.8f, 0.03f, 0f);
		boxOnScreen(gl, screenWidth-60f, screenHeight-110f, 40f, 40f);
		
		for(int i = 1; i <= knoplaag; i++){
			//knop links
			gl.glColor3f(0.8f, 0.03f, 0f);
			boxOnScreen(gl, screenWidth-110f, screenHeight-(110f+i*50), 40f, 40f);
			
			//knop rechts
			gl.glColor3f(0.8f, 0.03f, 0f);
			boxOnScreen(gl, screenWidth-60f, screenHeight-(110f+i*50), 40f, 40f);
			
		}
		
	}
	
	private void boxOnScreen(GL gl, float x, float y, float sizex, float sizey) {
		gl.glBegin(GL.GL_POLYGON);
		gl.glVertex2f(x, y);
		gl.glVertex2f(x + sizex, y);
		gl.glVertex2f(x + sizex, y + sizey);
		gl.glVertex2f(x, y + sizey);
		gl.glEnd();
	}

	@Override
	public void display(GLAutoDrawable drawable) {

		GL gl = drawable.getGL();

		// Set the clear color and clear the screen.
		gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);

		// Draw the buttons.
		drawButtons(gl);

		// Draw a figure based on the current draw mode and user input
		//drawFigure(gl);

		// Flush the OpenGL buffer, outputting the result to the screen.
		gl.glFlush();
		
	}

	@Override
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
		// TODO Auto-generated method stub
		
	}

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		new Editor();
	}

}
