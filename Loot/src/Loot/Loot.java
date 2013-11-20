package Loot;

import javax.media.opengl.GL;
import mazerunner.VisibleObject;

public abstract class Loot implements VisibleObject {
	protected double x,y,z;
	
	
	public Loot(double x,double y,double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Gives the X location 
	 * @return the x coordinate
	 */
	public double getX(){return x;}
	/**
	 * Gives the Y location
	 * @return the y coordinate
	 */
	public double getY(){return y;}
	/**
	 * Gives the z location
	 * @return the z coordinate
	 */
	public double getZ(){return z;}
	
	/**
	 * This methode can be used for loot that will lose potential over time
	 */
	public abstract void update();
	
	
	/**
	 * Draw's the loot
	 */
	public abstract void display(GL gl);
	
}
