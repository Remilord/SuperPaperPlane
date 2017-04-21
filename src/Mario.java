import java.awt.*;
import java.util.ArrayList;
import java.util.Random;	
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
public class Mario extends GameObject  {
	private BufferedImage mario;
	private BufferedImage mariod;
	private BufferedImage mariot;
	private BufferedImage marioq;
	private BufferedImage marioc;
	private Son non = new Son("/res/Non.wav");
	public Mario(int positionX,int positionY) {
		super(positionX,positionY);
		mario = Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/mario/Mario1.png");
		mariod = Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/mario/Mario2.png");
		mariot = Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/mario/Mario3.png");
		marioq = Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/mario/Mario4.png");
		marioc = Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/mario/Mario5.png");
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
		  		if(n==1) {
		      this.mario=this.mario;
		  } else if(n==2) {
			  this.mario=this.mariod;
		  }
		  else if(n==3) {
			  this.mario=this.mariot;
		  }
		  else if(n==4) {
			  this.mario=this.marioq;
		  }
		  else if(n==5) {
			  this.mario=this.marioc;
		  }
	  }
		  @Override
		  public BufferedImage getImage() {
		    return mario;
		  }
}
