package mazerunner;
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
	private int locationXMouse, locationYMouse;
	private int locationXMouseDragged, locationYMouseDragged;
	
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
		// calculate rotation since previous update
		dX = locationXMouseDragged - locationXMouse;
		dY = locationYMouseDragged - locationYMouse;
		locationXMouse = locationXMouseDragged;
		locationYMouse = locationYMouseDragged;
		
		// calculate walking direction angle (relative to viewing direction)
		moveDirection = null;
		if (back == forward) {
			if (left != right) {
				if (left) 	moveDirection = 90;
				else 		moveDirection = -90;}}
		else {
			if (left == right) {
				if(forward) moveDirection = 0;
				else		moveDirection = 180;}
			else {
				if (forward) {
					if (left)	moveDirection = 45;
					else		moveDirection = -45;}
				else {
					if (left)	moveDirection = 135;
					else		moveDirection = -135;}}}
		
	}

	/*
	 * **********************************************
	 * *		Input event handlers				*
	 * **********************************************
	 */

	@Override
	public void mousePressed(MouseEvent event)
	{
		locationXMouse = event.getX();
		locationYMouse = event.getY();
		locationXMouseDragged = locationXMouse;
		locationYMouseDragged = locationYMouse;
	}

	@Override
	public void mouseDragged(MouseEvent event)
	{		
		locationXMouseDragged = event.getX();
		locationYMouseDragged = event.getY();
	}

	@Override
	public void keyPressed(KeyEvent event)
	{	
		switch(event.getKeyCode()) {
		case KeyEvent.VK_W: forward = true; System.out.println("press W");break;
		case KeyEvent.VK_A: left = true;	System.out.println("press A");break;
		case KeyEvent.VK_S: back = true;	System.out.println("press S");break;
		case KeyEvent.VK_D: right = true;	System.out.println("press D");break;
		}
	}

	@Override
	public void keyReleased(KeyEvent event)
	{
		switch(event.getKeyCode()) {
		case KeyEvent.VK_W: forward = false; System.out.println("release W");break;	
		case KeyEvent.VK_A: left = false;	 System.out.println("release A");break;
		case KeyEvent.VK_S: back = false;	 System.out.println("release S");break;	
		case KeyEvent.VK_D: right = false;	 System.out.println("release D");break;
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
