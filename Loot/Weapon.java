package Loot;

import javax.media.opengl.GL;

public abstract class Weapon extends Loot {
	protected double damage;
	boolean equipped;
	
	
	public Weapon(double x, double y, double z, double damage){
		super(x,y,z);
		this.damage = damage;
	}
	
	public double doDamage(){return damage;}
	
	public boolean getEquipped(){return equipped;}
	public void setEquipped(boolean set){
		equipped = set;
	}
	
	public void display(GL gl){}
	
}
