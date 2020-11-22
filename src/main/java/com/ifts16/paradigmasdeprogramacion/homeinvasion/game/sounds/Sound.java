package com.ifts16.paradigmasdeprogramacion.homeinvasion.game.sounds;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Sound {
	private Map<String, byte[]> sounds;

	public Sound() {
		this.sounds = new HashMap<String, byte[]>();
		addSound("music", "sounds/HeroicIntrusion.wav");
		addSound("tank", "sounds/tank_explosion.wav");
		addSound("jet", "sounds/jet_explosion.wav");
		addSound("building", "sounds/building_explosion.wav");
	}

	private void addSound(String name, String file) {
		try {
			byte[] fileContent = Files.readAllBytes(Paths.get(Sound.class.getClassLoader().getResource(file).toURI()));
			sounds.put(name, fileContent);
		} catch (Exception e) {
			System.out.println("No se pudo encontrar el archivo de sonido: " + file);
			throw new RuntimeException(e);
		}
	}

	public void play(String sound, boolean continuosly) {
		try {
			byte[] soundBytes = sounds.get(sound);
			InputStream myInputStream = new ByteArrayInputStream(soundBytes);
			AudioInputStream ais = AudioSystem.getAudioInputStream(myInputStream);
			DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			if (continuosly) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			clip.start();
		} catch (Exception e) {
			System.out.println("No se puede ejecutar sonido: " + sound);
			throw new RuntimeException(e);
		}
	}
}
