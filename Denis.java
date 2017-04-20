import javax.swing.*;
import java.awt.image.*;
import java.awt.*;
import java.io.File;
@SuppressWarnings("serial")
public class Denis extends GameObject {
	private BufferedImage denis;
	private BufferedImage[] animationdenis;
	private int hp;
	private boolean dennisToTheLeft = false; // Booleen pour la ronde de denis gauche vers droite pour false  puis droite vers gauche pour true

	public Denis(int x,int y, Niveau niveau) {
		super(x, y, niveau);
		objectType = ObjectType.ENEMY;
		niveau.setIsDenisAlive(true);
		denis = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"denis"+File.separator+"Denis0.png");
		animationdenis=new BufferedImage[6];
		for(int a=0;a<6;a++) {
			animationdenis[a]=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"denis"+File.separator+"Denis"+a+".png");
		}
		this.largeur=100;
		this.hauteur=100;
		this.hp = 3;
	}
	public void setImageDenisActuel(int n) {
		this.denis=animationdenis[n];
	}
	@Override
	public BufferedImage getImage() {
		return this.denis;
	}

	@Override
	public void deplacement(int vitesse){
		if(dennisToTheLeft) {
			setPositionX(getPositionX()-3);
		} else {
			setPositionX(getPositionX()+3);
		}

		if(getPositionX() >= 405) {
			dennisToTheLeft = true;
		}else if(getPositionX() <= 0 ){
			dennisToTheLeft = false;
		}
	}

	@Override
	public boolean remove(){
		if(hp == 0){
			niveau.setIsDenisAlive(false);
			return true;
		}
		return false;
	}

	@Override
	public void whenGetHit(){
		System.out.println("Removing hp");
		if(hp > 0){
			hp--;
		}
	}

	@Override
	public boolean canHit(GameObject g){
		if(g.getObjectType() == ObjectType.OFFENSIVE)
			return true;
		return false;
	}

}
