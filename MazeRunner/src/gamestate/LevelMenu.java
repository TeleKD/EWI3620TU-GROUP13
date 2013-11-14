package gamestate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Levels is the class representing the level menu state of the game
 */
public final class LevelMenu {
		
	private final String levelFile = "level\\levels.txt";
	private String[] levels;
	private int numLevels; 
	
	/**
	 * Constructor reads the levels file specified by the levelFile string
	 */
	public LevelMenu() {
		// read the levels file
		readLevels();
	}
	
	/**
	 * read the levels file
	 */
	private void readLevels() {
		// open the levels file and scan the levels
		try {
			// set up a scanner
			Scanner sc = new Scanner(new File(levelFile));
			
			// get the number of levels in the file
			numLevels = sc.nextInt(); sc.nextLine();
			
			// scan the level names and store them in levels
			levels = new String[numLevels];
			for (int i=0; i<numLevels; i++) {
				levels[i] = sc.nextLine();
			}
			
			// close the scanner
			sc.close();
			} 
		catch (FileNotFoundException e) {e.printStackTrace();}
	}
	
//	private Maze loadLevel(String levelPath) {
//		return Maze.read(levelPath);
//	}
}
