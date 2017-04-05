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

public class Hitbox extends Polygon {
	public Hitbox() {
		super();
	}
	public void setEtat(int pos,GameObject a,boolean mini) {
		if(!(mini)) {
			if(pos==0) { /*Position vers le bas */
			       this.addPoint(a.getPositionX()+29,a.getPositionY()+7);
			       this.addPoint(a.getPositionX()+78,a.getPositionY()+6);
			      this.addPoint(a.getPositionX()+47,a.getPositionY()+74);
			}
		if(pos==1) { /*Position vers la droite*/
			this.addPoint(a.getPositionX()+52,a.getPositionY()+1);
		       this.addPoint(a.getPositionX(),a.getPositionY()+44);
		       this.addPoint(a.getPositionX()+100,a.getPositionY()+73);
		}
		if(pos==2) { /*Position vers la gauche*/
		       this.addPoint(a.getPositionX()+50,a.getPositionY()+4);
		       this.addPoint(a.getPositionX()+97,a.getPositionY()+47);
		       this.addPoint(a.getPositionX()+3,a.getPositionY()+72);
		}
		
		} else if(mini) {
			if(pos==0) { /*Position vers le bas */
			       this.addPoint(a.getPositionX()+14,a.getPositionY()+3);
			       this.addPoint(a.getPositionX()+40,a.getPositionY()+2);
			      this.addPoint(a.getPositionX()+23,a.getPositionY()+38);
			}
			if(pos==1) { /*Position vers la droite */
				this.addPoint(a.getPositionX()+25,a.getPositionY()+1);
			       this.addPoint(a.getPositionX(),a.getPositionY()+19);
			       this.addPoint(a.getPositionX()+48,a.getPositionY()+28);
			}
			if(pos==2) { /*Position vers la gauche */
			       this.addPoint(a.getPositionX()+24,a.getPositionY()+2);
			       this.addPoint(a.getPositionX()+49,a.getPositionY()+19);
			       this.addPoint(a.getPositionX()+3,a.getPositionY()+30);
			}
		}
	}
	public int detectCollision(ArrayList<GameObject> obj) {
		int i=0;
		for(i=0;i<obj.size()-1;i++) {
			if(this.intersects(obj.get(i).getPositionX(),obj.get(i).getPositionY(),obj.get(i).getLargeur(),obj.get(i).getHauteur())) {
				if((obj.get(i) instanceof Barre)||(obj.get(i) instanceof Feu)) {
				return 1;
				}else if(obj.get(i) instanceof Bonus) {
					return 2;
				}
			}
		}
		return 0;
	}
}
