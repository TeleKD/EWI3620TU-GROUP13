package gamestate;

import java.awt.Font;

import javax.media.opengl.GL;

import com.sun.opengl.util.j2d.TextRenderer;

public class Pause {
	
	TextRenderer textRenderer = new TextRenderer(new Font(null, 0, 0));
	
	/*
	 * Displays pause on the screen when the game is paused
	 */
	public void display(GL gl) {
		
		textRenderer.begin3DRendering();
		textRenderer.setColor(1.0f, 0.0f, 0.0f, 1.0f);
		textRenderer.draw3D("PAUSE", 4f, 2.5f, 5.5f, 10f);
		textRenderer.end3DRendering();
	}
}
