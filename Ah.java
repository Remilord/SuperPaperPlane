import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
public class Ah extends GameObject {
	private BufferedImage ah;
			public Ah(int x,int y) {
				super(x,y);
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
					this.setPositionY(this.getPositionY()-vitesse*3);
			}
}
