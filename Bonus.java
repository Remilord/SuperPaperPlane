import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;
import java.io.File;


@SuppressWarnings("serial")
public class Bonus extends GameObject {
	private int vitesse;
	private boolean mini;
	private BufferedImage bonusTras;
	private BufferedImage bonusSpeed;
	private BufferedImage bonusactuel;
	private BufferedImage bonusmini;
	private int etatnum;
	private Random rand;
	public Bonus(int x,int y, Niveau niveau) {
		super(0,0, niveau);
		objectType = ObjectType.BONUS;
		needsToBeRemoved = false;
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


	public int getVitesseStatus() {
		return this.vitesse;
	}
	public boolean getMiniStatus() {
		return this.mini;
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
			if(getPositionY() <= -50) {
        needsToBeRemoved = true;
      }
	}
	@Override
	public boolean remove(){
    return needsToBeRemoved;
  }


	@Override
	public void whenGetHit(){
		niveau.getAvion().setIsBonused(true);
		if(etatnum == 1){
			niveau.getAvion().setInvincible(true);
		}
		else if(etatnum == 2){
			niveau.setVitesse(12);
		}
		else
			niveau.getAvion().setLittle(true);

		needsToBeRemoved = true;
	}

	@Override
	public boolean canHit(GameObject g){
		if(g.getObjectType() == ObjectType.PLAYER)
			return true;
		return false;
	}

}
