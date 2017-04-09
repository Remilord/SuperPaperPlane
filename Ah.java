import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.util.ArrayList;
public class Ah extends GameObject {
	private BufferedImage ah;
	private ArrayList<GameObject> objects;
			public Ah(int x,int y, ArrayList<GameObject> objects) {
				super(x,y);
				this.objects = objects;
				ah=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"denis"+File.separator+"AH.png");
				this.largeur=40;
				this.hauteur=30;
			}
			@Override
			public BufferedImage getImage() {
				return this.ah;
			}

			@Override
			public void deplacement(int vitesse){
				setPositionY(getPositionY()-30);
        if(getPositionY() <= 0) {
          this.objects.remove(ah);
        }
			}

}
