import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.util.ArrayList;
@SuppressWarnings("serial")

public class Tir extends GameObject {
			private BufferedImage tir;
			private ArrayList<GameObject> objects;
			private Niveau niveau;
			public Tir(int x,int y, Niveau niveau) {
				super(x, y, niveau);
				objectType = ObjectType.OFFENSIVE;

				this.niveau = niveau;
				this.objects = objects;
				tir=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"denis"+File.separator+"Tir.png");
				this.largeur=15;
				this.hauteur=30;
			}
			@Override
			public BufferedImage getImage() {
				return tir;
			}

			@Override
			public void deplacement(int vitesse){
				if(niveau.getDennisExist()&& niveau.getAvion().getIsShooting()){
	        setPositionY(getPositionY()+25);
	      }
			}

			@Override
			public boolean remove(){
				if(getPositionY() >= 850){
					niveau.getAvion().setIsShooting(false);
					return true;
				}
				return false;
			}


			@Override
			public void whenGetHit(){
				
			}

			@Override
			public boolean canHit(GameObject g){
				if(g.getObjectType() == ObjectType.ENEMY)
					return true;
				return false;
			}


}
