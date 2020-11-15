/*
 * 		GamePanel.java
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
 * 		GamePanel.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Nov 10, 2020
 */
package com.ifts16.paradigmasdeprogramacion.homeinvasion.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.Shape;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.StatusDisplay;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.Structure;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites.Jet;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites.Sprite;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites.Tank;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.verifications.Collision;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class GamePanel extends JPanel implements KeyListener, Runnable {
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	private Tank tank;
	private int score;
	private final int TANK_VELOCITY = 2;
	private final int TILT_STEP = 5;
	private final int STATUS_DISPLAY_Y = 470;
	private final int POWER_DISPLAY_X = 30;
	private final int ANGLE_DISPLAY_X = 150;
	private final int SCORE_DISPLAY_X = 700;
	private List<Sprite> spritesList;
	private List<Shape> shapesList;
	private StatusDisplay powerDisplay;
	private StatusDisplay angleDisplay;
	private StatusDisplay scoreDisplay;
	private Structure building1;
	private Structure building2;
	private Structure building3;
	private Structure barrier;
	private Jet jet1;
	private Jet jet2;
	private Collision collision;

	public GamePanel(int width, int height) {
		this.width = width;
		this.height = height;
		spritesList = new ArrayList<>();
		shapesList = new ArrayList<>();
		collision = new Collision();
		initComponents();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		cleanScreen(g2d);
		for (Sprite sprite : spritesList)
			sprite.draw(g2d);
		for (Shape shape : shapesList)
			shape.draw(g2d);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			updateScene();
			repaint();

			waitToMove(40);
		}
	}

	private void initComponents() {
		setBackground(Color.black);
		tank = new Tank(0, 443);
		spritesList.add(tank);
		jet1 = new Jet(800, 100, 5);
		jet2 = new Jet(500, 150, 5);
		spritesList.add(jet1);
		spritesList.add(jet2);

		powerDisplay = new StatusDisplay(POWER_DISPLAY_X, STATUS_DISPLAY_Y, "POTENCIA");
		angleDisplay = new StatusDisplay(ANGLE_DISPLAY_X, STATUS_DISPLAY_Y, "ANGULO");
		scoreDisplay = new StatusDisplay(SCORE_DISPLAY_X, STATUS_DISPLAY_Y, "PUNTAJE");
		building1 = new Structure(690, 290, Color.BLUE);
		building2 = new Structure(750, 290, Color.BLUE);
		building3 = new Structure(620, 290, Color.BLUE);
		barrier = new Structure(550, 290, Color.RED);
		shapesList.add(powerDisplay);
		shapesList.add(angleDisplay);
		shapesList.add(scoreDisplay);
		shapesList.add(building1);
		shapesList.add(building2);
		shapesList.add(building3);
		shapesList.add(barrier);
	}

	private void updateScene() {
		updateSpritesStatus();
		for (Sprite sprite : spritesList)
			sprite.move();
		updateDisplayStatus();
	}

	private void waitToMove(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void updateSpritesStatus() {
		
		if(collision.verifyCollisionCannonballJet(tank, jet1)) {
			spritesList.remove(jet1);
		}
		if(collision.verifyCollisionCannonballJet(tank, jet2)) {
			spritesList.remove(jet2);
		}
		if(collision.verifyCollisionCannonballTank(tank)) {
			score--;
		}
		if(collision.verifyCollisionCannonballBuilding(tank, building1)) {
			shapesList.remove(building1);
		}
		if(collision.verifyCollisionCannonballBuilding(tank, building2)) {
			shapesList.remove(building2);
		}
		if(collision.verifyCollisionCannonballBuilding(tank, building3)) {
			shapesList.remove(building3);
		}
		//verifyCollisionMissileTank();
		//verifyCannonballStatus();
		collision.veryfyCollisionCannonballWall(tank, barrier);
		if(collision.verifyCollisionMissileTank(jet1, tank) || collision.verifyCollisionMissileTank(jet2, tank)) {
			//gameOver;
			score--;
		}
		//veryfyCollisionCannonballWall();
	}
	
	private void updateDisplayStatus() {
		powerDisplay.setMagnitude(tank.getPower());
		angleDisplay.setMagnitude(tank.getAngle());
		scoreDisplay.setMagnitude(score);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int dx = 0;
		
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				if (tank.getX() < 500) {
					dx = TANK_VELOCITY;
				} else {
					dx = 0;
					tank.setX(500);
				}
				tank.setDx(dx);
				break;
			case KeyEvent.VK_LEFT:
				if (tank.getX() > 0) {
					dx = -TANK_VELOCITY;
				} else {
					dx = 0;
					tank.setX(0);
				}
				tank.setDx(dx);
				break;
			case KeyEvent.VK_UP:
				tank.modifyAngle(TILT_STEP);
				break;
			case KeyEvent.VK_DOWN:
				tank.modifyAngle(-TILT_STEP);
				break;
			case KeyEvent.VK_F1:
				tank.setPower(1);
				break;
			case KeyEvent.VK_F2:
				tank.setPower(2);
				break;
			case KeyEvent.VK_F3:
				tank.setPower(3);
				break;
			case KeyEvent.VK_F4:
				tank.setPower(4);
				break;
			case KeyEvent.VK_F5:
				tank.setPower(5);
				break;
			case KeyEvent.VK_SPACE:
				tank.fire();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
			tank.setDx(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
			tank.modifyAngle(0);
		}
	}

	public void cleanScreen(Graphics2D g2d) {
		g2d.setColor(Color.green);
		g2d.fillRect(0, 450, 800, 60);
	}
}
