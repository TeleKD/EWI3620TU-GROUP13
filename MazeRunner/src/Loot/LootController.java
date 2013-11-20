package Loot;

import java.util.ArrayList;
import java.util.Iterator;

import javax.media.opengl.GL;

import mazerunner.Maze;
import mazerunner.VisibleObject;

public class LootController implements VisibleObject {
	private static ArrayList<Loot> list;
	
	public LootController(){
		list = new ArrayList<Loot>();
		//list.add(new Food(2 * Maze.SQUARE_SIZE + Maze.SQUARE_SIZE / 2,Maze.SQUARE_SIZE / 2,4 * Maze.SQUARE_SIZE + Maze.SQUARE_SIZE / 2,50,true));
		list.add(new Food(8 * Maze.SQUARE_SIZE + Maze.SQUARE_SIZE / 2,Maze.SQUARE_SIZE / 2,4 * Maze.SQUARE_SIZE + Maze.SQUARE_SIZE / 2,50,true));
	}
	
	private boolean near(double x1,double z1,double x2,double z2){
		double seperation = Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(z2-z1, 2));
		if(seperation < 0.5)
			return true;
		return false;
	}
	
	public ArrayList<Loot> getList(){return list;}
	
	public void update(int deltaTime,double x,double z){
		Iterator<Loot> it = list.listIterator();
		
		while(it.hasNext()){
			Loot temp = it.next();
			if(temp instanceof Food){
				Food temp2 = (Food) temp;
				if(near(x,z,temp2.getX(),temp2.getZ()) /*&& player.getLocationZ() == temp2.getZ()*/){
//***************************************************************************************************************************************
//***************************************************************************************************************************************
					//player.addHP(temp2.giveHP()); // dit werkt waarschijnlijk niet (goed)
					it.remove();
				}
			}
		}
	}
	
	public void display(GL gl){
		for(Iterator<Loot> it = list.iterator();it.hasNext();)
			it.next().display(gl);
	}
	
	
}
