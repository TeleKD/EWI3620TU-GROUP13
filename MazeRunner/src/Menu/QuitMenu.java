package Menu;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

public class QuitMenu extends MenuObject implements Menu {
	private Button butt[];
	
	public static final byte YES = 0;
	public static final byte NO = 1;
	
	public QuitMenu(int minX,int maxX,int minY,int maxY){
		super(minX,maxX,minY,maxY,true);
		
		butt = new Button[2];
		butt[0] = new Button("Yes",minX,maxX,minY+(maxY-minY)/4,minY+(maxY-minY)/2,0f,1f,.5f,false);
		butt[1] = new Button("No",minX,maxX,minY,minY+(maxY-minY)/4,0f,1f,.5f,false);
		
	}
	
	
	public int getButton(int x,int y){
		if(butt[0].minX < x && x < butt[0].maxX && butt[0].minY < y && y < butt[0].maxY)
			return YES;
		if(butt[1].minX < x && x < butt[1].maxX && butt[1].minY < y && y < butt[1].maxY)
			return NO;
		return -1;
	}
	
	public void display(GL gl){
		gl.glPushMatrix();
		GLUT glut = new GLUT();
		float width = glut.glutStrokeLengthf(GLUT.STROKE_ROMAN, "Are your sure?"); // the width of the text-string in gl coordinations
		gl.glColor3f(1f,0f,0f);
		gl.glTranslatef(0, maxY-(maxY-minY)/4, 0); // Translation to upperside of screen
		gl.glScalef((maxX-minX)/width,(maxY-minY)/4/100f, 1f); // Text scale
		glut.glutStrokeString(GLUT.STROKE_ROMAN, "Are your sure?"); // Draw's the text
		gl.glPopMatrix();
		
		for(int i = 0; i < 2; i++)
			butt[i].display(gl);
	}
	
	/**
	* This methode is used to check if and what is selected
	**/
	public void update(int x, int y){
		// set all the buttons to false
		for (int i=0; i<butt.length; i++) {
			butt[i].selected(false);}
		
		// set selected button to true
		switch(getButton(x,y)){
		case YES: 		butt[YES].selected(true);		break;
		case NO: 		butt[NO].selected(true);		break;}
	}
	
	/**
	* This methode preforms the actions of the buttons
	**/
	public void start(int x, int y){
		switch(getButton(x,y)){
			case YES:
				MainMenu.bTerminate = true;
				System.exit(0); // temp normal this is not what you want because mayby there has thinks to be done before termination
				break;
			case NO:
				MainMenu.bTerminate = false;
				MainMenu.bQuitMenu = false;
				break;
		}
	}
}