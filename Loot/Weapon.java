package Loot;

public abstract class Weapon extends Loot {
	protected float damage;
	boolean equipped;
	
	
	public Weapon(float x, float y, float damage){
		super(x,y);
		this.damage = damage;
	}
	
	public float doDamage(){return damage;}
	
	public boolean getEquipped(){return equipped;}
	public void setEquipped(boolean set){
		equipped = set;
	}
	
}
