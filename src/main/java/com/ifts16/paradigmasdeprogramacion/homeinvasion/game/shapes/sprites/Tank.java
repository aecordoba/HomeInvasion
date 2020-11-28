package com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.*;

public class Tank extends Sprite {
	private static final int TANK_VELOCITY = 2;
	private static final int TILT_STEP = 5;
	private static final int LENGTH = 30;
	private static final int HEIGHT = 7;
	private static final int CANNON_LENGTH = 25;
	private int gap;
	private Color color;
	private int integrity;
	private int angle;
	private int angleIncrement;
	private int power;
	private List<Cannonball> ballList;

	public Tank(int xCoordinate, int yCoordinate, int range) {
		super(xCoordinate, yCoordinate, 0, 0);
		integrity = 100;
		angle = 0;
		power = 1;
		this.gap = range - (LENGTH / 2 + CANNON_LENGTH);
		color = new Color(51, 102, 0);
		ballList = new ArrayList<>();
	}

	@Override
	public void move() {
		setXCoordinate((int) (getXCoordinate() + getXShift()));
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
		g2d.fillOval(getXCoordinate(), getYCoordinate(), LENGTH, HEIGHT);
		int tankCentre = getXCoordinate() + LENGTH / 2;
		g2d.drawLine(tankCentre, getYCoordinate(), (int) (tankCentre + CANNON_LENGTH * (Math.cos(Math.toRadians(angle)))), (int) (getYCoordinate() - CANNON_LENGTH * (Math.sin(Math.toRadians(angle)))));
		if (!ballList.isEmpty()) {
			for (Cannonball cannonball : ballList) {
				cannonball.draw(g2d);
			}
		}
	}

	@Override
	public Rectangle getBounds() {
		Rectangle rectangle = new Rectangle(getXCoordinate(), getYCoordinate(), LENGTH, HEIGHT);
		return rectangle;
	}

	public void advance() {
		if (getXCoordinate() < 500)
			setXShift(TANK_VELOCITY);
		else {
			setXShift(0);
			setXCoordinate(gap);
		}
	}

	public void goBack() {
		if (getXCoordinate() > 0)
			setXShift(-TANK_VELOCITY);
		else {
			setXShift(0);
			setXCoordinate(0);
		}
	}

	public void stop() {
		setXShift(0);
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
		checkIntegrityAndPower();
	}

	private void checkIntegrityAndPower() {
		if (integrity < 75) {
			color = new Color(0, 26, 26);
			if (power > 3)
				power = 3;
		} else if (integrity >= 75 && integrity < 100) {
			color = new Color(0, 51, 51);
			if (power > 4)
				power = 4;
		}
	}

	public void fire() {
		ballList.add(new Cannonball((int) (getXCoordinate() + LENGTH / 2 + CANNON_LENGTH * (Math.cos(Math.toRadians(angle)))), (int) (getYCoordinate() - CANNON_LENGTH * (Math.sin(Math.toRadians(angle)))), power + 4, angle));
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
		checkIntegrityAndPower();
	}
}