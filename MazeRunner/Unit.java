package mazerunner;

public class Unit extends GameObject {
	protected double hp;
	
	public Unit( double x, double y, double z,double hp){
		super(x,y,z);
		this.hp = hp;
	}
	
	/**
	 * Gives current the HP
	 * @return the current hp
	 */
	public double getHP(){return hp;}
	/**
	 * Adds the HP
	 * @param add the amount to add
	 */
	public void addHP(double add){
		if(add > 0)
			hp += add;
	}
	/**
	 * Remove HP
	 * @param remove amount to remove
	 */
	public void removeHP(double remove){
		if(remove > 0)
			hp -= remove;
	}
	
	
}
