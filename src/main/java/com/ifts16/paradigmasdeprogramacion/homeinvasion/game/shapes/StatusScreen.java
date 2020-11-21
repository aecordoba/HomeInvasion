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
	private String statusString;
	private String informationString;
	private String commandString;

	/**
	 * @param statusString
	 * @param informationString
	 * @param commandString
	 */
	public StatusScreen(String statusString, String informationString, String commandString) {
		super();
		this.statusString = statusString;
		this.informationString = informationString;
		this.commandString = commandString;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.Drawable#
	 * draw(java.awt.Graphics2D)
	 */
	@Override
	public void draw(Graphics2D g2d) {
		g2d.setBackground(Color.BLACK);
		g2d.setColor(Color.RED);
		Font font = new Font("Dialog", Font.BOLD, 30);
		g2d.setFont(font);
		FontMetrics metrics = g2d.getFontMetrics(font);
		int stringWidth = metrics.stringWidth(statusString);
		int stringHeight = metrics.getHeight();
		int x = (800 - stringWidth) / 2;
		int y = (500 - stringHeight) / 2;
		g2d.drawString(statusString, x, y);

		if (informationString != null) {
			g2d.setColor(Color.CYAN);
			font = new Font("Dialog", Font.PLAIN, 20);
			g2d.setFont(font);
			metrics = g2d.getFontMetrics(font);
			stringWidth = metrics.stringWidth(informationString);
			x = (800 - stringWidth) / 2;
			g2d.drawString(informationString, x, y + 50);
		}

		if (commandString != null) {
			g2d.setColor(Color.WHITE);
			font = new Font("Dialog", Font.PLAIN, 15);
			g2d.setFont(font);
			metrics = g2d.getFontMetrics(font);
			stringWidth = metrics.stringWidth(commandString);
			x = (800 - stringWidth) / 2;
			g2d.drawString(commandString, x, y + 100);
		}
	}

}
