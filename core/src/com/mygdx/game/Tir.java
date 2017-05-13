package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;



public class Tir extends GameObject {
	private Niveau niveau;
	private EventSpawner eventSpawner;

	public Tir(int x, int y, Niveau niveau, EventSpawner eventSpawner) {
		super(x, y, niveau);
		objectType = ObjectType.OFFENSIVE;
		this.largeur = 15;
		this.hauteur = 30;
		this.eventSpawner = eventSpawner;
		this.niveau = niveau;
	}

	@Override
	public Texture getImage() {
		return ImageBanque.getCaseImage(39);
	}

	@Override
	public void deplacement(int vitesse) {
		if (eventSpawner.getIsDenis() && niveau.getAvion().getIsShooting()) {
			setPositionY(getPositionY() - 25);
		}
		if (!eventSpawner.getIsDenis())
			needsToBeRemoved = true;
	}

	@Override
	public boolean remove() {
		if (needsToBeRemoved) {
			niveau.getAvion().setIsShooting(false);
			return true;
		}
		if (getPositionY() < -20) {
			niveau.getAvion().setIsShooting(false);
			return true;
		}
		return false;
	}

	@Override
	public void whenGetHit() {
		needsToBeRemoved = true;
	}

	@Override
	public boolean canHit(GameObject g) {
		if (g.getObjectType() == ObjectType.ENEMY && !needsToBeRemoved) {
			return true;
		}
		return false;
	}

}
