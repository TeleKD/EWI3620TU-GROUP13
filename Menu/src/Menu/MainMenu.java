package Menu;

import javax.media.opengl.GL;

public class MainMenu extends MenuObject implements Menu {
	private Button butt[]; // ArrayList doesn't work it will generate a lot of runtime Errors in openGL
	private PlayMenu playMenu;
	private OptionsMenu optionsMenu;
	private QuitMenu quitMenu;
	
	
	public static boolean bPlayMenu;
	public static boolean bOptionsMenu;
	public static boolean bQuitMenu;
	public static boolean bTerminate;
	
	
	public static final byte PLAY = 0;
	public static final byte OPTIONS = 1;
	public static final byte QUIT = 2;
	
	
	public MainMenu(GL gl,int minX,int maxX,int minY,int maxY){
		super(gl,minX,maxX,minY,maxY,true);
		
		bPlayMenu = false;
		bOptionsMenu = false;
		bQuitMenu = false;
		bTerminate = false;
		
		playMenu = new PlayMenu(gl,minX,maxX,minY,maxY);
		optionsMenu = new OptionsMenu(gl,minX,maxX,minY,maxY);
		quitMenu = new QuitMenu(gl,minX,maxX,minY,maxY);
		
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
	
	public void draw(){
		if(bPlayMenu)
			playMenu.draw();
		else if(bOptionsMenu)
			optionsMenu.draw();
		else if(bQuitMenu)
			quitMenu.draw();
		else{
			butt[0].draw();
			butt[1].draw();
			butt[2].draw();
		}
	}
	
	public void setPlay(boolean set){bPlayMenu = set;}
	public void setOptions(boolean set){bOptionsMenu = set;}
	public void setQuit(boolean set){bQuitMenu = set;}
	
	public boolean getPlay(){return bPlayMenu;}
	public boolean getOptions(){return bOptionsMenu;}
	public boolean getQuit(){return bQuitMenu;}
	
	/**
	* This methode is used to check if and what is selected
	**/
	public void update(int x,int y){
		if(bPlayMenu)
			playMenu.update(x,y);
		else if(bOptionsMenu)
			optionsMenu.update(x,y);
		else if(bQuitMenu)
			quitMenu.update(x, y);
		else{
			switch(getButton(x,y)){
			case PLAY:
				butt[OPTIONS].selected(false);
				butt[QUIT].selected(false);
				butt[PLAY].selected(true);
				break;
			case OPTIONS:
				butt[PLAY].selected(false);
				butt[QUIT].selected(false);
				butt[OPTIONS].selected(true);
				break;
			case QUIT:
				butt[PLAY].selected(false);
				butt[OPTIONS].selected(false);
				butt[QUIT].selected(true);
				break;
			default:
				butt[PLAY].selected(false);
				butt[OPTIONS].selected(false);
				butt[QUIT].selected(false);
			}
		}
	}
	/**
	* This methode preforms the actions of the buttons
	**/
	public void start(int x,int y){
		if(bPlayMenu){
			playMenu.start(x,y);
		}
		else if(bOptionsMenu){
			optionsMenu.start(x,y);
		}
		else if(bQuitMenu){
			quitMenu.start(x,y);
		}
		else{
			switch(getButton(x,y)){
				case PLAY:
					bPlayMenu = true;
					break;
				case OPTIONS:
					bOptionsMenu = true;
					break;
				case QUIT:
					bQuitMenu = true;
					break;
			}
		}
	}
	
}
