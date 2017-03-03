import java.awt.*;
import javax.swing.*;
import java.io.File;

public class Avion extends GameObject{
  private Image avionGauche;
  private Image avionDroite;
  private Image avionBas;
  protected Image avionImage;
  private Image crash;
  public Avion(int positionX, int positionY){
    super(positionX, positionY);
    avionGauche = Toolkit.getDefaultToolkit().getImage(".."+File.separator+"image"+File.separator+"PionPlaneGauche.png");
    avionDroite = Toolkit.getDefaultToolkit().getImage(".."+File.separator+"image"+File.separator+"PionPlane.png");
    avionBas = Toolkit.getDefaultToolkit().getImage(".."+File.separator+"image"+File.separator+"PionPlaneBas.png");
    crash = Toolkit.getDefaultToolkit().getImage(".."+File.separator+"image"+File.separator+"Crash.png");
    avionImage = Toolkit.getDefaultToolkit().getImage(".."+File.separator+"image"+File.separator+"PionPlane.png");
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
  public Image getImage() {
    return avionImage;
  }

}
