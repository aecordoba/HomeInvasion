/*
 * 		StatusScreen.java
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
 * 		StatusScreen.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Nov 15, 2020
 */
package com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class StatusScreen implements Drawable {
	private int width;
	private int height;
	private String statusString;
	private String informationString;
	private String commandString;

	public StatusScreen(int width, int height, String statusString, String informationString, String commandString) {
		super();
		this.width = width;
		this.height = height;
		this.statusString = statusString;
		this.informationString = informationString;
		this.commandString = commandString;
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setBackground(Color.BLACK);
		setText(g2d, statusString, new Font("Dialog", Font.BOLD, 30), Color.RED, height / 3);
		if (informationString != null)
			setText(g2d, informationString, new Font("Dialog", Font.PLAIN, 20), Color.CYAN, height / 2);
		if (commandString != null)
			setText(g2d, commandString, new Font("Dialog", Font.PLAIN, 15), Color.WHITE, 2 * height / 3);
	}
	
	private void setText(Graphics2D g2d, String text, Font font, Color color, int yCoordinate) {
		g2d.setFont(font);
		g2d.setColor(color);
		FontMetrics metrics = g2d.getFontMetrics(font);
		int stringWidth = metrics.stringWidth(text);
		int xCoordinate = (width - stringWidth) / 2;
		g2d.drawString(text, xCoordinate, yCoordinate);
	}
}
