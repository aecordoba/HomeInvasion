/*
 * 		Building.java
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
 * 		Building.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Nov 21, 2020
 */
package com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class Building extends Structure {
	private static final Color color = Color.BLUE;
	private static final int WIDTH = 30;
	private static final int HEIGHT = 130;
	private boolean visible;
	
	public Building(int x1, int y1) {
		super(x1, y1, x1 + WIDTH, y1 + HEIGHT, color);
		visible = true;
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		if(visible)
			super.draw(g2d);
	}
	
	@Override
	public Rectangle getBounds() {
		Rectangle rectangle = null;
		if(visible)
			rectangle = super.getBounds();
		else
			rectangle = new Rectangle(0, 0, 0, 0);
		return rectangle;
		
	}
	
	public void destroy() {
		visible = false;
	}
}
