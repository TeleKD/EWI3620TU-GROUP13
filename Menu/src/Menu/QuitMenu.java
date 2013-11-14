package Menu;

import javax.media.opengl.GL;

public class QuitMenu extends MenuObject {
	private Button butt[];
	
	public static final byte YES = 0;
	public static final byte NO = 1;
	
	public QuitMenu(GL gl,int minX,int maxX,int minY,int maxY){
		super(gl,minX,maxX,minY,maxY,true);
		
		butt = new Button[2];
		butt[0] = new Button("Yes",this.gl,minX,maxX,minY+(maxY-minY)*1/2,maxY,0f,1f,.5f,false);
		butt[1] = new Button("No",this.gl,minX,maxX,minY+(maxY-minY)/2,minY+(maxY-minY)*1/2,0f,1f,.5f,false);
		
	}
	
	
	public int getButton(int x,int y){
		if(butt[0].minX < x && x < butt[0].maxX && butt[0].minY < y && y < butt[0].maxY)
			return YES;
		if(butt[1].minX < x && x < butt[1].maxX && butt[1].minY < y && y < butt[1].maxY)
			return NO;
		return -1;
	}
	
	public void draw(){
		// need also some text like "Are you sure?"
		
		for(int i = 0; i < 2; i++)
			butt[i].draw();
	}
	
	/**
	* This methode is used to check if and what is selected
	**/
	public void update(int x, int y){
		switch(getButton(x,y)){
			case YES:
				butt[NO].selected(false);
				butt[YES].selected(true);
				break;
			case NO:
				butt[YES].selected(false);
				butt[NO].selected(true);
				break;
			default:
				butt[YES].selected(false);
				butt[NO].selected(false);
		}
	}
	
	/**
	* This methode preforms the actions of the buttons
	**/
	public void start(int x, int y, boolean terminate){
		switch(getButton(x,y)){
			case YES:
				terminate = true;
				break;
			case NO:
				terminate = false;
				break;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}