package mazerunner;
import gamestate.GameState;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.media.opengl.GLCanvas;

/**
 * The UserInput class is an extension of the Control class. It also implements three 
 * interfaces, each providing handler methods for the different kinds of user input.
 *
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
	private GameState gameState;
	
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
	
	/**
	 * update the controlled variables
	 */
	@Override
	public void update()
	{
		// calculate rotation since previous update
		dX = locationXMouseDragged - locationXMouse;
		dY = locationYMouseDragged - locationYMouse;
		locationXMouse = locationXMouseDragged;
		locationYMouse = locationYMouseDragged;
		
		/* 
		 * calculate walking direction angle (relative to viewing direction), null 
		 * means there is no movement. 
		 */
		moveDirection = null;
		Point temp = new Point();
		
		if (forward) 	temp.translate(0,1);
		if (back)		temp.translate(0,-1);
		if (left) 		temp.translate(1,0);
		if (right)		temp.translate(-1,0);
		
		if (temp.distance(0,0) != 0)
			moveDirection = (int) Math.floor((180d/Math.PI)*Math.atan2(temp.x, temp.y));

	}

	/*
	 * **********************************************
	 * *		Input event handlers				*
	 * **********************************************
	 */

	@Override
	public void mousePressed(MouseEvent event)
	{
		if (gameState == GameState.INGAME) {
			locationXMouse = event.getX();
			locationYMouse = event.getY();
			locationXMouseDragged = locationXMouse;
			locationYMouseDragged = locationYMouse;}
	}

	@Override
	public void mouseDragged(MouseEvent event)
	{	
		if (gameState == GameState.INGAME) {
			locationXMouseDragged = event.getX();
			locationYMouseDragged = event.getY();
		}
	}

	@Override
	public void keyPressed(KeyEvent event)
	{	
		if(gameState == GameState.INGAME) {
			switch(event.getKeyCode()) {
			case KeyEvent.VK_W: forward = true; break;
			case KeyEvent.VK_A: left = true;	break;
			case KeyEvent.VK_S: back = true;	break;
			case KeyEvent.VK_D: right = true;	break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent event)
	{
		switch(event.getKeyCode()) {
		case KeyEvent.VK_W: forward = false; 	break;	
		case KeyEvent.VK_A: left = false;	 	break;
		case KeyEvent.VK_S: back = false;	 	break;	
		case KeyEvent.VK_D: right = false;	 	break;
		
		// pause/unpause
		case KeyEvent.VK_P: 
			if (gameState == GameState.INGAME) gameState = GameState.PAUSE;
			else if (gameState == GameState.PAUSE) gameState = GameState.INGAME;
			break;
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

	public GameState getGameState() {
		return gameState;
	}
	
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}


}
