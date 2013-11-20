package mazerunner;

/**
 * TEMP AI CLASS
 * @author Marius
 *
 */
public class EnemyAI extends Control{
	
	private Enemy enemy;
	private Player player;
	double seperationX, seperationZ, seperationR;
	
	public EnemyAI(Enemy enemy, Player player) {
		forward = true;
		this.enemy = enemy;
		this.player = player;
	}
	
	@Override
	public void update() {
		seperationX = enemy.getLocationX() - player.getLocationX();
		seperationZ = enemy.getLocationZ() - player.getLocationZ();

		enemy.setHorAngle((180/Math.PI)*Math.atan2(seperationX, seperationZ));
		
		seperationR = Math.sqrt(Math.pow(seperationX, 2) + Math.pow(seperationZ, 2));
		if (seperationR < 0.5) {
			forward = false;
			System.out.println("loser");
			System.exit(0);
		}
	}
}
