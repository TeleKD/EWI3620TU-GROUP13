package Game;
import javax.media.opengl.GL;


public abstract class GameObject {
	protected int x,y;
	protected GL gl;
	
	public GameObject(GL gl,int x,int y){
		this.gl = gl;
		this.x = x;
		this.y = y;
	}
	
	public int getX(){return x;}
	public int getY(){return y;}
	
	
	public abstract void draw();
	public abstract void update();
}