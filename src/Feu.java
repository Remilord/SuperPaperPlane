import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
public class Feu extends GameObject {
	private BufferedImage feugauche;
	private BufferedImage feudroite;
		public Feu(int x,int y) {
			super(x,y);
			feugauche=Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/feugauche.png");
			feudroite=Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/feudroite.png");
			this.largeur =50;
		    this.hauteur = 800;
		}
		@Override
		  public BufferedImage getImage() {
			if(this.getPositionX()==0) {
		    return feugauche;
		  }else {
			  return feudroite;
		  }
}
}
