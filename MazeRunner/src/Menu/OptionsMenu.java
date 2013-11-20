package Menu;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

public class OptionsMenu extends MenuObject implements Menu {
	private Button butt[];
	
	public static final byte BACK = 0;
	
	
	public OptionsMenu(int minX,int maxX,int minY,int maxY){
		super(minX,maxX,minY,maxY,true);
		
		butt = new Button[1];
		butt[0] = new Button("Back",minX,maxX,minY,maxY/2,0f,1f,.5f,false);
	}
	
	public int getButton(int x,int y){
		if(butt[0].minX < x && x < butt[0].maxX && butt[0].minY < y && y < butt[0].maxY)
			return BACK;
		return -1;
	}
	
	public void display(GL gl){
		gl.glPushMatrix();
		GLUT glut = new GLUT();
		float width = glut.glutStrokeLengthf(GLUT.STROKE_ROMAN, "Options"); // the width of the text-string in gl coordinations
		gl.glColor3f(1f,0f,0f);
		gl.glTranslatef(0, maxY-(maxY-minY)/4, 0); // Translation to upperside of screen
		gl.glScalef((maxX-minX)/width,(maxY-minY)/4/100f, 1f); // Text scale
		glut.glutStrokeString(GLUT.STROKE_ROMAN, "Options"); // Draw's the text
		gl.glPopMatrix();
		
		
		for(int i = 0; i < 1; i++)
			butt[i].display(gl);
	}
	
	
	/**
	* This methode is used to check if and what is selected
	**/
	public void update(int x, int y){
		switch(getButton(x,y)){
			case BACK:
				butt[BACK].selected(true);
				break;
			default:
				butt[BACK].selected(false);
		}
	}
	
	/**
	* This methode preforms the actions of the buttons
	**/
	public void start(int x, int y){
		switch(getButton(x,y)){
			case BACK:
				MainMenu.bOptionsMenu = false;
				break;
		}
	}
}
