package Menu;
import  Game.GameObject;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

public class Button extends GameObject{
	private int maxX,maxY;
	String text;
	
	public Button(String text,GL gl,int minX,int maxX,int minY,int maxY){
		super(gl,minX,minY);
		this.text = text;
		this.maxX = maxX;
		this.maxY = maxY;
	}
	
	public void draw(){
		
		gl.glColor3f(0f, 1f, 0f); // kleur van button
		// tekenen van de button
		gl.glBegin(GL.GL_QUADS);
		gl.glVertex2f(x, y);
		gl.glVertex2f(x+maxX, y);
		gl.glVertex2f(x+maxX, maxY);
		gl.glVertex2f(x,maxY);
		gl.glEnd();
		
		
		
		// hier moet de tekst nog op de button gezet worden
		gl.glPushMatrix();
		GLUT glut = new GLUT();
		float width = glut.glutStrokeLengthf(GLUT.STROKE_ROMAN, text);
		
		gl.glColor3f(1.0f,0f,0f); // kleur van tekst
		
		
		//gl.glWindowPos2d(x+(maxX-x-text.length()*9)/2, y+(maxY-y-1)/2); // *9 omdat Helvetica_18 9 pixels groot is per letter
		
		gl.glTranslatef(x+15, y+15, 0);
		gl.glScalef((maxX-x-30)/width, (maxY-y-30)/100f, 1f);
		glut.glutStrokeString(GLUT.STROKE_ROMAN, text);
				
		
		//glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, text);
		
		gl.glPopMatrix();
	}
	
	public void update(){
		/*
		 * update functie
		 */
	}
	
}
