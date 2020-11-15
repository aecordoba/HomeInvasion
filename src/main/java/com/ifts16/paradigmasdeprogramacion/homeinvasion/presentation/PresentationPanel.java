/*
 * 		PresentationPanel.java
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
 * 		PresentationPanel.java
 *  Adrián E. Córdoba [software.asia@gmail.com]		Nov 10, 2020
 */
package com.ifts16.paradigmasdeprogramacion.homeinvasion.presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Adrián E. Córdoba [software.asia@gmail.com]
 *
 */
public class PresentationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	private Image image;

	/**
	 * 
	 */
	public PresentationPanel(int width, int height) {
		this.width = width;
		this.height = height;
		initComponents();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(image, 0, 0, null);
		
		g2D.setColor(new Color(102, 102, 255));
		g2D.setFont(new Font("Dialog", 3, 48));
		g2D.drawString("HOME INVASION", 230, 100);

		g2D.setColor(new Color(0, 0, 0));
		g2D.setFont(new Font("Dialog", Font.BOLD, 15));
		g2D.drawString("by YAMILA CAPPARI & ADRIAN CORDOBA", 500, 450);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	private void initComponents() {
		try {
			image = ImageIO.read(getClass().getResource("/images/tank.jpg"));
		} catch (IOException e) {
			System.out.println("No se puede cargar la imagen de presentación.");
		}
	}
}
