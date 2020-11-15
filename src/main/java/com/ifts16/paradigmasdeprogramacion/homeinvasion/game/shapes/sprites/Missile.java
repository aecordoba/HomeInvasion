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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class Missile extends Sprite {

	/**
	 * @param x
	 * @param y
	 * @param dx
	 * @param dy
	 */
	public Missile(int x, int y) {
		super(x, y, 7, 5);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.ifts16.paradigmasdeprogramacion.homeinvasion.game.sprites.Sprite#move()
	 */
	@Override
	public void move() {
		setX((int)(getX() - getDx()));
		setY((int)(getY() + getDy()));
	}

	/* (non-Javadoc)
	 * @see com.ifts16.paradigmasdeprogramacion.homeinvasion.game.sprites.Sprite#draw(java.awt.Graphics2D)
	 */
	@Override
	public void draw(Graphics2D g2d) {
		int x = getX();
		int y = getY();
		g2d.setColor(Color.RED);
		g2d.drawLine(x, y, x - 2, y + 2);
	}
	
	public Rectangle getBounds() {
		Rectangle rectangle = new Rectangle(getX(), getY(), 2, 2);
		return rectangle;
	}
}
