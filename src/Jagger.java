import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
public class Jagger extends GameObject {
	private BufferedImage jagger;
	private BufferedImage jaggerz;
	private BufferedImage jaggero;
	private BufferedImage jaggert;
	private BufferedImage jaggerth;
	private BufferedImage jaggerf;
	private Random rand;
		public Jagger(int x,int y) {
			super(x,y);
			this.rand=new Random();
			jagger=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"jagger"+File.separator+"jagger0.png");
			jaggerz=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"jagger"+File.separator+"jagger0.png");
			jaggero=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"jagger"+File.separator+"jagger1.png");
			jaggert=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"jagger"+File.separator+"jagger2.png");
			jaggerth=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"jagger"+File.separator+"jagger3.png");
			jaggerf=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"jagger"+File.separator+"jagger4.png");
			this.positionX=500+rand.nextInt(1000);
			this.positionY=2000;
			this.hauteur=120;
			this.largeur=80;
		}
		public void resetPosition() {
			this.positionX=500+rand.nextInt(1000);
			this.positionY=2000+rand.nextInt(2000);
		}
		public void setImageJagger(int n) {
			if(n==0) {
				this.jagger=this.jaggerz;
			}else if(n==1) {
				this.jagger=this.jaggero;
			}else if(n==2) {
				this.jagger=this.jaggert;
			}else if(n==3) {
				this.jagger=this.jaggerth;
			}else if(n==4) {
				this.jagger=this.jaggerf;
			}
		}
		/* Methode pour rendre flou une image , inutilis� car trop lent et image peu flou*/
		public static BufferedImage flou(BufferedImage img)
		{
			int l=0,h=0,somme=0;
			BufferedImage im=img,imfl=null;
			l = im.getWidth();
			h = im.getHeight();
			imfl = new BufferedImage(l,h,BufferedImage.TYPE_INT_RGB);
			for (int j=1;j<h-1;j++) {
				for (int i=1;i<l-1;i++)	{
					int r = 0;
					int g = 0;
					int b = 0;
					for (int m=j-1;m<=j+1;m++) {
						for (int k=i-1;k<=i+1;k++) {
							Color c = new Color(im.getRGB(k,m));
							r += c.getRed();
							g += c.getGreen();
							b += c.getBlue();
						}
						imfl.setRGB(i,j,new Color(r/9,g/9,b/9).getRGB());
					}
				}
			}
			return imfl;
		}
		public BufferedImage getImage() {
		    return jagger;
		  }
}
