package Menu;

import javax.media.opengl.GL;


public abstract class MenuObject {
	protected int minX,minY;
	protected int maxX,maxY;
	protected boolean selected;
	
	public MenuObject(int minX,int maxX,int minY,int maxY,boolean selected){
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
		this.selected = selected;
	}
	
	public int getMinX(){return minX;}
	public int getMaxX(){return maxX;}
	public int getMinY(){return minY;}
	public int getMaxY(){return maxY;}
	
	public void selected(boolean select){selected = select;}
	public boolean isSelected(){return selected;}
	
	public abstract void display(GL gl);
}