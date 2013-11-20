package Editor;

import java.awt.event.MouseEvent;

import javax.media.opengl.GL;

public class Button {
	
	private float x;
	private float y;
	private float sizex;
	private float sizey;
	private float R;
	private float G;
	private float B;
	protected boolean selected;
	
	public Button(GL gl, float x, float y, float sizex, float sizey, float R, float G, float B){
		this.x = x;
		this.y = y;
		this.sizex = sizex;
		this.sizey = sizey;
		this.R = R;
		this.G = G;
		this.B = B;
	}
	
	public void draw(GL gl){
		if (selected){
			gl.glColor3f(1-R,1-G,1-B);
			boxOnScreen(gl,x,y,sizex,sizey);
		}
		else{
			gl.glColor3f(R,G,B);
			boxOnScreen(gl,x,y,sizex,sizey);
		}	
	}
	
	public void boxOnScreen(GL gl, float x, float y, float sizex, float sizey) {
		gl.glBegin(GL.GL_POLYGON);
		gl.glVertex2f(x, y);
		gl.glVertex2f(x + sizex, y);
		gl.glVertex2f(x + sizex, y + sizey);
		gl.glVertex2f(x, y + sizey);
		gl.glEnd();
	}
	
	public void setSelected(boolean selected){
		this.selected = selected;
	}
	public void mouseReleased(MouseEvent me) {
		
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getSizex() {
		return sizex;
	}

	public float getSizey() {
		return sizey;
	}

	public float getR() {
		return R;
	}

	public float getG() {
		return G;
	}

	public float getB() {
		return B;
	}

}
