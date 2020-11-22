/*
 * 		Jet.java
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
 * 		Jet.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Nov 13, 2020
 */
package com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class Jet extends Sprite {
	private static final int VELOCITY = 5;
	private static final int MAX_ALTITUDE = 50;
	private static final int MIN_ALTITUDE = 200;
	private List<Missile> missilesList;

	public Jet() {
		super((int) ((Math.random() * 300) + 500), (int) ((Math.random() * (MIN_ALTITUDE - MAX_ALTITUDE)) + MAX_ALTITUDE), VELOCITY, 0);
		missilesList = new ArrayList<Missile>();
	}

	@Override
	public void move() {
		setX((int) (getX() - getDx()));
		for (int i = 0; i < 30; i++) {
			if (getX() == (int) (Math.random() * 800)) {
				addMissile();
			}
		}
		if (getX() < 800) {
			for (Missile missile : missilesList) {
				missile.move();
			}
		}
		if (getX() <= 0) {
			this.setX(800);
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		int x = getX();
		int y = getY();
		g2d.setColor(Color.GRAY);
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 3);
		path.moveTo(x, y);
		path.lineTo(x + 50, y);
		path.lineTo(x + 57, y - 15);
		path.lineTo(x + 45, y - 5);
		path.closePath();
		g2d.fill(path);
		for (Missile missile : missilesList) {
			missile.draw(g2d);
		}
	}

	@Override
	public Rectangle getBounds() {
		Rectangle rectangle = new Rectangle(getX(), getY(), 57, 15);
		return rectangle;
	}

	public void reset() {
		setX(800);
		setY((int) ((Math.random() * (MIN_ALTITUDE - MAX_ALTITUDE)) + MAX_ALTITUDE));
	}

	private void addMissile() {
		missilesList.add(new Missile(getX(), getY()));
	}

	public List<Missile> getMissilesList() {
		return missilesList;
	}
}
