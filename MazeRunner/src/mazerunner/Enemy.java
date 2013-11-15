package mazerunner;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

/**
 * Enemy represents enemys in the game.
 * <p>
 * This class extends GameObject to take advantage of the already implemented location 
 * functionality. Furthermore, it also contains the orientation of the Player, ie. 
 * where it is looking at and the player's speed. 
 * <p>
 * For the enemies to move, a reference to a Control object can be set, which wich
 * eventually should be controlled by some sort of AI
 * <p>
 * This class implements VisibleObject to force the developer to implement the display(GL)
 * method, so the Enemy can be displayed.
 * 
 * @author Marius
 *
 */
public class Enemy extends GameObject implements VisibleObject{
	private double horAngle;
	private double speed;
	
	private Control control;
	
	/**
	 * The Enemy constructor.
	 * <p>
	 * This is the constructor that should be used when creating a Player. It sets
	 * the starting location and orientation.
	 * <p>
	 * Note that the starting location should be somewhere within the maze of 
	 * MazeRunner, though this is not enforced by any means.
	 * 
	 * @param x		the x-coordinate of the location
	 * @param y		the y-coordinate of the location
	 * @param z		the z-coordinate of the location
	 * @param h		the horizontal angle of the orientation in degrees
	 */
	public Enemy( double x, double y, double z, double h) {
		// Set the initial position and viewing direction of the enemy.
		super(x, y, z);
		horAngle = h;
		speed = 0.008;
	}

	
	/*
	 * **********************************************
	 * *			Drawing functions 				*
	 * **********************************************
	 */
	
	/**
	 * enemy display function 
	 * FOR NOW DRAWS A BOX
	 */
	@Override
	public void display(GL gl) {
		GLUT glut = new GLUT();

        // Set colour and material.
        float wallColour[] = { 1f, 0f, 0f, 1f };						// red
        gl.glMaterialfv( GL.GL_FRONT, GL.GL_DIFFUSE, wallColour, 0);	// Set the materials

        // push matrix
        gl.glPushMatrix();
		
        // translate and rotate to correct location and orientation
        gl.glTranslated(locationX, locationY, locationZ );
		gl.glRotated(horAngle+45, 0, 1, 0);					// rotate around y axis
		
		// TEMP: draw a cube
		glut.glutSolidCube(2);
		
		// pop matrix
		gl.glPopMatrix();

	}

	
	
	/*
	 * **********************************************
	 * *					update					*
	 * **********************************************
	 */
		/**
	 * Updates the physical location and orientation of the player
	 * @param deltaTime The time in milliseconds since the last update.
	 */
	public void update(int deltaTime)
	{
		if (control != null)
		{
			// update control
			control.update();
			
			// move the enemy, according to control
			locationX -= speed*deltaTime*
					Math.sin((horAngle)*(Math.PI/180));
			locationZ -= speed*deltaTime*
					Math.cos((horAngle)*(Math.PI/180));
		}
	}
	
	/**
	 * Get the horizantol orientation
	 * @return
	 */
	protected double getHorAngle() {
		return horAngle;
	}

	protected void setHorAngle(double horAngle) {
		this.horAngle = horAngle;
	}

	protected double getSpeed() {
		return speed;
	}

	protected void setSpeed(double speed) {
		this.speed = speed;
	}

	protected Control getControl() {
		return control;
	}

	protected void setControl(Control control) {
		this.control = control;
	}





	
}
