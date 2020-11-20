package com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.*;

public class Tank extends Sprite {
	public static final int TANK_VELOCITY = 2;
	public static final int TILT_STEP = 5;
	private final int LENGTH = 30;
	private final int HEIGHT = 7;
	private final int CANNON_LENGTH = 25;
	private final int TOTAL_LENGTH = LENGTH / 2 + CANNON_LENGTH;
	private int gap;
	private Color color;
	private int integrity;
	private int angle;
	private int angleIncrement;
	private int power;
	private List<Cannonball> ballList;

	public Tank(int x, int y, int range) {
		super(x, y, 0, 0);
		integrity = 100;
		angle = 0;
		power = 1;
		this.gap = range - TOTAL_LENGTH;
		color = new Color(0, 102, 102);
		ballList = new ArrayList<>();
	}

	@Override
	public void move() {
		setX((int) (getX() + getDx()));
		angle += angleIncrement;
		if (angle < 0)
			angle = 0;
		if (angle > 90)
			angle = 90;
		if (!ballList.isEmpty()) {
			for (Cannonball cannonball : ballList) {
				cannonball.move();
			}
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.fillOval(getX(), getY(), LENGTH, HEIGHT);
		int tankCentre = getX() + LENGTH / 2;
		g2d.drawLine(tankCentre, getY(), (int) (tankCentre + CANNON_LENGTH * (Math.cos(Math.toRadians(angle)))), (int) (getY() - CANNON_LENGTH * (Math.sin(Math.toRadians(angle)))));
		if (!ballList.isEmpty()) {
			for (Cannonball cannonball : ballList) {
				cannonball.draw(g2d);
			}
		}
	}

	@Override
	public Rectangle getBounds() {
		Rectangle rectangle = new Rectangle(getX(), getY(), 30, 7);
		return rectangle;
	}

	public void advance() {
		if (getX() < 500)
			setDx(TANK_VELOCITY);
		else {
			setDx(0);
			setX(gap);
		}
	}

	public void goBack() {
		if (getX() > 0)
			setDx(-TANK_VELOCITY);
		else {
			setDx(0);
			setX(0);
		}
	}

	public void stop() {
		setDx(0);
	}

	public void increaseTilt() {
		angleIncrement = TILT_STEP;
	}

	public void decreaseTilt() {
		angleIncrement = -TILT_STEP;
	}

	public void stopTilt() {
		angleIncrement = 0;
	}

	public void decreaseIntegrity() {
		integrity -= 25;
	}

	public void fire() {
		ballList.add(new Cannonball((int) (getX() + TOTAL_LENGTH * (Math.cos(Math.toRadians(angle)))), (int) (getY() - CANNON_LENGTH * (Math.sin(Math.toRadians(angle)))), power + 4, angle));
	}

	public ArrayList<Cannonball> getCannonballsList() {
		ArrayList<Cannonball> cannonballsList = new ArrayList<Cannonball>();
		for (Cannonball cannonball : ballList) {
			cannonballsList.add(cannonball);
		}
		return cannonballsList;
	}

	public void remove(Cannonball cannonball) {
		ballList.remove(cannonball);
	}

	public void resetIntegrity() {
		integrity = 100;
	}

	/**
	 * @return the integrity
	 */
	public int getIntegrity() {
		return integrity;
	}

	public int getAngle() {
		return angle;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}
}