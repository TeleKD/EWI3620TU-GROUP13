package Editor;

import java.io.File;

import javax.media.opengl.GL;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

public class Level {
	
	protected int[][] level;
	private int x;
	private int y;
	protected float buttonSize;
	protected int lineWidth = 2;
	private Texture[] textureMaze;
	private int nTiles = 8; //this is the number of different tiles currently present in the maze
	
	//creates a level with borders at the edges of the matrix
	public Level(int x, int y){
		this.x = x;
		this.y = y;	
		level = new int [x][y];
		for (int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				level [i][j] = 0;	
				if(i == 0 || i == x-1 || j == 0 || j == y-1){
					level[i][j] = 1;
				}
			}
		}
	}
		
	//draws the level on the specified location
	public void draw(GL gl, float startx, float starty, float width, float height){
		
		textureMaze = new Texture[nTiles+1];
		
		//Loading all the textures in the maze
		try {
			textureMaze[6] = TextureIO.newTexture(new File("img\\StairsL.png"), false);
			textureMaze[8] = TextureIO.newTexture(new File("img\\StairsH.png"), false);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		buttonSize = height/y;
		
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				//Drawing the floor
				if (level[i][j] == 0){
					gl.glColor3f(150/255f, 73/255f, 37/255f);
				}
				//Drawing the walls
				else if(level[i][j] == 1){
					gl.glColor3f(87/255f, 84/255f, 83/255f);
				}
				//Drawing the voids, setting tile to black
				if(level[i][j] == 4){
					gl.glColor3f(0/255f, 0/255f, 0/255f);
				}

				
				//filling the squares
				gl.glBegin(GL.GL_QUADS);
				//clockwise
				gl.glVertex2f(startx+((width-x*buttonSize)/2)+i*buttonSize, (j+1)*buttonSize);
				gl.glVertex2f(startx+((width-x*buttonSize)/2)+(i+1)*buttonSize, (j+1)*buttonSize);
				gl.glVertex2f(startx+((width-x*buttonSize)/2)+(i+1)*buttonSize, (j)*buttonSize);
				gl.glVertex2f(startx+((width-x*buttonSize)/2)+i*buttonSize, (j)*buttonSize);
				gl.glEnd();
				
				//Drawing the voids, drawing a red cross
				if(level[i][j] == 4){
					gl.glColor3f(255/255f, 0/255f, 0/255f);
					gl.glLineWidth(3);
					gl.glBegin(GL.GL_LINES);
					gl.glVertex2f(startx+((width-x*buttonSize)/2)+i*buttonSize, (j+1)*buttonSize);		
					gl.glVertex2f(startx+((width-x*buttonSize)/2)+(i+1)*buttonSize, (j)*buttonSize);
					gl.glVertex2f(startx+((width-x*buttonSize)/2)+(i+1)*buttonSize, (j+1)*buttonSize);
					gl.glVertex2f(startx+((width-x*buttonSize)/2)+i*buttonSize, (j)*buttonSize);
					gl.glEnd();
				}
				
				//drawing textures if present
				for (int k = 0; k < nTiles+1; k++){	
					if (level[i][j] == k){
						if (textureMaze[k] != null) {
							//every textures will be drawn on a floor background
							gl.glColor3f(150/255f, 73/255f, 37/255f);
							textureMaze[k].enable();
							textureMaze[k].bind();
						}
						
						//TODO implement rotation for the direction of the stairs

						gl.glBegin(GL.GL_QUADS);
						gl.glTexCoord2f(0,0);
						gl.glVertex2f(startx+((width-x*buttonSize)/2)+i*buttonSize, (j+1)*buttonSize);
						gl.glTexCoord2f(1,0);
						gl.glVertex2f(startx+((width-x*buttonSize)/2)+(i+1)*buttonSize, (j+1)*buttonSize);
						gl.glTexCoord2f(1,1);
						gl.glVertex2f(startx+((width-x*buttonSize)/2)+(i+1)*buttonSize, (j)*buttonSize);
						gl.glTexCoord2f(0,1);
						gl.glVertex2f(startx+((width-x*buttonSize)/2)+i*buttonSize, (j)*buttonSize);
						gl.glEnd();

						
						//van links naar rechts, 'normaal': 0,0 1,0 1,1 0,1
						//van boven naar onder = 0,0 0,1 1,1 1,0
						//van rechts naar links = 1,0 0,0 0,1 1,1
						//van onder naar boven = 1,1 1,0 0,0 0,1
						
						if (textureMaze[k] != null) {
							textureMaze[k].disable();
						}

					}
					
				}
				
				
			}
		}
		
		//Drawing the GRID on top of everything!
		//set the LineWidth and the line color
		gl.glLineWidth(lineWidth);
		gl.glColor3f(255/255f, 255/255f, 255/255f);
		
		//vertical lines of the grid
		for(int i = 0; i <= x; i++){
			gl.glBegin(GL.GL_LINES);
			gl.glVertex2f(startx+((width-x*buttonSize)/2)+i*buttonSize, 0);
			gl.glVertex2f(startx+((width-x*buttonSize)/2)+i*buttonSize, height);
			gl.glEnd();
		}
		
		//horizontal lines of the grid
		for(int i = 0; i <= y; i++){
			gl.glBegin(GL.GL_LINES);
			gl.glVertex2f(startx+((width-x*buttonSize)/2), i*buttonSize);
			gl.glVertex2f(startx+width-((width-x*buttonSize)/2), i*buttonSize);
			gl.glEnd();
		}
		//End of drawing the grid
	}
	
	public  String toString(){
		String res = new String();
		for (int j = 0; j < y; j++){
			for(int i = 0; i < x; i++){
				res = res + level[i][j] + " ";
			}
			res = res +"\n";
		}
		return res;
	}
	
	public static void main(String[] args) { /* Not Used */ }
}
