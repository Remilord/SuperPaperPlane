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
	public void setEtat(int e,GameObject a) {
		if(e==1) {
			this.addPoint(a.getPositionX()+52,a.getPositionY()+1);
		       this.addPoint(a.getPositionX(),a.getPositionY()+44);
		       this.addPoint(a.getPositionX()+100,a.getPositionY()+73);
		}
		if(e==2) {
		       this.addPoint(a.getPositionX()+50,a.getPositionY()+4);
		       this.addPoint(a.getPositionX()+97,a.getPositionY()+47);
		       this.addPoint(a.getPositionX()+3,a.getPositionY()+72);
		}
		
		if(e==0) {
		       this.addPoint(a.getPositionX()+29,a.getPositionY()+7);
		       this.addPoint(a.getPositionX()+78,a.getPositionY()+6);
		      this.addPoint(a.getPositionX()+47,a.getPositionY()+74);
		}
	}
	public boolean detectCollision(ArrayList<GameObject> obj) {
		int i=0;
		for(i=0;i<obj.size()-1;i++) {
			if(this.intersects(obj.get(i).getPositionX(),obj.get(i).getPositionY(),obj.get(i).getLargeur(),obj.get(i).getHauteur())) {
				return true;
			}
		}
		return false;
	}
}
