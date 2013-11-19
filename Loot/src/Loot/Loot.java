package Loot;

public abstract class Loot {
	protected float x,y;
	
	
	public Loot(float x,float y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gives the X location 
	 * @return the x coordinate
	 */
	public float getX(){return x;}
	/**
	 * Gives the Y location
	 * @return the y coordinate
	 */
	public float getY(){return y;}
	
	/**
	 * This methode can be used for loot that will lose potential over time
	 */
	public abstract void update();
	
	
	/**
	 * Draw's the loot
	 */
	public abstract void draw();
	
}
