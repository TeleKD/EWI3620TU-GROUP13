package Menu;

import javax.media.opengl.GL;

public class PlayMenu extends MenuObject implements Menu {
	private Button butt[];
	
	protected boolean toGame = false;
	
	public static final byte NEW = 0;
	public static final byte LOAD = 1;
	public static final byte BACK = 2;
	
	PlayMenu(int minX,int maxX,int minY,int maxY){
		super(minX,maxX,minY,maxY,true);
		
		butt = new Button[3];
		
		butt[0] = new Button("New",minX,maxX,minY+(maxY-minY)*2/3,maxY,0f,1f,.5f,false);
		butt[1] = new Button("Load",minX,maxX,minY+(maxY-minY)/3,minY+(maxY-minY)*2/3,0f,1f,.5f,false);
		butt[2] = new Button("Back",minX,maxX,minY,minY+(maxY-minY)/3,0f,1f,.5f,false);
	}
	
	public int getButton(int x,int y){
		if(butt[0].minX < x && x < butt[0].maxX && butt[0].minY < y && y < butt[0].maxY)
			return NEW;
		if(butt[1].minX < x && x < butt[1].maxX && butt[1].minY < y && y < butt[1].maxY)
			return LOAD;
		if(butt[2].minX < x && x < butt[2].maxX && butt[2].minY < y && y < butt[2].maxY)
			return BACK;
		return -1;
	}
	/*public void selected(int button,boolean selected){
		butt[button].selected(selected);
	}*/
		
	public void display(GL gl){
		for(int i = 0; i < 3; i++)
			butt[i].display(gl);
	}
	/**
	* This methode is used to check if and what is selected
	**/
	public void update(int x,int y){
		
		// set all the buttons to false
		for (int i=0; i<butt.length; i++) {
			butt[i].selected(false);}
		
		// set selected button to true
		switch(getButton(x,y)){
		case NEW: 		butt[NEW].selected(true);		break;
		case LOAD: 		butt[LOAD].selected(true);		break;
		case BACK:		butt[BACK].selected(true);		break;}
	}
	
	/**
	 * This methode preforms the actions of the buttons
	 */
	public void start(int x, int y){
		switch(getButton(x,y)){
			case NEW:
				toGame = true;
			break;
			case LOAD:
				// load game
			break;
			case BACK:
				MainMenu.bPlayMenu = false;
			break;
		}
	}
}
