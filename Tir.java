import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
public class Tir extends GameObject {
			private BufferedImage tir;
			public Tir(int x,int y) {
				super(x,y);
				tir=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"denis"+File.separator+"Tir.png");
				this.largeur=15;
				this.hauteur=30;
			}
			@Override
			public BufferedImage getImage() {
				return tir;
			}
}
