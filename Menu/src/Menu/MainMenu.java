package Menu;

import javax.media.opengl.GL;

public class MainMenu extends MenuObject {
	private Button butt[]; // ArrayList doesn't work it will generate a lot of runtime Errors in openGL
	
	
	public MainMenu(GL gl,int minX,int maxX,int minY,int maxY){
		super(gl,minX,maxX,minY,maxY,true);
		System.out.println("MainMenu constructor");
		butt = new Button[3];
		butt[0] = new Button("Play",this.gl,minX,maxX,minY+(maxY-minY)*2/3,maxY,0f,1f,.5f,false);
		butt[1] = new Button("Options",this.gl,minX,maxX,minY+(maxY-minY)/3,minY+(maxY-minY)*2/3,0f,1f,.5f,false);
		butt[2] = new Button("Quit",this.gl,minX,maxX,minY,minY+(maxY-minY)/3,0f,1f,.5f,false);
	}
	
	
	public void draw(){
		butt[0].draw();
		butt[1].draw();
		butt[2].draw();
	}
	
	public void update(){}
	
}
