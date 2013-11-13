package Menu;

import java.util.ArrayList;

import javax.media.opengl.GL;

public class MainMenu extends MenuObject {
	private ArrayList<Button> buttons;
	
	
	public MainMenu(GL gl,int minX,int maxX,int minY,int maxY){
		super(gl,minX,maxX,minY,maxY);
		//buttons.add(new Button("Start Game",this.gl,this.minX,this.maxX,this.min));
	}
	
	
	public void draw(boolean selected){}
	public void update(){}
	
}
