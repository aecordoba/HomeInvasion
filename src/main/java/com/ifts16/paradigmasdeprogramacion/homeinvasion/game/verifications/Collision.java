package com.ifts16.paradigmasdeprogramacion.homeinvasion.game.verifications;

import java.awt.Rectangle;
import java.util.ArrayList;

import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.Structure;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites.Cannonball;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites.Jet;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites.Missile;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites.Tank;

public class Collision {

	public boolean verifyCollisionCannonballJet(Tank tank, Jet jet) {
		ArrayList<Cannonball> cannonList = tank.returnList();
		boolean collision = false;
		for(Cannonball cannonball: cannonList) {
			Rectangle rectangle1 = cannonball.getBounds();
			Rectangle rectangle2 = jet.getBounds();
			if(isCollision((int)rectangle1.getX(), (int)(rectangle1.getX() + rectangle1.getWidth()), (int)rectangle2.getX(), (int)(rectangle2.getX() + rectangle2.getWidth()))&& isCollision((int)rectangle1.getY(), (int)(rectangle1.getY()+rectangle1.getHeight()), (int)rectangle2.getY(), (int)(rectangle2.getY()+rectangle2.getHeight()))) {
				collision = true;
			}
		}
		
		return collision;
		
	}
			public boolean verifyCollisionCannonballTank(Tank tank) {
				ArrayList<Cannonball> cannonList = tank.returnList();
				boolean collision = false;
				for(Cannonball cannonball: cannonList) {
					Rectangle rectangle1 = cannonball.getBounds();
					Rectangle rectangle2 = tank.getBounds();
					if(isCollision((int)rectangle1.getX(), (int)(rectangle1.getX() + rectangle1.getWidth()), (int)rectangle2.getX(), (int)(rectangle2.getX() + rectangle2.getWidth()))&& isCollision((int)rectangle1.getY(), (int)(rectangle1.getY()+rectangle1.getHeight()), (int)rectangle2.getY(), (int)(rectangle2.getY()+rectangle2.getHeight()))) {
						collision = true;
					}
				}
				return collision;
			}
			
			public void verifyCannonballStatus(Tank tank) {
				ArrayList<Cannonball> cannonList = tank.returnList();
				for(Cannonball cannonball: cannonList) {
					if(cannonball.getX() > 800) {
						tank.remove(cannonball);
					}
				}
			}
			public boolean verifyCollisionCannonballBuilding(Tank tank, Structure structure) {
				ArrayList<Cannonball> cannonList = tank.returnList();
				boolean collision = false;
				for(Cannonball cannonball: cannonList) {
					Rectangle rectangle1 = cannonball.getBounds();
					Rectangle rectangle2 = structure.getBounds();
					if(isCollision((int)rectangle1.getX(), (int)(rectangle1.getX() + rectangle1.getWidth()), (int)rectangle2.getX(), (int)(rectangle2.getX() + rectangle2.getWidth()))&& isCollision((int)rectangle1.getY(), (int)(rectangle1.getY()+rectangle1.getHeight()), (int)rectangle2.getY(), (int)(rectangle2.getY()+rectangle2.getHeight()))) {
						collision = true;
					}
				}
				
				
				return collision;
			}
			
			public void veryfyCollisionCannonballWall(Tank tank, Structure barrier) {
				ArrayList<Cannonball> cannonList = tank.returnList();
				
				for(Cannonball cannonball: cannonList) {
					Rectangle rectangle1 = cannonball.getBounds();
					Rectangle rectangle2 = barrier.getBounds();
					if(isCollision((int)rectangle1.getX(), (int)(rectangle1.getX() + rectangle1.getWidth()), (int)rectangle2.getX(), (int)(rectangle2.getX() + rectangle2.getWidth()))&& isCollision((int)rectangle1.getY(), (int)(rectangle1.getY()+rectangle1.getHeight()), (int)rectangle2.getY(), (int)(rectangle2.getY()+rectangle2.getHeight()))) {
						tank.remove(cannonball);
					}
				}
			}
			
			public boolean verifyCollisionMissileTank(Jet jet, Tank tank) {
				ArrayList<Missile> missileList = jet.returnList();
				boolean collision = false;
				for(Missile missile: missileList) {
					Rectangle rectangle1 = missile.getBounds();
					Rectangle rectangle2 = tank.getBounds();
					if(isCollision((int)rectangle1.getX(), (int)(rectangle1.getX() + rectangle1.getWidth()), (int)rectangle2.getX(), (int)(rectangle2.getX() + rectangle2.getWidth()))&& isCollision((int)rectangle1.getY(), (int)(rectangle1.getY()+rectangle1.getHeight()), (int)rectangle2.getY(), (int)(rectangle2.getY()+rectangle2.getHeight()))) {
						collision = true;
					}
				}
				
				
				return collision;
			
			}
	
	private boolean isCollision(int sprite11, int sprite12, int sprite21, int sprite22) {
		boolean hit = false;
		if(sprite11 < sprite22 && sprite12 > sprite21) {
			hit = true;
		}
		// TODO
		return hit;
	}

	
}
