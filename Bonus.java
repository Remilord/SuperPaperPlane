import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;
import java.io.File;
public class Bonus extends GameObject {
	private int vitesse;
	private boolean mini;
	private BufferedImage bonusTras;
	private BufferedImage bonusSpeed;
	private BufferedImage bonusactuel;
	private BufferedImage bonusmini;
	private int etatnum;
	private Random rand;
	public Bonus(int x,int y) {
		super(0,0);
		this.rand=new Random();
		this.bonusTras = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"bonus"+File.separator+"BonusTras.png");
		this.bonusSpeed = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"bonus"+File.separator+"BonusSpeed.png");
		this.bonusmini = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"bonus"+File.separator+"Bonusmini.png");
		this.positionX = 280 + (int)(Math.random() * ((400 - 280) + 1));
		this.positionY = 2000;
		this.largeur = 50;
		this.hauteur = 50;
		this.etatnum=rand.nextInt(3)+1;
		this.setImageBonus(this.etatnum);
		this.vitesse=6;
		this.mini=false;
	}
	@Override
	  public BufferedImage getImage() {
	    return bonusactuel;
	  }
	public boolean sousEffet(int bde) {
		if(bde>0) {
			return true;
		}
		return false;
	}
	public void setAttributes(int be,int speed,boolean min) {
		if(be==2) {
			this.vitesse=12;
		}else if(be==3) {
			this.mini=true;
		}
	}
	public int getVitesseStatus() {
		return this.vitesse;
	}
	public boolean getMiniStatus() {
		return this.mini;
	}
	public void resetBonusStatus() {
		this.vitesse=6;
		this.mini=false;
	}
	public void resetBonus() {
        this.etatnum=rand.nextInt(3)+1;;
 	   this.setImageBonus(this.etatnum);
 	   this.resetPosition();
	}
	public void resetPosition() {
		this.setPositionY(rand.nextInt(2001)+1000);
        this.setPositionX(rand.nextInt(571)+70);
	}
	public boolean timerBonus(int t) {
		if(t==600) {
		    	   this.etatnum=rand.nextInt(3)+1;;
		    	   this.setImageBonus(this.etatnum);
		    	   this.resetBonusStatus();
		    	   return true;
		}
		return false;
	}
	private void setImageBonus(int r) {
		if(r==1) {
			this.bonusactuel=this.bonusTras;
		}else if(r==2) {
			this.bonusactuel=this.bonusSpeed;
		}else if(r==3) {
			this.bonusactuel=this.bonusmini;
		}
	}
	public int getEtatNum() {
		return this.etatnum;
	}

	@Override
	public void deplacement(int vitesse){
			this.setPositionY(this.getPositionY()-vitesse);
			if(getPositionY()<=-50) {
        resetBonus();
      }
	}
}
