package Menu;
import javax.media.opengl.GL;


public abstract class MenuObject {
	protected int minX,minY;
	protected int maxX,maxY;
	protected GL gl;
	protected boolean selected;
	
	public MenuObject(GL gl,int minX,int maxX,int minY,int maxY,boolean selected){
		this.gl = gl;
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
		this.selected = selected;
	}
	public MenuObject(){}
	
	public int getMinX(){return minX;}
	public int getMaxX(){return maxX;}
	public int getMinY(){return minY;}
	public int getMaxY(){return maxY;}
	
	public void selected(boolean select){selected = select;}
	public boolean isSelected(){return selected;}
	
	public abstract void draw();
	public abstract void update();
}