package com.mygdx.superpaperplane;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;

public class Fillon extends GameObject {
	/**
	 * 
	 */

	private Texture fillon;
	private Random rand;
	private int timer;
	private int actualImageNumber;

	public Fillon(int x, int y, Niveau niveau, EventSpawner e) {
		super(x, y, niveau);
		objectType = ObjectType.BONUS;
		this.fillon = com.mygdx.superpaperplane.ImageBanque.getCaseImage(41);
		this.rand = new Random();
		this.positionX = -350 + rand.nextInt(900);
		this.positionY = -80 ;
		this.actualImageNumber = 0;
		this.hauteur = 150;
		this.largeur = 125;
	}
	@Override
	public void dispose(){
		fillon.dispose();
	}
	public void resetPosition() {
		this.positionX = -750 + rand.nextInt(900);
		this.positionY = 1000 + rand.nextInt(1000);
	}

	public void setImageFillonActuel(int n) {
		this.fillon = com.mygdx.superpaperplane.ImageBanque.getCaseImage(41 + n);
	}

	public Texture getImage() {
		return this.fillon;
	}

	@Override
	public boolean remove() {
		if(positionY > 900)
			return true;
		return false;
	}

	@Override
	public void deplacement(int vitesse) {
		timer++;
		if (timer == 90) {
			timer = 0;
			this.actualImageNumber = 0;
			setImageFillonActuel(actualImageNumber);
		} else if ((timer == 15) || (timer == 30) || (timer == 45) || (timer == 60) || (timer == 75)) {
			actualImageNumber++;
			setImageFillonActuel(actualImageNumber);
		}
		setPositionY(getPositionY() + (vitesse + 5));
		setPositionX(getPositionX() + 3);
		if ((getPositionX() <= 0 - 125) || (getPositionY() <= 0 - 150)) {
			resetPosition();
		}
	}

	@Override
	public void whenGetHit() {
		resetPosition();
		niveau.lessScore();
	}

	@Override
	public boolean canHit(GameObject g) {
		if (g.getObjectType() == ObjectType.PLAYER)
			return true;
		return false;
	}
}
