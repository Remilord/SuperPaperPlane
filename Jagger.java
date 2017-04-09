import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
public class Jagger extends GameObject {
	private BufferedImage jagger;
	private BufferedImage[] animationjagger;
	private Random rand;
	private ArrayList<GameObject> objects;
		public Jagger(int x,int y, Niveau niveau){
			super(x,y, niveau);
			this.objects = objects;
			this.rand=new Random();
			jagger=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"jagger"+File.separator+"jagger0.png");
			animationjagger=new BufferedImage[5];
			for(int a=0;a<5;a++) {
				animationjagger[a]=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"jagger"+File.separator+"jagger"+a+".png");
			}
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
			this.jagger=animationjagger[n];
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


			@Override
			public void deplacement(int vitesse){
				setPositionY(getPositionY()-(vitesse+5));
				setPositionX(getPositionX()-3);
				if((getPositionX()<=0-80)||(getPositionY()<=0-80)) {
	        resetPosition();
	      }
			}
}
