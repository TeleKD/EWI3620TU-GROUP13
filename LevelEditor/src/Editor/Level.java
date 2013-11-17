package Editor;

import javax.media.opengl.GL;

public class Level {
	
	private int[][] level;
	int x;
	int y;
	
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
		
	//TODO vakjes vierkant maken en goed centreren
		//TODO testen welke grootte maze nog werkbaar is en of zoom en panfunctie vereist zijn
		
		float buttonsizex = width/x;
		float buttonsizey = height/y;
		
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				//Drawing the floor
				if (level[i][j] == 0){
					gl.glColor3f(0.8f, 0.9f, 0.4f);
				}
				//Drawing the walls
				else if(level[i][j] == 1){
					gl.glColor3f(0.0f, 0.9f, 0.4f);
				}
				
				//filling the squares
				gl.glBegin(GL.GL_QUADS);
				//clockwise
				gl.glVertex2f(startx+i*buttonsizex, (j+1)*buttonsizey);
				gl.glVertex2f(startx+(i+1)*buttonsizex, (j+1)*buttonsizey);
				gl.glVertex2f(startx+(i+1)*buttonsizex, (j)*buttonsizey);
				gl.glVertex2f(startx+i*buttonsizex, (j)*buttonsizey);
				gl.glEnd();
			}
		}
		
		//Drawing the GRID on top of everything!
		//set the LineWidth and the line color
		int size = 3;
		gl.glLineWidth(size);
		gl.glColor3f(0.0f, 0.0f, 0.0f);
		
		//vertical lines of the grid
		for(int i = 0; i <= x; i++){
			gl.glBegin(GL.GL_LINES);
			gl.glVertex2f(startx+i*buttonsizex, 0);
			gl.glVertex2f(startx+i*buttonsizex, height);
			gl.glEnd();
		}
		
		//horizontal lines of the grid
		for(int i = 0; i <= y; i++){
			gl.glBegin(GL.GL_LINES);
			gl.glVertex2f(startx, i*buttonsizey);
			gl.glVertex2f(startx+width, i*buttonsizey);
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	

}
