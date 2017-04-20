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
	public Mario(int positionX,int positionY, Niveau niveau) {
		super(positionX,positionY, niveau);
		mario = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"mario"+File.separator+"Mario1.png");
		animationMario = new BufferedImage[6];
		for(int a=1;a<6;a++) {
			animationMario[a]=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"mario"+File.separator+"Mario"+a+".png");
		}
		this.largeur=250;
		this.hauteur=400;
	}
	public boolean marioQuiPop() {
		Random rd = new Random();
		if(rd.nextInt(150)==25) {
			return true;
		} else {
			return false;
		}
	}
	/*public void animationMario (ArrayList<GameObject> gg,int t) {
	}*/
	  public void setImageMarioActuel(int n){
		  		this.mario=animationMario[n];
	  }
		  @Override
		  public BufferedImage getImage() {
		    return mario;
		  }
}
