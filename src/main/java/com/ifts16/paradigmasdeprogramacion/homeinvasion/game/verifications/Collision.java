package com.ifts16.paradigmasdeprogramacion.homeinvasion.game.verifications;

import java.awt.Rectangle;
import java.util.List;

import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.Building;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.Structure;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites.Cannonball;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites.Jet;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites.Missile;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites.Tank;

public class Collision {
	private Tank tank;

	public Collision(Tank tank) {
		this.tank = tank;
	}

	public boolean verifyCollisionCannonballJet(Jet jet) {
		List<Cannonball> cannonballList = tank.getCannonballsList();
		boolean collision = false;
		for (Cannonball cannonball : cannonballList) {
			collision = isCollision(cannonball.getBounds(), jet.getBounds());
			if (collision) {
				tank.remove(cannonball);
				break;
			}
		}
		return collision;
	}

	public boolean verifyCollisionCannonballTank() {
		List<Cannonball> cannonballList = tank.getCannonballsList();
		boolean collision = false;
		for (Cannonball cannonball : cannonballList) {
			collision = isCollision(cannonball.getBounds(), tank.getBounds());
			if (collision) {
				tank.remove(cannonball);
				break;
			}
		}
		return collision;
	}

	public boolean verifyCollisionCannonballBuilding(Building building) {
		List<Cannonball> cannonballList = tank.getCannonballsList();
		boolean collision = false;
		for (Cannonball cannonball : cannonballList) {
			collision = isCollision(cannonball.getBounds(), building.getBounds());
			if (collision) {
				tank.remove(cannonball);
				break;
			}
		}
		return collision;
	}

	public void veryfyCollisionCannonballStructure(Structure structure) {
		List<Cannonball> cannonballList = tank.getCannonballsList();
		for (Cannonball cannonball : cannonballList) {
			if (isCollision(cannonball.getBounds(), structure.getBounds())) {
				tank.remove(cannonball);
				break;
			}
		}
	}

	public boolean verifyCollisionMissileTank(Jet jet) {
		List<Missile> missilesList = jet.getMissilesList();
		boolean collision = false;
		for (Missile missile : missilesList) {
			collision = isCollision(missile.getBounds(), tank.getBounds());
			if (collision)
				break;
		}
		return collision;
	}

	private boolean isCollision(Rectangle rectangle1, Rectangle rectangle2) {
		boolean collision = false;
		if (isSuperimposed((int) rectangle1.getX(), (int) (rectangle1.getX() + rectangle1.getWidth()), (int) rectangle2.getX(), (int) (rectangle2.getX() + rectangle2.getWidth())) && isSuperimposed((int) rectangle1.getY(), (int) (rectangle1.getY() + rectangle1.getHeight()), (int) rectangle2.getY(), (int) (rectangle2.getY() + rectangle2.getHeight())))
			collision = true;
		return collision;
	}

	private boolean isSuperimposed(int sprite11, int sprite12, int sprite21, int sprite22) {
		boolean overlap = false;
		if (sprite11 < sprite22 && sprite12 > sprite21) {
			overlap = true;
		}
		return overlap;
	}
}
