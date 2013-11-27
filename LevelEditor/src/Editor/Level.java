package Editor;

import javax.media.opengl.GL;

public class Level {
	
	protected int[][] level;
	private int x;
	private int y;
	protected float buttonSize;
	protected int lineWidth = 2;
	
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
