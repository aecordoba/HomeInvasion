/*
 * 		Missile.java
 *   Copyright (C) 2020  Adrián E. Córdoba [software.asia@gmail.com]
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * 		Missile.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Nov 13, 2020
 */
package com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class Missile extends Sprite {
	private BasicStroke stroke;
	
	public Missile(int xCoordinate, int yCoordinate) {
		super(xCoordinate, yCoordinate, 7, 5);
		stroke = new BasicStroke(2);
	}

	@Override
	public void move() {
		setXCoordinate((int)(getXCoordinate() - getXShift()));
		setYCoordinate((int)(getYCoordinate() + getYShift()));
	}

	@Override
	public void draw(Graphics2D g2d) {
		int xCoordinate = getXCoordinate();
		int yCoordinate = getYCoordinate();
		g2d.setColor(Color.RED);
		g2d.setStroke(stroke);
		g2d.drawLine(xCoordinate, yCoordinate, xCoordinate - 2, yCoordinate + 2);
	}
	
	@Override
	public Rectangle getBounds() {
		Rectangle rectangle = new Rectangle(getXCoordinate(), getYCoordinate(), 2, 2);
		return rectangle;
	}
}
