/*
 * 		Score.java
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
 * 		Score.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Nov 14, 2020
 */
package com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class StatusDisplay extends Shape {
	private String text;
	private int magnitude;
	private final Color COLOR;
	private final Font FONT;

	/**
	 * @param x
	 * @param y
	 */
	public StatusDisplay(int x, int y, String text) {
		super(x, y);
		this.text = text;
		COLOR = Color.BLACK;
		FONT = new Font("Dialog", Font.BOLD, 13);
	}

	/* (non-Javadoc)
	 * @see com.ifts16.paradigmasdeprogramacion.homeinvasion.game.sprites.Shape#draw(java.awt.Graphics2D)
	 */
	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(COLOR);
		g2d.setFont(FONT);
		g2d.drawString(text + ": " + magnitude, getX(), getY());
	}

	public void setMagnitude(int magnitude) {
		this.magnitude = magnitude;
	}
	
	public Rectangle getBounds() {
		Rectangle rectangle = new Rectangle(getX(), getY(), 0, 0);
		return rectangle;
	}
}
