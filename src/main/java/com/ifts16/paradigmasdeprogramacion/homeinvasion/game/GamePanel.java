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
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.Shape;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.StatusDisplay;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.StatusScreen;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.Structure;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites.Jet;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites.Sprite;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites.Tank;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.sounds.Sound;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.verifications.Collision;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class GamePanel extends JPanel implements KeyListener, Runnable {
	private static final long serialVersionUID = 1L;

	enum Status {
		PLAYING, LOST, GAME_OVER, WON
	};

	private Status currentStatus;
	private final int STATUS_DISPLAY_Y = 470;
	private final int POWER_DISPLAY_X = 30;
	private final int ANGLE_DISPLAY_X = 150;
	private final int SCORE_DISPLAY_X = 700;
	private final int INTEGRITY_DISPLAY_X = 500;
	private final int BARRIER_X = 550;

	private int width;
	private int height;
	private Tank tank;
	private int score;
	private List<Sprite> spritesList;
	private List<Shape> shapesList;
	private StatusDisplay powerDisplay;
	private StatusDisplay angleDisplay;
	private StatusDisplay scoreDisplay;
	private StatusDisplay integrityDisplay;
	private Structure building1;
	private Structure building2;
	private Structure building3;
	private Structure barrier;
	private Structure ground;
	private Jet jet1;
	private Jet jet2;
	private Collision collision;
	private Sound sounds;

	public GamePanel(int width, int height) {
		this.width = width;
		this.height = height;
		currentStatus = Status.PLAYING;
		spritesList = new ArrayList<>();
		shapesList = new ArrayList<>();
		initComponents();
		addSounds();
		sounds.playSound("music");
	}

	private void initComponents() {
		setBackground(Color.BLACK);
		tank = new Tank(0, 443, BARRIER_X);
		collision = new Collision(tank);
		spritesList.add(tank);
		jet1 = new Jet(800, 100, 5);
		jet2 = new Jet(500, 150, 5);
		spritesList.add(jet1);
		spritesList.add(jet2);
		powerDisplay = new StatusDisplay(POWER_DISPLAY_X, STATUS_DISPLAY_Y, "POTENCIA");
		angleDisplay = new StatusDisplay(ANGLE_DISPLAY_X, STATUS_DISPLAY_Y, "ANGULO");
		scoreDisplay = new StatusDisplay(SCORE_DISPLAY_X, STATUS_DISPLAY_Y, "PUNTAJE");
		integrityDisplay = new StatusDisplay(INTEGRITY_DISPLAY_X, STATUS_DISPLAY_Y, "INTEGRIDAD");
		building1 = new Structure(690, 290, Color.BLUE);
		building2 = new Structure(750, 290, Color.BLUE);
		building3 = new Structure(620, 290, Color.BLUE);
		barrier = new Structure(BARRIER_X, 290, Color.RED);
		ground = new Structure(0, 450, 800, 500, Color.GREEN);
		shapesList.add(ground);
		shapesList.add(powerDisplay);
		shapesList.add(angleDisplay);
		shapesList.add(scoreDisplay);
		shapesList.add(integrityDisplay);
		shapesList.add(building1);
		shapesList.add(building2);
		shapesList.add(building3);
		shapesList.add(barrier);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		if (currentStatus == Status.PLAYING) {
			GradientPaint redtowhite = new GradientPaint(0, 0, Color.BLUE, 0, 500, Color.WHITE);
			g2d.setPaint(redtowhite);
			g2d.fill(new Rectangle(0, 0, width, height));
			for (Sprite sprite : spritesList)
				sprite.draw(g2d);
			for (Shape shape : shapesList)
				shape.draw(g2d);
		} else if (currentStatus == Status.LOST) {
			new StatusScreen("TANQUE DETERIORADO").draw(g2d);
		} else if (currentStatus == Status.GAME_OVER) {
			new StatusScreen("JUEGO TERMINADO").draw(g2d);
		} else if (currentStatus == Status.WON) {
			new StatusScreen("USTED GANA").draw(g2d);
		}
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
			if (currentStatus == Status.PLAYING)
				updateScene();

			repaint();
			waitToMove(40);
		}
	}

	private void addSounds() {
		try {
			sounds = new Sound();
			sounds.addSound("bomb", "sounds/bomb.wav");
			sounds.addSound("music", "sounds/HeroicIntrusion.wav");
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
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
		if (collision.verifyCollisionCannonballJet(jet1)) {
			sounds.playSound("bomb");
			jet1.setX(800);
			jet1.setY(100);
			score += 5;
			if (score > 15)
				currentStatus = Status.WON;
		}

		if (collision.verifyCollisionCannonballJet(jet2)) {
			sounds.playSound("bomb");
			jet2.setX(800);
			jet2.setY(150);
			score += 7;
			if (score > 15)
				currentStatus = Status.WON;
		}

		if (collision.verifyCollisionCannonballTank()) {
			tank.decreaseIntegrity();
			if (tank.getIntegrity() <= 0)
				currentStatus = Status.GAME_OVER;
			else
				currentStatus = Status.LOST;
		}
		if (collision.verifyCollisionCannonballBuilding(building1)) {
			sounds.playSound("bomb");
			shapesList.remove(building1);
			score += 8;
			if (score > 15)
				currentStatus = Status.WON;
		}

		if (collision.verifyCollisionCannonballBuilding(building2)) {
			sounds.playSound("bomb");
			shapesList.remove(building2);
			score += 8;
			if (score > 15)
				currentStatus = Status.WON;
		}

		if (collision.verifyCollisionCannonballBuilding(building3)) {
			sounds.playSound("bomb");
			shapesList.remove(building3);
			score += 8;
			if (score > 15)
				currentStatus = Status.WON;
		}

		collision.veryfyCollisionCannonballWall(barrier);

		if (collision.verifyCollisionMissileTank(jet1) || collision.verifyCollisionMissileTank(jet2)) {
			tank.decreaseIntegrity();
			if (tank.getIntegrity() <= 0)
				currentStatus = Status.GAME_OVER;
			else
				currentStatus = Status.LOST;
		}

	}

	private void updateDisplayStatus() {
		powerDisplay.setMagnitude(tank.getPower());
		angleDisplay.setMagnitude(tank.getAngle());
		scoreDisplay.setMagnitude(score);
		integrityDisplay.setMagnitude(tank.getIntegrity());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// Not used.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (currentStatus == Status.LOST && e.getKeyCode() == KeyEvent.VK_ENTER) {
			currentStatus = Status.PLAYING;
		} else if ((currentStatus == Status.GAME_OVER || currentStatus == Status.WON) && e.getKeyCode() == KeyEvent.VK_ENTER) {
			tank.resetIntegrity();
			score = 0;
			spritesList.clear();
			shapesList.clear();
			initComponents();
			currentStatus = Status.PLAYING;
		} else if (currentStatus == Status.PLAYING) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					tank.advance();
					break;
				case KeyEvent.VK_LEFT:
					tank.goBack();
					break;
				case KeyEvent.VK_UP:
					tank.increaseTilt();
					break;
				case KeyEvent.VK_DOWN:
					tank.decreaseTilt();
					break;
				case KeyEvent.VK_1:
					tank.setPower(1);
					break;
				case KeyEvent.VK_2:
					tank.setPower(2);
					break;
				case KeyEvent.VK_3:
					tank.setPower(3);
					break;
				case KeyEvent.VK_4:
					tank.setPower(4);
					break;
				case KeyEvent.VK_5:
					tank.setPower(5);
					break;
				case KeyEvent.VK_SPACE:
					tank.fire();
			}
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
			tank.stop();
		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
			tank.stopTilt();
		}
	}
}
