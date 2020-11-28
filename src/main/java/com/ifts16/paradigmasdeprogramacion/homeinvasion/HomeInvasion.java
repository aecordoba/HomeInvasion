/*
 * 		HomeInvasion.java
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
 * 		HomeInvasion.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Nov 10, 2020
 */
package com.ifts16.paradigmasdeprogramacion.homeinvasion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.ifts16.paradigmasdeprogramacion.homeinvasion.game.GamePanel;
import com.ifts16.paradigmasdeprogramacion.homeinvasion.presentation.PresentationPanel;

/**
 * Clase de inicio de la aplicación.
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 */
public class HomeInvasion {
	private static final int WIDTH = 800;
	private static final int HEIGHT = 500;
	
	private static JFrame mainFrame;

	/**
	 * Método de entrada de la aplicación.
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("sun.java2d.opengl", "true");

		mainFrame = new JFrame("Home Invasion");
		mainFrame.setIconImage(getIconImage());
		mainFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setBackground(new Color(255, 255, 255));
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		
		showPresentationPanel();
		mainFrame.setLocationRelativeTo(null);
	}

	private static BufferedImage getIconImage() {
		BufferedImage image = null;
		try {
			image = ImageIO.read(HomeInvasion.class.getResource("/icons/app.jpg"));
		} catch (IOException ex) {
			System.out.println("No se puede cargar la imagen para el ícono de la aplicación.");
		}
		return image;
	}
	
	private static void showPresentationPanel() {
		PresentationPanel presentationPanel = new PresentationPanel(WIDTH, HEIGHT);
		JButton startButton = new JButton("Start");
		presentationPanel.add(startButton);
		mainFrame.add(presentationPanel);
		mainFrame.pack();
		
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				presentationPanel.setVisible(false);
				startGame();
			}
		});
		startButton.setFocusable(false);
	}

	private static void startGame() {
		GamePanel gamePanel = new GamePanel(WIDTH, HEIGHT);
		mainFrame.add(gamePanel);
		gamePanel.setVisible(true);
		mainFrame.addKeyListener(gamePanel);
		mainFrame.pack();
		
		Thread gameThread = new Thread(gamePanel);
		gameThread.start();
	}
}
