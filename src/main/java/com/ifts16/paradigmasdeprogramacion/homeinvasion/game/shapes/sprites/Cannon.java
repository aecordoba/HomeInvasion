package com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Cannon extends Sprite {
	private int x2;
	private int y2;

	public Cannon(int x, int y, int x2, int y2, double dx, double dy) {
		super(x, y, dx, dy);
		this.x2 = x2;
		this.y2 = y2;

	}

	@Override
	public void move() {
		setX2((int) (getX2() + getDx()));
		setY2((int) (getY2() + getDy()));

	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		g2d.drawLine(getX(), getY(), getX2(), getY2());
	}

	public int getY2() {
		return this.y2;
	}

	public int getX2() {
		return this.x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}
	
	public Rectangle getBounds() {
		Rectangle rectangle = new Rectangle(getX(), getY(), 1, 30);
		return rectangle;
	}
}
