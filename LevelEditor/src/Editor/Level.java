package Editor;

import java.io.File;
import java.io.Serializable;

import javax.media.opengl.GL;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

public class Level implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8386177990027960325L;
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
			//textureMaze[0] = TextureIO.newTexture(new File("img\\Floor.png"), false); // do not use (yet) because of transparency issues
			//only implement if there is time: putting textures over the floor texture
			
			textureMaze[1] = TextureIO.newTexture(new File("img\\Wall.png"), false);
			textureMaze[2] = TextureIO.newTexture(new File("img\\TorchN.png"), false);
			textureMaze[3] = TextureIO.newTexture(new File("img\\TorchE.png"), false);
			textureMaze[5] = TextureIO.newTexture(new File("img\\TorchS.png"), false);
			textureMaze[6] = TextureIO.newTexture(new File("img\\StairsL.png"), false);
			textureMaze[7] = TextureIO.newTexture(new File("img\\TorchW.png"), false);
			textureMaze[8] = TextureIO.newTexture(new File("img\\StairsH.png"), false);
			//textureMaze[9] = TextureIO.newTexture(new File("img\\StairsH.png"), false);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		buttonSize = height/y;
		
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				
				//Drawing the floor color
				//Also be sure to maintain the background color of the floor for specific textures, making it not white
				if ((level[i][j] == 0 && textureMaze[0] == null) || level[i][j] == 2 || level[i][j] == 3 || level[i][j] == 5
						|| level[i][j] == 7){
					gl.glColor4f(150/255f, 73/255f, 37/255f, 1);
				}
				
 				//Drawing the floor color before a texture is applied
 				gl.glBegin(GL.GL_QUADS);
				gl.glVertex2f(startx+((width-x*buttonSize)/2)+i*buttonSize, (j+1)*buttonSize);
				gl.glVertex2f(startx+((width-x*buttonSize)/2)+(i+1)*buttonSize, (j+1)*buttonSize);
				gl.glVertex2f(startx+((width-x*buttonSize)/2)+(i+1)*buttonSize, (j)*buttonSize);
				gl.glVertex2f(startx+((width-x*buttonSize)/2)+i*buttonSize, (j)*buttonSize);
 				gl.glEnd();

				
				//Drawing the wall color
				if(level[i][j] == 1 && textureMaze[1] == null){
					gl.glColor4f(87/255f, 84/255f, 83/255f, 1);
				}
				//^is sneller dan textures^
				
				//Drawing the voids, setting tile to black
				if(level[i][j] == 4){
					gl.glColor3f(0/255f, 0/255f, 0/255f);
				}
				
				//drawing textures if present
				for (int k = 0; k < nTiles+1; k++){
					
					if (level[i][j] == k){
						if (textureMaze[k] != null) {
							gl.glEnable(GL.GL_BLEND);
							gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
							textureMaze[k].enable();
							textureMaze[k].bind();
							//White background color for normal texture view
							gl.glColor3f(255/255f, 255/255f, 255/255f);
						}
						

						int[] a = new int[2];	a[0] = 0; a[1] = 0;
						int[] b = new int[2];	b[0] = 1; b[1] = 0;
						int[] c = new int[2];	c[0] = 1; c[1] = 1;
						int[] d = new int[2];	d[0] = 0; d[1] = 1;
						
						//If the stairs are not facing from left to the right we need to adjust the direction of the textures
						
						//Start Drawing the stairs from right to left
						if ((level[i][j] == 6 && level[i-1][j] == 8) || (level[i][j] == 8 && level[i+1][j] == 6)){	
							a[0] = 1; a[1] = 0;
							b[0] = 0; b[1] = 0;
							c[0] = 0; c[1] = 1;
							d[0] = 1; d[1] = 1;
						}
						
						//Start Drawing the stairs from top to bottom
						if ((level[i][j] == 6 && level[i][j-1] == 8) || (level[i][j] == 8 && level[i][j+1] == 6)){	
							a[0] = 0; a[1] = 0;
							b[0] = 0; b[1] = 1;
							c[0] = 1; c[1] = 1;
							d[0] = 1; d[1] = 0;
						}
						
						//Start Drawing the stairs from bottom to top
						if ((level[i][j] == 6 && level[i][j+1] == 8) || (level[i][j] == 8 && level[i][j-1] == 6)){	
							a[0] = 1; a[1] = 1;
							b[0] = 1; b[1] = 0;
							c[0] = 0; c[1] = 0;
							d[0] = 0; d[1] = 1;
						}
						
						//normal texture drawing:
						gl.glBegin(GL.GL_QUADS);
						gl.glTexCoord2f(a[0],a[1]);
						gl.glVertex2f(startx+((width-x*buttonSize)/2)+i*buttonSize, (j+1)*buttonSize);
						gl.glTexCoord2f(b[0],b[1]);
						gl.glVertex2f(startx+((width-x*buttonSize)/2)+(i+1)*buttonSize, (j+1)*buttonSize);
						gl.glTexCoord2f(c[0],c[1]);
						gl.glVertex2f(startx+((width-x*buttonSize)/2)+(i+1)*buttonSize, (j)*buttonSize);
						gl.glTexCoord2f(d[0],d[1]);
						gl.glVertex2f(startx+((width-x*buttonSize)/2)+i*buttonSize, (j)*buttonSize);
						gl.glEnd();
						
						if (textureMaze[k] != null) {
							textureMaze[k].disable();
						}
					}	
				}
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
	
	public int[][] getLevel() {
		return level;
	}
	
	public static void main(String[] args) { /* Not Used */ }

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setLevel(int[][] level) {
		this.level = level;
	}
}
