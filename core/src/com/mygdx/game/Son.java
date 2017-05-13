package com.mygdx.game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Son {
	private Clip c;

	public Son(String son) {
		try {
			AudioInputStream ai = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(son));
			c = AudioSystem.getClip();
			c.open(ai);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play() {
		if (c != null)
			c.start();
	}

	public void stop() {
		if (c != null)
			c.stop();
	}

	public Clip getClip() {
		return c;
	}

	public static void playTempSound(String son) {
		Son s = new Son(son);
		s.play();
	}
}
