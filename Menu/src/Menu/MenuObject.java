package Menu;
import javax.media.opengl.GL;


public abstract class MenuObject {
	protected int x,y;
	protected GL gl;
	
	public MenuObject(GL gl,int x,int y){
		this.gl = gl;
		this.x = x;
		this.y = y;
	}
	
	public int getX(){return x;}
	public int getY(){return y;}
	
	
	public abstract void draw();
	public abstract void update();
}