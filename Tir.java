import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.util.ArrayList;
@SuppressWarnings("serial")

public class Tir extends GameObject {
			private BufferedImage tir;
			private Niveau niveau;
			private EventSpawner eventSpawner;

			public Tir(int x, int y, Niveau niveau, EventSpawner eventSpawner) {
				super(x, y, niveau);
				objectType = ObjectType.OFFENSIVE;
				tir=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"denis"+File.separator+"Tir.png");
				this.largeur=15;
				this.hauteur=30;
				this.eventSpawner = eventSpawner;
				this.niveau = niveau;
			}
			@Override
			public BufferedImage getImage() {
				return tir;
			}

			@Override
			public void deplacement(int vitesse){
				if(eventSpawner.getIsDenis() && niveau.getAvion().getIsShooting()){
	        setPositionY(getPositionY()+25);
	      }
				if(!eventSpawner.getIsDenis())
					needsToBeRemoved = true;
			}

			@Override
			public boolean remove(){
				if(needsToBeRemoved){
					niveau.getAvion().setIsShooting(false);
					return true;
				}
				if(getPositionY() >= 850){
					niveau.getAvion().setIsShooting(false);
					return true;
				}
				return false;
			}


			@Override
			public void whenGetHit(){
				needsToBeRemoved = true;
			}

			@Override
			public boolean canHit(GameObject g){
				if(g.getObjectType() == ObjectType.ENEMY && !needsToBeRemoved)
					return true;
				return false;
			}


}
