/*
 * 		Cannonball.java
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
 * 		Cannonball.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Nov 10, 2020
 */
package com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class Cannonball extends Sprite {
	private static final int DIAMETER = 3;
	private Color color;
	private double gravity = 0.1;

	public Cannonball(int x, int y, int velocity, double tilt) {
		super(x, y, velocity * (Math.cos(Math.toRadians(tilt))), -velocity * (Math.sin(Math.toRadians(tilt))));
		color = new Color(0, 51, 0);
	}

	@Override
	public void move() {
		setXCoordinate((int)(getXCoordinate() + getXShift()));
		setYCoordinate((int)(getYCoordinate() + getYShift() + gravity));
		gravity += 0.05;
	}

	@Override
	public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval(getXCoordinate(), getYCoordinate(), DIAMETER, DIAMETER);
	}

	@Override
	public Rectangle getBounds() {
		Rectangle rectangle = new Rectangle(getXCoordinate(), getYCoordinate(), DIAMETER, DIAMETER);
		return rectangle;
	}
}
