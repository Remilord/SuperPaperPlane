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
  public Avion(int positionX, int positionY){
    super(positionX, positionY);
    avionGauche = Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/avion/PionPlaneGauche.png");
    avionDroite = Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/avion/PionPlane.png");
    avionBas = Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/avion/PionPlaneBas.png");
    crash = Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/avion/Crash.png");
    avionImage = Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/avion/PionPlane.png");
    avionGaucherapi = Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/avion/PionPlaneGaucherapi.png");
    avionDroiterapi = Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/avion/PionPlanerapi.png");
    avionBasrapi = Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/avion/PionPlaneBasrapi.png");
    avionGauchetrans = Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/avion/PionPlaneGaucheinvi.png");
    avionDroitetrans = Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/avion/PionPlaneinvi.png");
    avionBastrans = Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/avion/PionPlaneBasinvi.png");
    avionGauchemini = Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/avion/PionPlaneGauchemini.png");
    avionDroitemini = Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/avion/PionPlanemini.png");
    avionBasmini = Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/avion/PionPlaneBasmini.png");
    this.largeur =100;
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
