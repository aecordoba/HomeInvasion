package com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Structure extends Shape{
	private Color color;
	
	public Structure(int x, int y, Color color) {
		super(x, y);
		this.color = color;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.fillRect(getX(), getY(), 30, 160);
		
	}
	
	public Rectangle getBounds() {
		Rectangle rectangle = new Rectangle(getX(), getY(), 30, 160);
		return rectangle;
	}
}
