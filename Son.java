
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import javax.sound.sampled.*;
public class Son {
	private Clip c;
	public Son(String son) {
		try {
			AudioInputStream ai = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(son));
			c=AudioSystem.getClip();
			c.open(ai);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void play() {
		if(c != null)
			c.start();
	}
	public void stop() {
		if(c != null)
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
