package gamestate;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.GLUT;

public class Pause {
	
	String pauseString = "PAUSE";
	
	/*
	 * Displays pause on the screen when the game is paused
	 */
	public void display(GL gl) {
        GLUT glut = new GLUT();
        GLU glu = new GLU();
        
        // push matrices and set projection to '2D' orthogonal
		gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        glu.gluOrtho2D(0,600,0,600);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glPushMatrix();
        
        // Set colour and material.
        float wallColour[] = { 0f, 1f, 0f, 1f };						// red
        gl.glMaterialfv( GL.GL_FRONT, GL.GL_AMBIENT, wallColour, 0);	// Set the materials
        
        // Draw String
        gl.glLoadIdentity();
        int length = glut.glutBitmapLength(GLUT.BITMAP_9_BY_15, pauseString);
        gl.glColor3f(1f,0f,0f);
        gl.glRasterPos2i(300 - length/2, 20);
        glut.glutBitmapString(GLUT.BITMAP_9_BY_15, pauseString);
        
        // pop matrices
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glPopMatrix();
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glPopMatrix();
	}
}
