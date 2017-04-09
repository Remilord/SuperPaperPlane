import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.*;
public class Feu extends GameObject {
	private BufferedImage feugauche;
	private BufferedImage feudroite;
	private BufferedImage feugaucheb;
	private BufferedImage feudroiteb;
	private BufferedImage feugauchef;
	private BufferedImage feudroitef;
		public Feu(int x,int y) {
			super(x,y);
			feugaucheb=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"feu"+File.separator+"feugauche.png");
			feudroiteb=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"feu"+File.separator+"feudroite.png");
			feugauche=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"feu"+File.separator+"feugauche.png");
			feudroite=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"feu"+File.separator+"feudroite.png");
			feugauchef=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"feu"+File.separator+"feugaucheflou.png");
			feudroitef=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"feu"+File.separator+"feudroiteflou.png");
			this.largeur =50;
		    this.hauteur = 800;
		}
		public void setFlou(boolean on) {
			if(on) {
			this.feugauche=this.feugauchef;
			this.feudroite=this.feudroitef;
			}else {
				this.feugauche=this.feugaucheb;
				this.feudroite=this.feudroiteb;
			}
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
