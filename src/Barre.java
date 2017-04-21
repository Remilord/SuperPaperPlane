import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.*;
import javax.swing.*;
public class Barre extends GameObject {
	private BufferedImage barre;

	public Barre(int x,int y) {
		super(0, 0);
		barre = Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/Barre.png");
		this.positionX = 280 + (int)(Math.random() * ((400 - 280) + 1));
		this.positionY = 600;
		this.largeur = 600;
		this.hauteur = 80;
	}
	public void setImage(String s) {
		barre= Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/Barre"+s+".png");
	}
	@Override
  public BufferedImage getImage() {
    return barre;
  }
}
