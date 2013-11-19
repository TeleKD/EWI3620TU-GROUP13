package Loot;

public abstract class Weapon extends Loot {
	protected float damage;
	
	
	public Weapon(float x, float y, float damage){
		super(x,y);
		this.damage = damage;
	}
	
	public float getDamage(){return damage;}
	
	/**
	 * To which enemy has the damage to be dealt
	 * @param enemy the Enemy that loses HP
	 */
	public abstract doDamage(Enemy enemy);
	
	
}
