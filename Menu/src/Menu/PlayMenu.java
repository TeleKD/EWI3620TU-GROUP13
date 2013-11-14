package Menu;

import javax.media.opengl.GL;

public class PlayMenu extends MenuObject {
	private Button butt[];
	
	PlayMenu(GL gl,int minX,int maxX,int minY,int maxY){
		super(gl,minX,maxX,minY,maxY,true);
		
		butt = new Button[3];
		
		butt[0] = new Button("New",this.gl,minX,maxX,minY+(maxY-minY)*2/3,maxY,0f,1f,.5f,false);
		butt[1] = new Button("Load",this.gl,minX,maxX,minY+(maxY-minY)/3,minY+(maxY-minY)*2/3,0f,1f,.5f,false);
		butt[2] = new Button("Back",this.gl,minX,maxX,minY,minY+(maxY-minY)/3,0f,1f,.5f,false);
	}
	
		
	public void draw(){
		for(int i = 0; i < 3; i++)
			butt[i].draw();
	}
	public void update(){}
}
