package com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.*;

public class Tank extends Sprite {
	private final int LENGTH = 30;
	private final int HEIGHT = 7;
	private final int CANNON_LENGTH = 25;
	private int angle;
	private int angleIncrement;
	private int power;
	private List<Cannonball> ballList;

	public Tank(int x, int y) {
		super(x, y, 0, 0);
		angle = 0;
		power = 1;
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
		g2d.setColor(Color.WHITE);
		g2d.fillOval(getX(), getY(), LENGTH, HEIGHT);
		int tankCentre = getX() + LENGTH / 2;
		g2d.drawLine(tankCentre, getY(), (int) (tankCentre + CANNON_LENGTH * (Math.cos(Math.toRadians(angle)))), (int) (getY() - CANNON_LENGTH * (Math.sin(Math.toRadians(angle)))));
		if (!ballList.isEmpty()) {
			for (Cannonball cannonball : ballList) {
				cannonball.draw(g2d);
			}
		}
	}

	public void modifyAngle(int step) {
		angleIncrement = step;
	}

	public void fire() {
		ballList.add(new Cannonball((int) (getX() + LENGTH / 2 + CANNON_LENGTH * (Math.cos(Math.toRadians(angle)))), (int) (getY() - CANNON_LENGTH * (Math.sin(Math.toRadians(angle)))), power + 4, angle));
	}

	public ArrayList<Cannonball> returnList() {
		ArrayList<Cannonball> cannonballs = new ArrayList<Cannonball>();
		for (Cannonball cannonball : ballList) {
			cannonballs.add(cannonball);
		}
		return cannonballs;
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

	public void remove(Cannonball cannonball) {
		ballList.remove(cannonball);
	}
	
	public Rectangle getBounds() {
		Rectangle rectangle = new Rectangle(getX(), getY(), 30, 7);
		return rectangle;
	}
}