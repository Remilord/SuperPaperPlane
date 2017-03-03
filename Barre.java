import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.*;
import javax.swing.*;
public class Barre extends GameObject {
	private BufferedImage barre;

	public Barre(int x,int y) {
		super(x, y);
		barre = Niveau.loadBufferedImage(".."+File.separator+"image"+File.separator+"Barre.png");
		this.positionX = (int) Math.floor(Math.random() * 250);
		this.positionY = (int) Math.floor(Math.random() * 340)+220;
		this.largeur = getPositionX()+600;
		this.hauteur = getPositionY()+80;
	}

	@Override
  public BufferedImage getImage() {
    return barre;
  }
}
