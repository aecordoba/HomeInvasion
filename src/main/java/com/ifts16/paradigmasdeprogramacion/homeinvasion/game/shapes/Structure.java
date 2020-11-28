package com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Structure extends Shape {
	private int x2Coordinate;
	private int y2Coordinate;
	private Color color;

	public Structure(int x1Coordinate, int y1Coordinate, int x2Coordinate, int y2Coordinate, Color color) {
		super(x1Coordinate, y1Coordinate);
		this.x2Coordinate = x2Coordinate;
		this.y2Coordinate = y2Coordinate;
		this.color = color;
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.fillRect(getXCoordinate(), getYCoordinate(), x2Coordinate - getXCoordinate(), y2Coordinate - getYCoordinate());
	}

	@Override
	public Rectangle getBounds() {
		Rectangle rectangle = new Rectangle(getXCoordinate(), getYCoordinate(), x2Coordinate - getXCoordinate(), y2Coordinate - getYCoordinate());
		return rectangle;
	}
}
