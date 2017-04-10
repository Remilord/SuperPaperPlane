import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
public class Avion extends GameObject{
  private BufferedImage avionImage;
  private BufferedImage[] PionPlane;
  private Niveau niveau;
  private boolean isShooting = false;
  private boolean invincible = false;
  public Avion(int positionX, int positionY, Niveau niveau){
    super(positionX, positionY, niveau);
    this.niveau = niveau;
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

  @Override
  public void deplacement(int vitesse){
    if(niveau.getNiveauEntreeUtilisateur() == 2){
      if(getPositionX() > niveau.getPosMin()){
        setPositionX(getPositionX()-niveau.getVitesse()+2);
        setImageAvionActuel((niveau.getBonusEffect()*3)+3);
      }
    }
    else if(niveau.getNiveauEntreeUtilisateur() == 1){
      if(getPositionX() < 605){
        setPositionX(getPositionX()+niveau.getVitesse()-2);
        setImageAvionActuel((niveau.getBonusEffect()*3)+1);
      }
    }
   else if(niveau.getEntreeUtilisateur() == 0){
      setImageAvionActuel((niveau.getBonusEffect()*3)+2);

    }


    if(niveau.getShootingStar() && niveau.getInsane()) {
      if(getPositionX() > 505) {
        setPositionX(-50);
      }else if (getPositionX() < -75) {
        setPositionX(490);
      }
    }
  }

  @Override
  public boolean create(){
    if(!getIsShooting() && niveau.getDennisExist() &&niveau.getEntreeUtilisateur() == 0 ){
      setIsShooting(true);
      return true;
    }
    return false;

  }



}
