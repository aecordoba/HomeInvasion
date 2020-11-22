package com.ifts16.paradigmasdeprogramacion.homeinvasion.game.verifications;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

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
		ArrayList<Cannonball> cannonList = tank.getCannonballsList();
		boolean collision = false;
		for (Cannonball cannonball : cannonList) {
			Rectangle rectangle1 = cannonball.getBounds();
			Rectangle rectangle2 = jet.getBounds();
			if (isCollision((int) rectangle1.getX(), (int) (rectangle1.getX() + rectangle1.getWidth()), (int) rectangle2.getX(), (int) (rectangle2.getX() + rectangle2.getWidth())) && isCollision((int) rectangle1.getY(), (int) (rectangle1.getY() + rectangle1.getHeight()), (int) rectangle2.getY(), (int) (rectangle2.getY() + rectangle2.getHeight()))) {
				collision = true;
				tank.remove(cannonball);
				
			}
		}
		return collision;
	}

	public boolean verifyCollisionCannonballTank() {
		ArrayList<Cannonball> cannonList = tank.getCannonballsList();
		boolean collision = false;
		for (Cannonball cannonball : cannonList) {
			Rectangle rectangle1 = cannonball.getBounds();
			Rectangle rectangle2 = tank.getBounds();
			if (isCollision((int) rectangle1.getX(), (int) (rectangle1.getX() + rectangle1.getWidth()), (int) rectangle2.getX(), (int) (rectangle2.getX() + rectangle2.getWidth())) && isCollision((int) rectangle1.getY(), (int) (rectangle1.getY() + rectangle1.getHeight()), (int) rectangle2.getY(), (int) (rectangle2.getY() + rectangle2.getHeight()))) {
				collision = true;
				tank.remove(cannonball);
			}
		}
		return collision;
	}

	public boolean verifyCollisionCannonballBuilding(Structure structure) {
		ArrayList<Cannonball> cannonList = tank.getCannonballsList();
		boolean collision = false;
		for (Cannonball cannonball : cannonList) {
			Rectangle rectangle1 = cannonball.getBounds();
			Rectangle rectangle2 = structure.getBounds();
			if (isCollision((int) rectangle1.getX(), (int) (rectangle1.getX() + rectangle1.getWidth()), (int) rectangle2.getX(), (int) (rectangle2.getX() + rectangle2.getWidth())) && isCollision((int) rectangle1.getY(), (int) (rectangle1.getY() + rectangle1.getHeight()), (int) rectangle2.getY(), (int) (rectangle2.getY() + rectangle2.getHeight()))) {
				collision = true;
				tank.remove(cannonball);
			}
		}
		return collision;
	}

	public void veryfyCollisionCannonballStructure(Structure structure) {
		ArrayList<Cannonball> cannonList = tank.getCannonballsList();
		for (Cannonball cannonball : cannonList) {
			Rectangle rectangle1 = cannonball.getBounds();
			Rectangle rectangle2 = structure.getBounds();
			if (isCollision((int) rectangle1.getX(), (int) (rectangle1.getX() + rectangle1.getWidth()), (int) rectangle2.getX(), (int) (rectangle2.getX() + rectangle2.getWidth())) && isCollision((int) rectangle1.getY(), (int) (rectangle1.getY() + rectangle1.getHeight()), (int) rectangle2.getY(), (int) (rectangle2.getY() + rectangle2.getHeight()))) {
				tank.remove(cannonball);
			}
		}
	}

	public boolean verifyCollisionMissileTank(Jet jet) {
		List<Missile> missileList = jet.getMissilesList();
		boolean collision = false;
		for (Missile missile : missileList) {
			Rectangle rectangle1 = missile.getBounds();
			Rectangle rectangle2 = tank.getBounds();
			if (isCollision((int) rectangle1.getX(), (int) (rectangle1.getX() + rectangle1.getWidth()), (int) rectangle2.getX(), (int) (rectangle2.getX() + rectangle2.getWidth())) && isCollision((int) rectangle1.getY(), (int) (rectangle1.getY() + rectangle1.getHeight()), (int) rectangle2.getY(), (int) (rectangle2.getY() + rectangle2.getHeight()))) {
				collision = true;
			}
		}
		return collision;
	}

	private boolean isCollision(int sprite11, int sprite12, int sprite21, int sprite22) {
		boolean hit = false;
		if (sprite11 < sprite22 && sprite12 > sprite21) {
			hit = true;
		}
		return hit;
	}
}
