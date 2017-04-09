import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.*;
import javax.swing.*;
public class Barre extends GameObject {
	private BufferedImage barre;
	private BufferedImage barreb;
	private BufferedImage barref;
	private BufferedImage barress;
	public Barre(int x,int y) {
		super(0, 0);
		barress = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"barre"+File.separator+"BarreSS.png");
		barre = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"barre"+File.separator+"Barre.png");
		barreb = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"barre"+File.separator+"Barre.png");
		barref=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"barre"+File.separator+"Barreflou.png");
		this.positionX = 280 + (int)(Math.random() * ((400 - 280) + 1));
		this.positionY = 600;
		this.largeur = 600;
		this.hauteur = 80;
	}
	public void setFlou(boolean on) {
		if(on) {
			this.barre=this.barref;
		}else{
			this.barre=this.barreb;
		}
	}
	public void setImageSS(boolean onss) {
		if(onss) {
		this.barre=this.barress;
	} else {
		this.barre=this.barreb;
	}
	}
	@Override
  public BufferedImage getImage() {
    return barre;
  }
}
