import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
public class Avion extends GameObject{
  private BufferedImage avionGauche;
  private BufferedImage avionDroite;
  private BufferedImage avionBas;
  protected BufferedImage avionImage;
  private BufferedImage crash;
  public Avion(int positionX, int positionY){
    super(positionX, positionY);
    avionGauche = Niveau.loadBufferedImage(".."+File.separator+"image"+File.separator+"PionPlaneGauche.png");
    avionDroite = Niveau.loadBufferedImage(".."+File.separator+"image"+File.separator+"PionPlane.png");
    avionBas = Niveau.loadBufferedImage(".."+File.separator+"image"+File.separator+"PionPlaneBas.png");
    crash = Niveau.loadBufferedImage(".."+File.separator+"image"+File.separator+"Crash.png");
    avionImage = Niveau.loadBufferedImage(".."+File.separator+"image"+File.separator+"PionPlane.png");
    this.largeur = getPositionX()+100;
    this.hauteur = 75;
  }


  public int getLargeur(){
    return this.largeur;
  }
  public int getHauteur(){
    return this.hauteur;
  }
  public void setImageAvionActuel(String str){
    if(str == "droite")
      this.avionImage = this.avionDroite;
    else if (str == "gauche")
      this.avionImage = this.avionGauche;
    else if (str == "bas")
      this.avionImage = this.avionBas;
    else
      this.avionImage = this.crash;
  }
  @Override
  public BufferedImage getImage() {
    return avionImage;
  }

}
