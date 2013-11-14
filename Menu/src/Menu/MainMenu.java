package Menu;

import javax.media.opengl.GL;

public class MainMenu extends MenuObject {
	private Button butt[]; // ArrayList doesn't work it will generate a lot of runtime Errors in openGL
	private PlayMenu playMenu;
	private QuitMenu quitMenu;
	
	private boolean playMenu;
	private boolean optionsMenu;
	private boolean quitMenu;
	private boolean terminate;
	
	
	public static final byte PLAY = 0;
	public static final byte OPTIONS = 1;
	public static final byte QUIT = 2;
	
	
	public MainMenu(GL gl,int minX,int maxX,int minY,int maxY){
		super(gl,minX,maxX,minY,maxY,true);
		
		playMenu = false;
		optionsMenu = false;
		quitMenu = false;
		terminate = false;
		
		playMenu = new PlayMenu(gl,minX,maxX,minY,maxY);
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
	
	/*public void selected(int button,boolean selected){
		butt[button].selected(selected);
	} */
	
	public void draw(){
		if(playMenu)
			playMenu.draw();
		/*else if(options)
			optionsMenu.draw();*/
		else if(quitMenu)
			quitMenu.draw();
		else{
			butt[0].draw();
			butt[1].draw();
			butt[2].draw();
		}
	}
	
	public void setPlay(boolean set){playMenu = set;}
	public void setOptions(boolean set){optionsMenu = set;}
	public void setQuit(boolean set){quitMenu = set;}
	
	public boolean getPlay(){return playMenu;}
	public boolean getOptions(){return optionsMenu;}
	public boolean getQuit(){return quitMenu;}
	
	/**
	* This methode is used to check if and what is selected
	**/
	public void update(int x,int y){
		if(menu.getPlay()){
			playMenu.update(x,y);
		}
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
		if(playMenu){
			playMenu.start(x,y,play);
		}
		else if(optionsMenu){
			// options menu
		}
		else if(quitMenu){
			quitMenu(x,y,terminate);
		}
		else{
			switch(getButton(x,y)){
				case PLAY:
					playMenu = true;
					break;
				case OPTIONS:
					optionsMenu = true;
					break;
				case QUIT:
					quitMenu = true;
					break;
			}
		}
	
}
