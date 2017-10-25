package com.mygdx.superpaperplane;

import com.badlogic.gdx.graphics.Texture;


public class Barre extends GameObject {
	private int number;
	private Niveau niveau;

	private Texture imageShootingStar;
	public Barre(int x, int y, Niveau niveau, Integer num) {
		super(0, 0, niveau);
		this.number = num;
		this.niveau = niveau;
		this.positionX = 280 + (int) (Math.random() * ((400 - 280) + 1));
		this.positionY = - 80;
		this.largeur = 600;
		this.hauteur = 80;
		image = com.mygdx.superpaperplane.ImageBanque.getCaseImage(17);
	}
	public void dispose(){

	}

	@Override
	public Texture getImage() {
		if (niveau.isShootingStar()) {
			return com.mygdx.superpaperplane.ImageBanque.getCaseImage(20);
		} else {
			return com.mygdx.superpaperplane.ImageBanque.getCaseImage(17);
		}
	}

	@Override
	public void deplacement(int vitesse) {
		
		this.setPositionY(this.getPositionY() + vitesse);
		if (getPositionY() >= 800 + 80) {
			
			setPositionY(0 - 80);	

			if (number == 0) {
				setPositionX(niveau.getPattern().getPatternX1());
			} else {
				setPositionX(niveau.getPattern().getPatternX2());
			}
		}
	}

	@Override
	public void whenGetHit() {
		niveau.getAvion().loosingLife();
	}

	@Override
	public boolean canHit(GameObject g) {
		if (g.getObjectType() == ObjectType.PLAYER)
			return true;
		return false;
	}
}
