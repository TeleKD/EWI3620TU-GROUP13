package Loot;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

public class Food extends Loot {
	private final static double DISSOLVE_FACTOR = .1f;
	private double giveHP;
	private boolean dissolve;
	
	/**
	 * Food constructor
	 * @param x the x coordinate 
	 * @param y the y coordinate
	 * @param giveHP the HP that will be regenerated
	 * @param disolve true if the giveHP dissolves over time otherwise false
	 */
	public Food(double x,double y,double z,double giveHP,boolean dissolve){
		super(x,y,z);
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
	
	public void display(GL gl){
		if(giveHP > 0){
			GLUT glut = new GLUT();

			// Set color and material.
			float wallColour[] = { 0f, 1f, 0f, 0f };						// green
			gl.glMaterialfv( GL.GL_FRONT, GL.GL_DIFFUSE, wallColour, 0);	// Set the materials

			// push matrix
			gl.glPushMatrix();
		
			// translate and scale to correct location
			gl.glTranslated(x, y, z);
			gl.glScaled(.5, .5, .5);
			
		
			// TEMP: draw a cube
			glut.glutSolidCube(2);
		
			// pop matrix
			gl.glPopMatrix();
		}
	}
	
	
}
