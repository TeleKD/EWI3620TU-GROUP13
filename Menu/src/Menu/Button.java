package Menu;
import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

public class Button extends MenuObject{
	private String text;
	
	/**
	 * Button constructor
	 * @param text The text on the button
	 * @param gl The GL to draw to
	 * @param minX The left x
	 * @param maxX the right x
	 * @param minY the bottom y
	 * @param maxY the top y
	 */
	public Button(String text,GL gl,int minX,int maxX,int minY,int maxY){
		super(gl,minX,maxX,minY,maxY);
		this.text = text;
	}
	/**
	 * Draw's the button
	 */
	public void draw(boolean selected){
		if(selected)
			gl.glColor3f(0f, 1f, 0f); // Color of the button background when selected
		else 
			gl.glColor3f(0f, 0f, 1f); // Color of the button background when not selected
		
		
		
		// Draw's the button background
		gl.glBegin(GL.GL_QUADS);
		gl.glVertex2f(minX, minY);
		gl.glVertex2f(maxX, minY);
		gl.glVertex2f(maxX, maxY);
		gl.glVertex2f(minX,maxY);
		gl.glEnd();
		
		
		
		// Draw's the text on the button
		gl.glPushMatrix();
		GLUT glut = new GLUT();
		float width = glut.glutStrokeLengthf(GLUT.STROKE_ROMAN, text); // the width of the text-string in gl coordinations
		float borderGap = 15; // Gap between the button border and the text
		gl.glColor3f(1.0f,0f,0f); // Color of the text
		
		gl.glTranslatef(minX+borderGap, minY+borderGap, 0); // Translation to the button
		gl.glScalef((maxX-minX-borderGap*2)/width, (maxY-minY-borderGap*2)/100f, 1f); // Text scale to the button size
		glut.glutStrokeString(GLUT.STROKE_ROMAN, text); // Draw's the text
		
		gl.glPopMatrix();
	}
	
	public void update(){
		/*
		 * update function
		 */
	}
	
}
