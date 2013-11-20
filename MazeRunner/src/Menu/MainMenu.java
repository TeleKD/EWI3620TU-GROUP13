package Menu;

import gamestate.GameState;

import javax.media.opengl.GL;

import mazerunner.UserInput;

public class MainMenu extends MenuObject implements Menu {
	
	private UserInput input;
	
	private Button butt[];
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
	
	
	public MainMenu(UserInput input, int minX,int maxX,int minY,int maxY){
		super(minX,maxX,minY,maxY,true);
		
		bPlayMenu = false;
		bOptionsMenu = false;
		bQuitMenu = false;
		bTerminate = false;
		
		playMenu = new PlayMenu(minX,maxX,minY,maxY);
		optionsMenu = new OptionsMenu(minX,maxX,minY,maxY);
		quitMenu = new QuitMenu(minX,maxX,minY,maxY);
		
		butt = new Button[3];
		butt[0] = new Button("Play",minX,maxX,minY+(maxY-minY)*2/3,maxY,0f,1f,.5f,false);
		butt[1] = new Button("Options",minX,maxX,minY+(maxY-minY)/3,minY+(maxY-minY)*2/3,0f,1f,.5f,false);
		butt[2] = new Button("Quit",minX,maxX,minY,minY+(maxY-minY)/3,0f,1f,.5f,false);
		
		this.input = input;
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
	
	public void display(GL gl){
		if(bPlayMenu)
			playMenu.display(gl);
		else if(bOptionsMenu)
			optionsMenu.display(gl);
		else if(bQuitMenu)
			quitMenu.display(gl);
		else{
			butt[0].display(gl);
			butt[1].display(gl);
			butt[2].display(gl);
		}
		
		if (playMenu.toGame == true) {
			input.setGameState(GameState.INGAME);
			playMenu.toGame = false;
		}
	}
	
	public void setPlay(boolean set){
		bPlayMenu = set;}
	
	public void setOptions(boolean set){
		bOptionsMenu = set;}
	
	public void setQuit(boolean set){
		bQuitMenu = set;}
	
	public boolean getPlay(){
		return bPlayMenu;}
	
	public boolean getOptions(){
		return bOptionsMenu;}
	
	public boolean getQuit(){
		return bQuitMenu;}
	
	
	/**
	* This methode is used to check if and what is selected
	**/
	public void update(int x,int y){
		
		if		(bPlayMenu)		playMenu.update(x,y);
		else if	(bOptionsMenu)	optionsMenu.update(x,y);
		else if	(bQuitMenu)		quitMenu.update(x,y);
		
		else{
			// set all the buttons to false
			for (int i=0; i<butt.length; i++) {
				butt[i].selected(false);}
			
			// set selected button to true
			switch(getButton(x,y)){
			case PLAY: 		butt[PLAY].selected(true);		break;
			case OPTIONS: 	butt[OPTIONS].selected(true);	break;
			case QUIT:		butt[QUIT].selected(true);		break;}
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
