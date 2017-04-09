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
  private BufferedImage avionGaucherapi;
  private BufferedImage avionDroiterapi;
  private BufferedImage avionBasrapi;
  private BufferedImage avionGauchetrans;
  private BufferedImage avionDroitetrans;
  private BufferedImage avionBastrans;
  private BufferedImage avionGauchemini;
  private BufferedImage avionDroitemini;
  private BufferedImage avionBasmini;
  public Avion(int positionX, int positionY, Niveau niveau){
    super(positionX, positionY, niveau);
    avionGauche = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"avion"+File.separator+"PionPlaneGauche.png");
    avionDroite = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"avion"+File.separator+"PionPlane.png");
    avionBas = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"avion"+File.separator+"PionPlaneBas.png");
    crash = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"avion"+File.separator+"Crash.png");
    avionImage = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"avion"+File.separator+"PionPlane.png");
    avionGaucherapi = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"avion"+File.separator+"PionPlaneGaucherapi.png");
    avionDroiterapi = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"avion"+File.separator+"PionPlanerapi.png");
    avionBasrapi = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"avion"+File.separator+"PionPlaneBasrapi.png");
    avionGauchetrans = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"avion"+File.separator+"PionPlaneGaucheinvi.png");
    avionDroitetrans = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"avion"+File.separator+"PionPlaneinvi.png");
    avionBastrans = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"avion"+File.separator+"PionPlaneBasinvi.png");
    avionGauchemini = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"avion"+File.separator+"PionPlaneGauchemini.png");
    avionDroitemini = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"avion"+File.separator+"PionPlanemini.png");
    avionBasmini = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"avion"+File.separator+"PionPlaneBasmini.png");
    this.largeur = 100;
    this.hauteur = 75;
  }
  public void setImageAvionActuel(String str,int i){
	  if(i==0) {
    if(str == "droite")
      this.avionImage = this.avionDroite;
    else if (str == "gauche")
      this.avionImage = this.avionGauche;
    else if (str == "bas")
      this.avionImage = this.avionBas;
    else
      this.avionImage = this.crash;
	  }
	  if(i==1) {
		    if(str == "droite")
		      this.avionImage = this.avionDroitetrans;
		    else if (str == "gauche")
		      this.avionImage = this.avionGauchetrans;
		    else if (str == "bas")
		      this.avionImage = this.avionBastrans;
		    else
		      this.avionImage = this.crash;
			  }
	  if(i==2) {
		    if(str == "droite")
		      this.avionImage = this.avionDroiterapi;
		    else if (str == "gauche")
		      this.avionImage = this.avionGaucherapi;
		    else if (str == "bas")
		      this.avionImage = this.avionBasrapi;
		    else
		      this.avionImage = this.crash;
			  }

	  if(i==3) {
		    if(str == "droite")
		      this.avionImage = this.avionDroitemini;
		    else if (str == "gauche")
		      this.avionImage = this.avionGauchemini;
		    else if (str == "bas")
		      this.avionImage = this.avionBasmini;
		    else
		      this.avionImage = this.crash;
			  }
  }

  @Override
  public BufferedImage getImage() {
    return avionImage;
  }


}
