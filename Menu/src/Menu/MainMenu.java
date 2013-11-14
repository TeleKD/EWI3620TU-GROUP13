package Menu;

import javax.media.opengl.GL;

public class MainMenu extends MenuObject {
	private Button butt[]; // ArrayList doesn't work it will generate a lot of runtime Errors in openGL
	public PlayMenu playMenu;
	
	
	private boolean play;
	private boolean options;
	private boolean quit;
	
	
	public static final byte PLAY = 0;
	public static final byte OPTIONS = 1;
	public static final byte QUIT = 2;
	
	
	public MainMenu(GL gl,int minX,int maxX,int minY,int maxY){
		super(gl,minX,maxX,minY,maxY,true);
		
		playMenu = new PlayMenu(gl,minX,maxX,minY,maxY);
		
		butt = new Button[3];
		butt[0] = new Button("Play",this.gl,minX,maxX,minY+(maxY-minY)*2/3,maxY,0f,1f,.5f,false);
		butt[1] = new Button("Options",this.gl,minX,maxX,minY+(maxY-minY)/3,minY+(maxY-minY)*2/3,0f,1f,.5f,false);
		butt[2] = new Button("Quit",this.gl,minX,maxX,minY,minY+(maxY-minY)/3,0f,1f,.5f,false);
	}
	
	public int getButton(int x,int y){
		if(butt[0].minX < x && x < butt[0].maxX && butt[0].minY < y && y < butt[0].maxY)
			return PLAY;
		if(butt[1].minX < x && x < butt[1].maxX && butt[1].minY < y && y < butt[1].maxY)
			return OPTIONS;
		if(butt[2].minX < x && x < butt[2].maxX && butt[2].minY < y && y < butt[2].maxY)
			return QUIT;
		return -1;
	}
	
	public void selected(int button,boolean selected){
		butt[button].selected(selected);
	}
	
	public void draw(){
		if(play)
			playMenu.draw();
		/*else if(options)
			optionsMenu.draw();
		else if(quit)
			quitMenu.draw();*/
		else{
			butt[0].draw();
			butt[1].draw();
			butt[2].draw();
		}
	}
	
	public void setPlay(boolean set){play = set;}
	public void setOptions(boolean set){options = set;}
	public void setQuit(boolean set){quit = set;}
	
	public boolean getPlay(){return play;}
	public boolean getOptions(){return options;}
	public boolean getQuit(){return quit;}
	
	public void update(){}
	
}
