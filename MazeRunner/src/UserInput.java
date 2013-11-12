import java.awt.event.*;

import javax.media.opengl.GLCanvas;

/**
 * The UserInput class is an extension of the Control class. It also implements three 
 * interfaces, each providing handler methods for the different kinds of user input.
 * <p>
 * For making the assignment, only some of these handler methods are needed for the 
 * desired functionality. The rest can effectively be left empty (i.e. the methods 
 * under 'Unused event handlers').  
 * <p>
 * Note: because of how java is designed, it is not possible for the game window to
 * react to user input if it does not have focus. The user must first click the window 
 * (or alt-tab or something) before further events, such as keyboard presses, will 
 * function.
 * 
 * @author Mattijs Driel
 *
 */
public class UserInput extends Control 
		implements MouseListener, MouseMotionListener, KeyListener
{
	// TODO: Add fields to help calculate mouse movement
	int muisX;
	int muisY;
	int muisXnext;
	int muisYnext;
	
	
	/**
	 * UserInput constructor.
	 * <p>
	 * To make the new UserInput instance able to receive input, listeners 
	 * need to be added to a GLCanvas.
	 * 
	 * @param canvas The GLCanvas to which to add the listeners.
	 */
	public UserInput(GLCanvas canvas)
	{
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		canvas.addKeyListener(this);
	}
	
	/*
	 * **********************************************
	 * *				Updating					*
	 * **********************************************
	 */

	@Override
	public void update()
	{
		// TODO: Set dX and dY to values corresponding to mouse movement
			dX = -(muisXnext-(muisX));
			muisX = muisXnext;
			
			dY = -(muisYnext-muisY);
			muisY = muisYnext;
	
	}

	/*
	 * **********************************************
	 * *		Input event handlers				*
	 * **********************************************
	 */

	@Override
	public void mousePressed(MouseEvent event)
	{
		// TODO: Detect the location where the mouse has been pressed
		muisX = event.getX();
		muisY = event.getY();
		muisXnext = event.getX();
		muisYnext = event.getY();
		
	}

	@Override
	public void mouseDragged(MouseEvent event)
	{		
		// TODO: Detect mouse movement while the mouse button is down
		muisX = muisXnext;
		muisY = muisYnext;
		
		
		muisXnext = event.getX();
		muisYnext = event.getY();

	}

	@Override
	public void keyPressed(KeyEvent event)
	{
		// TODO: Set forward, back, left and right to corresponding key presses
		int key = event.getKeyCode();
		
		if (key==KeyEvent.VK_UP || key==KeyEvent.VK_W) {
			forward = true;
		}
		else if (key==KeyEvent.VK_DOWN || key==KeyEvent.VK_S) {
			back = true;
		}
		else if (key==KeyEvent.VK_RIGHT || key==KeyEvent.VK_D) {
			right = true;
		}
		else if (key==KeyEvent.VK_LEFT || key==KeyEvent.VK_A) {
			left = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent event)
	{
		// TODO: Set forward, back, left and right to corresponding key presses
		int key = event.getKeyCode();
		
		if (key==KeyEvent.VK_UP || key==KeyEvent.VK_W) {
			forward = false;
		}
		else if (key==KeyEvent.VK_DOWN ||key==KeyEvent.VK_S) {
			back = false;
		}
		else if (key==KeyEvent.VK_RIGHT || key==KeyEvent.VK_D) {
			right = false;
		}
		else if (key==KeyEvent.VK_LEFT || key==KeyEvent.VK_A) {
			left = false;
		}
	}

	/*
	 * **********************************************
	 * *		Unused event handlers				*
	 * **********************************************
	 */
	
	@Override
	public void mouseMoved(MouseEvent event)
	{
	}

	@Override
	public void keyTyped(KeyEvent event)
	{
	}

	@Override
	public void mouseClicked(MouseEvent event)
	{
	}

	@Override
	public void mouseEntered(MouseEvent event)
	{
	}

	@Override
	public void mouseExited(MouseEvent event)
	{
	}

	@Override
	public void mouseReleased(MouseEvent event)
	{
	}


}
