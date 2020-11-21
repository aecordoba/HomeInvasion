/*
 * 		Barrier.java
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
 * 		Barrier.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Nov 21, 2020
 */
package com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes;

import java.awt.Color;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class Barrier extends Structure {
	private static final Color color = Color.RED;
	private static final int WIDTH = 30;
	private static final int HEIGHT = 160;
	
	public Barrier(int x1, int y1) {
		super(x1, y1, x1 + WIDTH, y1 + HEIGHT, color);
	}

}
