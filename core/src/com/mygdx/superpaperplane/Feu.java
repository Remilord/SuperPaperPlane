package com.mygdx.superpaperplane;
import com.badlogic.gdx.graphics.Texture;


public class Feu extends GameObject {
		private boolean eteint;
	public Feu(int x, int y, Niveau niveau) {
		super(x, y, niveau);
		this.largeur = 45;
		this.hauteur = 800;
		if (this.getPositionX() == 0) {
			image = ImageBanque.getCaseImage(31);
		} else {
			image = ImageBanque.getCaseImage(30);
		}
		eteint = false;
	}
	@Override
	public void dispose(){
		image.dispose();
	}
	@Override
	public Texture getImage() {
		return image;
	}

	public void eteindrefeu() {
		eteint = true;
		image = Niveau.loadBufferedImage("image/invulnerable.png");;
	}
	public void allumerfeu() {
		eteint = false;
		if (this.getPositionX() == 0) {
			image = ImageBanque.getCaseImage(31);
		} else {
			image = ImageBanque.getCaseImage(30);
		}
	}
	@Override
	public void whenGetHit() {
		if(!eteint) {
			niveau.getAvion().loosingLife();
		}
	}

	@Override
	public boolean canHit(GameObject g) {
		if (g.getObjectType() == ObjectType.PLAYER)
			return true;
		return false;
	}

}
