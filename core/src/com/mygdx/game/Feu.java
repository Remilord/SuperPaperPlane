package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;


public class Feu extends GameObject {
	public Feu(int x, int y, Niveau niveau) {
		super(x, y, niveau);
		this.largeur = 50;
		this.hauteur = 800;
	}

	@Override
	public Texture getImage() {
		if (this.getPositionX() == 0) {
			return ImageBanque.getCaseImage(27);
		} else {
			return ImageBanque.getCaseImage(26);
		}
	}

	@Override
	public void whenGetHit() {
		niveau.loose();
	}

	@Override
	public boolean canHit(GameObject g) {
		if (g.getObjectType() == ObjectType.PLAYER)
			return true;
		return false;
	}

}
