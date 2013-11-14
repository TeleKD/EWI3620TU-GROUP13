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
	
	public Button(float x, float y, float sizex, float sizey, float R, float G, float B){
		this.x = x;
		this.y = y;
		this.sizex = sizex;
		this.sizey = sizey;
		this.R = R;
		this.G= G;
		this.B = B;		
		
		
		
	}
	
	public void draw(GL gl){
		gl.glColor3f(R, G, B);
		boxOnScreen(gl, x, y, sizex, sizey);
	}
	
	//kan waarschijnlijk weg
	public float[] clicked(){
		float [] res = new float[4];
		res[0]=x;
		res[1]=y;
		res[2]=sizex;
		res[3]=sizey;
		
		return res;
	}
	
	public void boxOnScreen(GL gl, float x, float y, float sizex, float sizey) {
		gl.glBegin(GL.GL_POLYGON);
		gl.glVertex2f(x, y);
		gl.glVertex2f(x + sizex, y);
		gl.glVertex2f(x + sizex, y + sizey);
		gl.glVertex2f(x, y + sizey);
		gl.glEnd();
	}
	
	public void mouseReleased(MouseEvent me) {
		
		System.out.println(me.getX() + " " + me.getY());
		//these floats define the boundaries of the buttons
		//float Xleft = screenWidth-110f;				//left button, left boundary
		//float Xleftr = screenWidth-70f;				//left button, right boundary
		//float Xright = screenWidth-20f;				//right button, right boundary
		//float Xrightl = screenWidth-60f;			//right button, left boundary
		
		//these floats define the upper and lower boundary of the big top button, from
		//here there is always a difference of 50
		//float Ytop = screenHeight - 20f;
		//float Ybottom = screenHeight - 60f;
		
		boolean pressed = false;
		
		if(me.getX() > x && me.getX() < sizex+x /*&& me.getY() < Ytop && me.getY() > Ybottom*/){
			System.out.println("bovenste knop is aangeklikt");
		}
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

}
