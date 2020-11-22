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

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class StatusDisplay implements Drawable {
	private static final int STATUS_DISPLAY_Y = 470;
	private static final int POWER_DISPLAY_X = 30;
	private static final int ANGLE_DISPLAY_X = 150;
	private static final int SCORE_DISPLAY_X = 700;
	private static final int INTEGRITY_DISPLAY_X = 500;
	private static final Color COLOR = Color.BLACK;
	private final Font FONT;
	private int power;
	private int angle;
	private int integrity;
	private int score;

	public StatusDisplay() {
		FONT = new Font("Dialog", Font.BOLD, 13);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ifts16.paradigmasdeprogramacion.homeinvasion.game.sprites.Shape#draw(
	 * java.awt.Graphics2D)
	 */
	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(COLOR);
		g2d.setFont(FONT);
		g2d.drawString("POTENCIA: " + power, POWER_DISPLAY_X, STATUS_DISPLAY_Y);
		g2d.drawString("ANGULO: " + angle, ANGLE_DISPLAY_X, STATUS_DISPLAY_Y);
		g2d.drawString("INTEGRIDAD: " + integrity + "%", INTEGRITY_DISPLAY_X, STATUS_DISPLAY_Y);
		g2d.drawString("PUNTAJE: " + score, SCORE_DISPLAY_X, STATUS_DISPLAY_Y);
	}

	/**
	 * @param power the power to set
	 */
	public void setPower(int power) {
		this.power = power;
	}

	/**
	 * @param angle the angle to set
	 */
	public void setAngle(int angle) {
		this.angle = angle;
	}

	/**
	 * @param integrity the integrity to set
	 */
	public void setIntegrity(int integrity) {
		this.integrity = integrity;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

}
