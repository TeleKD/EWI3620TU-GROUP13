package Menu;
import javax.media.opengl.GL;


public abstract class MenuObject {
	protected int minX,minY;
	protected int maxX,maxY;
	protected GL gl;
	
	public MenuObject(GL gl,int minX,int maxX,int minY,int maxY){
		this.gl = gl;
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
	}
	
	public int getMinX(){return minX;}
	public int getMaxX(){return maxX;}
	public int getMinY(){return minY;}
	public int getMaxY(){return maxY;}
	
	
	public abstract void draw(boolean selected);
	public abstract void update();
}