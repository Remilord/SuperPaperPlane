import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.util.ArrayList;
public class Tir extends GameObject {
			private BufferedImage tir;
			private ArrayList<GameObject> objects;
			public Tir(int x,int y, Niveau niveau) {
				super(x, y, niveau);
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
				setPositionY(getPositionY()+15);

			}

			@Override
			public boolean remove(){
				return getPositionY() >= 850;
			}
}
