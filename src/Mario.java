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
public class Mario extends GameObject  {
	private BufferedImage mario;
	private BufferedImage mariod;
	private BufferedImage mariot;
	private BufferedImage marioq;
	private BufferedImage marioc;
	private BufferedImage mariob;
	public Mario(int positionX,int positionY) {
		super(positionX,positionY);
		mariob=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"mario"+File.separator+"Mario1.png");
		mario = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"mario"+File.separator+"Mario1.png");
		mariod = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"mario"+File.separator+"Mario2.png");
		mariot = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"mario"+File.separator+"Mario3.png");
		marioq = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"mario"+File.separator+"Mario4.png");
		marioc = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"mario"+File.separator+"Mario5.png");
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
		      this.mario=this.mariob;
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
