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

import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.Building;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.StatusDisplay;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.StatusScreen;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.Structure;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites.Jet;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites.Sprite;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.shapes.sprites.Tank;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.sounds.Sound;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.verifications.Collision;

/**
 * Representación del panel principal del juego.
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class GamePanel extends JPanel implements KeyListener, Runnable {
	private static final long serialVersionUID = 1L;
	private static final int BUILDING_VALUE = 5;
	private static final int JET_VALUE = 8;
	private static final int WINNER_SCORE = 15;
	private static final int BARRIER_X = 550;

	enum Status {
		PLAYING, LOST, GAME_OVER, WON
	};

	private Status currentStatus;
	private int width;
	private int height;
	private Tank tank;
	private int score;
	private List<Sprite> spritesList;
	private List<Structure> structuresList;
	private StatusDisplay statusDisplay;
	private Collision collision;
	private Sound sound;

	public GamePanel(int width, int height) {
		this.width = width;
		this.height = height;
		currentStatus = Status.PLAYING;
		spritesList = new ArrayList<>();
		structuresList = new ArrayList<>();

		initComponents();
		sound = new Sound();
		sound.play("music", true);
	}

	private void initComponents() {
		setBackground(Color.BLACK);
		tank = new Tank(0, 443, BARRIER_X);
		collision = new Collision(tank);
		spritesList.add(tank);
		spritesList.add(new Jet());
		spritesList.add(new Jet());
		structuresList.add(new Building(690, 320));
		structuresList.add(new Building(750, 320));
		structuresList.add(new Building(620, 320));
		structuresList.add(new Structure(BARRIER_X, 290, 585, 450, Color.RED)); // Barrier
		structuresList.add(new Structure(0, 450, 800, 500, Color.GREEN)); // Ground
		statusDisplay = new StatusDisplay();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if (currentStatus == Status.PLAYING) {
			GradientPaint blueToWhite = new GradientPaint(0, 0, Color.BLUE, 0, 500, Color.WHITE);
			g2d.setPaint(blueToWhite);
			g2d.fill(new Rectangle(0, 0, width, height));	// Sky
			for (Sprite sprite : spritesList)
				sprite.draw(g2d);
			for (Structure structure : structuresList)
				structure.draw(g2d);
			statusDisplay.draw(g2d);
		} else if (currentStatus == Status.LOST) {
			new StatusScreen("TANQUE DETERIORADO", "Estado del tanque: " + tank.getIntegrity() + "%", "Enter para continuar.").draw(g2d);
		} else if (currentStatus == Status.GAME_OVER) {
			new StatusScreen("JUEGO TERMINADO", "Puntaje obtenido: " + score, "Enter para continuar. / Esc para terminar.").draw(g2d);
		} else if (currentStatus == Status.WON) {
			new StatusScreen("USTED GANA", "Puntaje obtenido: " + score, "Enter para continuar. / Esc para terminar.").draw(g2d);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	@Override
	public void run() {
		while (true) {
			if (currentStatus == Status.PLAYING)
				updateScene();
			repaint();
			waitToMove(40);
		}
	}

	private void updateScene() {
		updateSpritesStatus();
		updateStructuresStatus();
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
		for (Sprite sprite : spritesList) {
			if (sprite instanceof Jet) {
				Jet jet = (Jet) sprite;
				if (collision.verifyCollisionCannonballJet(jet)) {
					sound.play("jet", false);
					jet.reset();
					score += JET_VALUE;
					if (score > WINNER_SCORE)
						currentStatus = Status.WON;
				}
				if (collision.verifyCollisionMissileTank(jet)) {
					deteriorateTank();
				}
			}
		}
		if (collision.verifyCollisionCannonballTank()) {
			deteriorateTank();
		}
	}

	private void deteriorateTank() {
		sound.play("tank", false);
		tank.decreaseIntegrity();
		currentStatus = (tank.getIntegrity() <= 0) ? Status.GAME_OVER : Status.LOST;
	}

	private void updateStructuresStatus() {
		for (Structure structure : structuresList) {
			if (structure instanceof Building) {
				Building building = (Building) structure;
				if (collision.verifyCollisionCannonballBuilding(building)) {
					sound.play("building", false);
					building.destroy();
					score += BUILDING_VALUE;
					if (score > WINNER_SCORE)
						currentStatus = Status.WON;
				}
			} else if (structure instanceof Structure)
				collision.veryfyCollisionCannonballStructure(structure);
		}
	}

	private void updateDisplayStatus() {
		statusDisplay.setPower(tank.getPower());
		statusDisplay.setAngle(tank.getAngle());
		statusDisplay.setScore(score);
		statusDisplay.setIntegrity(tank.getIntegrity());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Not used.
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (currentStatus == Status.LOST && e.getKeyCode() == KeyEvent.VK_ENTER) {
			currentStatus = Status.PLAYING;
		} else if ((currentStatus == Status.GAME_OVER || currentStatus == Status.WON)) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
					break;
				case KeyEvent.VK_ENTER:
					tank.resetIntegrity();
					score = 0;
					spritesList.clear();
					structuresList.clear();
					initComponents();
					currentStatus = Status.PLAYING;
					break;
			}
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
