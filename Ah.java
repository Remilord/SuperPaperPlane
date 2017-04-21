import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.util.ArrayList;
@SuppressWarnings("serial")

public class Ah extends GameObject {
	private BufferedImage ah;
	private EventSpawner eventSpawner;
			public Ah(int x,int y, Niveau niveau, EventSpawner e) {
				super(x, y, niveau);
				eventSpawner = e;
				ah=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"denis"+File.separator+"AH.png");
				this.largeur=40;
				this.hauteur=30;
				Son.playTempSound("/res/son/ah.wav");
			}
			@Override
			public BufferedImage getImage() {
				return this.ah;
			}

			@Override
			public void deplacement(int vitesse){
				setPositionY(getPositionY()-30);
      }

			@Override
			public boolean remove(){
				if(getPositionY() <= -60){
					eventSpawner.setIsDenisShouting(false);
					return true;
				}
				return false;
			}


			@Override
			public void whenGetHit(){
		    niveau.loose();
		  }

			@Override
			public boolean canHit(GameObject g){
				if(g.getObjectType() == ObjectType.PLAYER){
					return true;
				}
				return false;
			}



}
