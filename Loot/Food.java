package Loot;

import javax.media.opengl.GL;

public class Food extends Loot {
	private final static float DISSOLVE_FACTOR = .1f;
	private float giveHP;
	private boolean dissolve;
	
	/**
	 * Food constructor
	 * @param x the x coordinate 
	 * @param y the y coordinate
	 * @param giveHP the HP that will be regenerated
	 * @param disolve true if the giveHP dissolves over time otherwise false
	 */
	public Food(float x,float y,float giveHP,boolean dissolve){
		super(x,y);
		this.giveHP = giveHP;
		this.dissolve = dissolve;
	}
	/**
	 * Gives the HP to the player
	 * @param player the player to give the HP to
	 */
	public double giveHP(){
		return giveHP;
	}
	
	public void update(){
		if(!dissolve)
			return;
		
		giveHP *= (1-DISSOLVE_FACTOR);
	}
	
	public void draw(GL gl){
		
	}
	
	
}
