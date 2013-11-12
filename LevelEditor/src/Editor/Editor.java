package Editor;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;

import com.sun.opengl.util.Animator;



public class Editor extends Frame{
	
	private int breedte = 0;
	private int lengte = 0;
	private int lagen;
	
	
	
	private int screenWidth = 900;
	private int screenHeight = 600;
	
	private int knop = screenWidth/100;
	
	private GLCanvas canvas;
	
	public Editor() {
		super("Level Editor v0.001");

		setSize(screenWidth, screenHeight);
		setBackground(new Color(0.0f, 1.0f, 1.0f, 1.0f));

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

		//Animator anim = new Animator(canvas);
		//anim.start();

		setVisible(true);
	}
	
	//Teken knoppen
	public void drawButtons(){
		
	}
	
	public static void main(String[] args){
		new Editor();
	}

}
