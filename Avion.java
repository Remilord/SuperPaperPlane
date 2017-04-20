import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

@SuppressWarnings("serial")
public class Avion extends GameObject{
  private BufferedImage avionImage;
  private BufferedImage[] PionPlane;
  private Niveau niveau;
  private boolean isShooting = false;
  private boolean isInvincible = false;
  private boolean isLittle = false;
  private boolean isHighSpeed = false;

  private Hitbox hitbox = new Hitbox(this);
  private int nbBottles;
  private int positionAvion; //0 pour bas, 1 pour gauche, 2 pour droite

  public Avion(int positionX, int positionY, Niveau niveau){
    super(positionX, positionY, niveau);
    objectType = ObjectType.PLAYER;

    this.niveau = niveau;
    positionAvion = 0;
    nbBottles = 0;
    PionPlane=new BufferedImage[14];
    this.avionImage=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"avion"+File.separator+"PionPlane2.png");
    for(int a =1;a<14;a++) {
      PionPlane[a] = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"avion"+File.separator+"PionPlane"+a+".png");
    }
    this.largeur = 100;
    this.hauteur = 75;
  }
  public void setImageAvionActuel(int i){
    this.avionImage=PionPlane[i];
  }

  public int getNumberBottles(){
    return nbBottles;
  }

  public void setNumberBottles(int number){
    nbBottles = number;
  }

  public void setLittle(boolean b){
    isLittle = b;
  }

  public boolean getIsLittle(){
    return isLittle;
  }

  public int getPositionAvion(){
    return positionAvion;
  }

  public boolean getIsInvincible(){
    return isInvincible;
  }

  @Override
  public BufferedImage getImage() {
    return avionImage;
  }

  public void setIsShooting(boolean b){
    this.isShooting = b;
  }
  public boolean getIsShooting(){
    return isShooting;
  }
  public void setInvincible(boolean b){
    isInvincible = b;
  }



  @Override
  public void deplacement(int vitesse){
    int bonusNum = 0;
    if(isInvincible)
      bonusNum = 1;
    else if(isLittle)
      bonusNum = 3;
    else if(isHighSpeed)
      bonusNum = 2;

    if(niveau.getNiveauEntreeUtilisateur() == 2){
      if(getPositionX() > niveau.getPosMin()){
        setPositionX(getPositionX()-niveau.getVitesse()+2);
        setImageAvionActuel((bonusNum*3)+3);
        positionAvion = 2;
      }
    }
    else if(niveau.getNiveauEntreeUtilisateur() == 1){
      if(getPositionX() < 605){
        setPositionX(getPositionX()+niveau.getVitesse()-2);
        setImageAvionActuel((bonusNum*3)+1);
        positionAvion = 1;
      }
    }
    else if(niveau.getEntreeUtilisateur() == 0){
      setImageAvionActuel((bonusNum*3)+2);
      positionAvion = 0;
    }


    if(niveau.getShootingStar() && niveau.getInsane()) {
      if(getPositionX() > 505) {
        setPositionX(-50);
      }else if (getPositionX() < -75) {
        setPositionX(490);
      }
    }
    hitbox.setHitboxAvion();
  }

  @Override
  public boolean create(){
    if(!getIsShooting() && niveau.getDennisExist() &&niveau.getEntreeUtilisateur() == 0 ){
      setIsShooting(true);
      return true;
    }
    return false;
  }

  @Override
  public void createHitbox(){
    hitbox.setHitboxAvion();
  }

  @Override
  public Polygon getHitbox(){
    //createHitbox();
    return hitbox.getHitbox();
  }

  @Override
  public void whenGetHit(){

  }

  public String toString(){
    return "Avion";
  }

  @Override
  public boolean canHit(GameObject g){
    if(g.getObjectType() != ObjectType.PLAYER
        && g.getObjectType() != ObjectType.OFFENSIVE){
      return true;
    }
    return false;
  }

}
