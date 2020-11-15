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

	/**
	 * @param x
	 * @param y
	 * @param dx
	 * @param dy
	 */
	private List<Missile> missiles;
	
	public Jet(int x, int y, double dx) {
		super(x, y, dx, 0);
		missiles = new ArrayList<Missile>();
	}

	/* (non-Javadoc)
	 * @see com.ifts16.paradigmasdeprogramacion.homeinvasion.game.sprites.Sprite#move()
	 */
	@Override
	public void move() {
		setX((int)(getX() - getDx()));
		
		for(int i = 0; i<40; i++) {
		 if(getX() == (int)(Math.random()*800)) {
			  addMissile();
		 }
		}
		 
		if(getX()<800) {
			
		    
		    for(Missile missile: missiles) {
			missile.move();
		    }
		}
		
		if(getX()==0) {
			this.setX(800);
		}
	}

	/* (non-Javadoc)
	 * @see com.ifts16.paradigmasdeprogramacion.homeinvasion.game.sprites.Sprite#draw(java.awt.Graphics2D)
	 */
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
		
		for(Missile missile: missiles) {
		missile.draw(g2d);
		}
	}
	
	public void addMissile() {
		missiles.add(new Missile(getX(), getY()));
	}

	public ArrayList<Missile> returnList(){
	    ArrayList<Missile> missilesList = new ArrayList<Missile>();
	    for(Missile missile: missiles) {
	    	missilesList.add(missile);
	    }
		
		return missilesList;
	}
	
	public Rectangle getBounds() {
		Rectangle rectangle = new Rectangle( getX(), getY(), 50, 15);
		return rectangle;
	}
}
