import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.Random;
import java.io.File;
import java.util.ArrayList;
public class Portal extends GameObject {
	private BufferedImage portal;
	private BufferedImage[] animationportal;
	private Random rx;
	private ArrayList<GameObject> objects;
	private static int post;
			public Portal(int x,int y, ArrayList<GameObject> objects) {
				super(x,y);
				this.objects = objects;
				rx=new Random();
				portal=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"portal"+File.separator+"portal0.png");
				animationportal=new BufferedImage[7];
				for(int a=0;a<7;a++) {
					animationportal[a]=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"portal"+File.separator+"portal"+a+".png");
				}
				this.largeur=50;
				this.hauteur=100;
				post=0;
			}

			public void setPositions(int n) {
				if(n==1) {
					this.setPositionX(rx.nextInt(151)+240);
					this.setPositionY(350);
					post=this.getPositionX();
				} else {
					this.setPositionX(rx.nextInt(post-239)+80);
					this.setPositionY(850);
				}
			}
			public void setImagePortalActuel(int n) {
				this.portal=this.animationportal[n];
			}
			@Override
			public BufferedImage getImage() {
				return this.portal;
			}

			@Override
			public void deplacement(int vitesse){
				setPositionY(getPositionY()-vitesse);
				if(getPositionY() < -100 )
          objects.remove(this);
			}
}
