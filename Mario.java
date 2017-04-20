import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.File;
@SuppressWarnings("serial")

public class Mario extends GameObject  {
	private BufferedImage mario;
	private BufferedImage[] animationMario;
	private int timer;
	private int actualImageNumber;
	private EventSpawner eventSpawner;
	public Mario(int positionX,int positionY, Niveau niveau, EventSpawner e) {
		super(positionX,positionY, niveau);
		objectType = ObjectType.BONUS;
		mario = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"mario"+File.separator+"Mario1.png");
		animationMario = new BufferedImage[6];
		timer = 0;
		actualImageNumber = 0;
		setImageMarioActuel(actualImageNumber);
		eventSpawner = e;
		eventSpawner.setIsMario(true);

		for(int a=1;a<6;a++) {
			animationMario[a]=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"mario"+File.separator+"Mario"+a+".png");
		}
		this.largeur=250;
		this.hauteur=400;
	}


	@Override
	public void deplacement(int vitesse){
		timer++;
		if(timer == 1) {
			Son.playTempSound("/res/son/Non.wav");
		}
		if(timer == 150) {
			needsToBeRemoved = true;
		} else if ((timer == 15) || (timer == 30) || (timer == 45) || (timer == 60)) {
			actualImageNumber++;
			setImageMarioActuel(actualImageNumber);
		}

	}


	public void setImageMarioActuel(int n){
		this.mario = animationMario[n];
	}

	@Override
	public boolean remove(){
		if(needsToBeRemoved){
			eventSpawner.setIsMario(false);
			return true;
		}
		return false;
	}

	@Override
	public BufferedImage getImage() {
		return mario;
	}

	@Override
	public void whenGetHit(){

	}
}
