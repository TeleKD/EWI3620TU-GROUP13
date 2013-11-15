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
	private int mouseX;
	private int mouseY;

	int knoplaag = 7;

	private int screenWidth = 1000;
	private int screenHeight = 600;
	
	private Button btn[];
	
	private GLCanvas canvas;
		
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
		
		//setDefaultLookAndFeelDecorated(false);
		setUndecorated(true);
		setResizable(false);
		setVisible(true);
	}
	
	//Button definition
	public void Buttons(GL gl){
		
		//background for the buttons		
		Button background = new Button(gl, 0, 0, screenWidth/8, screenHeight, 0.8f, 0.03f, 0f);
		background.draw(gl);
		
		for(int i=0;i<knoplaag*2-1;i++){
			btn[i].draw(gl);
		}

		
	}
	
	@Override
	public void mouseReleased(MouseEvent me) {
		int i;
		System.out.println(me.getX() + " " + me.getY());
		i = getButton(me.getX(),screenHeight-me.getY());
		if(i>=0){
			btn[i].setSelected(true);
		}
		System.out.println("Dit is knop "+ i);
		if(i == (knoplaag-1)*2){
			System.exit(0);
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
	
	public static void main(String[] args){
		new Editor();
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

}
