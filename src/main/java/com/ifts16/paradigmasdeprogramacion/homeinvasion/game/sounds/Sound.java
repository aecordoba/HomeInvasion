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
    }

    public void addSound(String name, String file) {
        try {
            byte[] fileContent = Files.readAllBytes(Paths.get(Sound.class.getClassLoader().getResource(file).toURI()));
            sounds.put(name, fileContent);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo encontrar el archivo de sonido: " + file);
        }
    }
  
    public void playSound(String sound) {
        try {
            byte[] soundBytes = sounds.get(sound);
            InputStream myInputStream = new ByteArrayInputStream(soundBytes);
            AudioInputStream ais = AudioSystem.getAudioInputStream(myInputStream);
            DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);
            clip.start();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

