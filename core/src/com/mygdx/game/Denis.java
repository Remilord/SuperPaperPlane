package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;


public class Denis extends GameObject {
	private Texture denis;
	private int hp;
	private int timer;
	private int actualImageNumber;
	private boolean dennisToTheLeft = false; // Booleen pour la ronde de denis
												// gauche vers droite pour false
												// puis droite vers gauche pour
												// true
	private EventSpawner eventSpawner;

	public Denis(int x, int y, Niveau niveau, EventSpawner e) {
		super(x, y, niveau);
		objectType = ObjectType.ENEMY;
		eventSpawner = e;
		e.setIsDenis(true);
		timer = 0;
		actualImageNumber = 0;
		denis = ImageBanque.getCaseImage(33);
		this.largeur = 100;
		this.hauteur = 100;
		this.hp = 3;
	}

	public void setImageDenisActuel(int n) {
		this.denis = ImageBanque.getCaseImage(33 + n);
	}

	@Override
	public Texture getImage() {
		return this.denis;
	}

	@Override
	public boolean needsToCreate() {
		if (!eventSpawner.getIsDenisShouting() && timer == 30)
			return true;
		else
			return false;
	}

	@Override
	public GameObject createGameObject() {
		eventSpawner.setIsDenisShouting(true);
		return new Ah(getPositionX(), getPositionY(), niveau, eventSpawner);
	}

	@Override
	public void deplacement(int vitesse) {
		timer++;
		if (dennisToTheLeft) {
			setPositionX(getPositionX() - 3);
		} else {
			setPositionX(getPositionX() + 3);
		}

		if (getPositionX() >= 405) {
			dennisToTheLeft = true;
		} else if (getPositionX() <= 0) {
			dennisToTheLeft = false;
		}
		if (timer == 15 || timer == 30 || timer == 45 || timer == 60) {
			if (actualImageNumber == 1) {

			}
			actualImageNumber++;
		} else if (timer == 75) {
			timer = 0;
			actualImageNumber = 0;
		}
		setImageDenisActuel(actualImageNumber);
	}

	@Override
	public boolean remove() {
		if (hp == 0) {
			eventSpawner.setIsDenis(false);
			return true;
		}
		return false;
	}

	@Override
	public void whenGetHit() {
		if (hp > 0) {
			hp--;
		}
	}

	@Override
	public boolean canHit(GameObject g) {
		if (g.getObjectType() == ObjectType.OFFENSIVE) {
			return true;
		}
		return false;
	}

}
