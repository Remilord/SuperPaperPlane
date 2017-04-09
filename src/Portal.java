import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.Random;
import java.io.File;
public class Portal extends GameObject {
	private BufferedImage portal;
	private BufferedImage portalz;
	private BufferedImage portalo;
	private BufferedImage portalt;
	private BufferedImage portalth;
	private BufferedImage portalq;
	private BufferedImage portalf;
	private BufferedImage portals;
	private Random rx;
	private static int post;
			public Portal(int x,int y) {
				super(x,y);
				rx=new Random();
				portal=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"portal"+File.separator+"portal0.png");
				portalz=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"portal"+File.separator+"portal0.png");
				portalo=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"portal"+File.separator+"portal1.png");
				portalt=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"portal"+File.separator+"portal2.png");
				portalth=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"portal"+File.separator+"portal3.png");
				portalq=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"portal"+File.separator+"portal4.png");
				portalf=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"portal"+File.separator+"portal5.png");
				portals=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"portal"+File.separator+"portal6.png");
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
				if(n==0) {
					this.portal=this.portalz;
				}else if(n==1) {
					this.portal=this.portalo;
				}else if(n==2) {
					this.portal=this.portalt;
				}else if(n==3) {
					this.portal=this.portalth;
				}else if(n==4) {
					this.portal=this.portalq;
				}else if(n==5) {
					this.portal=this.portalf;
				}else if(n==6) {
					this.portal=this.portalq;
				}
			}
			@Override
			public BufferedImage getImage() {
				return this.portal;
			}
}
