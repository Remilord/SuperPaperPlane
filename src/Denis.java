import javax.swing.*;
import java.awt.image.*;
import java.awt.*;
import java.io.File;
public class Denis extends GameObject {
	private BufferedImage denis;
	private BufferedImage denisz;
	private BufferedImage deniso;
	private BufferedImage denist;
	private BufferedImage denisth;
	private BufferedImage denisq;
			public Denis(int x,int y) {
				super(x,y);
				denis=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"denis"+File.separator+"Denis0.png");
				denisz=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"denis"+File.separator+"Denis1.png");
				deniso=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"denis"+File.separator+"Denis2.png");
				denist=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"denis"+File.separator+"Denis3.png");
				denisth=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"denis"+File.separator+"Denis4.png");
				denisq=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"denis"+File.separator+"Denis5.png");
				this.largeur=100;
				this.hauteur=100;
			}
			public void setImageDenisActuel(int n) {
				if(n==0) {
					this.denis=this.denisz;
				}else if(n==1) {
					this.denis=this.deniso;
				}else if(n==2) {
					this.denis=this.denist;
				}else if(n==3) {
					this.denis=this.denisth;
				}else if(n==4) {
					this.denis=this.denisq;
				}
			}
			@Override
			public BufferedImage getImage() {
				return this.denis;
			}
}
