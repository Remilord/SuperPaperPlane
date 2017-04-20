import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyListener;
@SuppressWarnings("serial")

public class Hitbox extends Polygon {
	private Avion avion;
	private Polygon polygon;

	public Hitbox(Avion avion) {
		super();
		this.avion = avion;
		polygon = new Polygon();
	}

	public Polygon getHitbox(){
		return polygon;
	}

	public void setHitboxAvion() {
		polygon.reset();
		if(!avion.getIsLittle()) {
			if(avion.getPositionAvion() == 0) { /*Position vers le bas */
				polygon.addPoint(avion.getPositionX()+29,avion.getPositionY()+7);
				polygon.addPoint(avion.getPositionX()+78,avion.getPositionY()+6);
				polygon.addPoint(avion.getPositionX()+47,avion.getPositionY()+74);
			}
			if(avion.getPositionAvion()==1) { /*Position vers la droite*/
				polygon.addPoint(avion.getPositionX()+52,avion.getPositionY()+1);
				polygon.addPoint(avion.getPositionX(),avion.getPositionY()+44);
				polygon.addPoint(avion.getPositionX()+100,avion.getPositionY()+73);
			}
			if(avion.getPositionAvion()==2) { /*Position vers la gauche*/
				polygon.addPoint(avion.getPositionX()+50,avion.getPositionY()+4);
				polygon.addPoint(avion.getPositionX()+97,avion.getPositionY()+47);
				polygon.addPoint(avion.getPositionX()+3,avion.getPositionY()+72);
			}

		}else{
			if(avion.getPositionAvion()==0) { /*Position vers le bas */
				polygon.addPoint(avion.getPositionX()+14,avion.getPositionY()+3);
				polygon.addPoint(avion.getPositionX()+40,avion.getPositionY()+2);
				polygon.addPoint(avion.getPositionX()+23,avion.getPositionY()+38);
			}
			if(avion.getPositionAvion()==1) { /*Position vers la droite */
				polygon.addPoint(avion.getPositionX()+25,avion.getPositionY()+1);
				polygon.addPoint(avion.getPositionX(),avion.getPositionY()+19);
				polygon.addPoint(avion.getPositionX()+48,avion.getPositionY()+28);
			}
			if(avion.getPositionAvion()==2) { /*Position vers la gauche */
				polygon.addPoint(avion.getPositionX()+24,avion.getPositionY()+2);
				polygon.addPoint(avion.getPositionX()+49,avion.getPositionY()+19);
				polygon.addPoint(avion.getPositionX()+3,avion.getPositionY()+30);
			}
		}
	}
}
