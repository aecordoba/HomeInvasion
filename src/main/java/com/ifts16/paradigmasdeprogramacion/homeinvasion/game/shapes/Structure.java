package com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Structure extends Shape {
	private int x2;
	private int y2;
	private Color color;

	public Structure(int x1, int y1, int x2, int y2, Color color) {
		super(x1, y1);
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.fillRect(getX(), getY(), x2 - getX(), y2 - getY());
	}

	@Override
	public Rectangle getBounds() {
		Rectangle rectangle = new Rectangle(getX(), getY(), x2 - getX(), y2 - getY());
		return rectangle;
	}
}
