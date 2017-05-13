package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;

public class Avion extends GameObject {
	private Texture avionImage;
	private Niveau niveau;
	private boolean isBonused = false;
	private boolean isShooting = false;
	private boolean isInvincible = false;
	private boolean isLittle = false;
	private boolean isHighSpeed = false;
	private Hitbox hitboxCreator = new Hitbox(this);
	private Polygon hitbox;
	private int nbBottles;
	
	private EventSpawner eventSpawner;
	private int position = 0;

	public Avion(int positionX, int positionY, Niveau niveau, EventSpawner e) {
		super(positionX, positionY, niveau);
		objectType = ObjectType.PLAYER;
		this.eventSpawner = e;
		this.niveau = niveau;
		position = 0;
		nbBottles = 0;
		this.largeur = 100;
		this.hauteur = 75;
		setPositionY(getPositionY() - hauteur - 20);
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setImageAvionActuel(int i) {
		this.avionImage = ImageBanque.getCaseImage(i);
	}

	public void setIsBonused(boolean b) {
		isBonused = b;
	}
	

	public boolean getIsBonused() {
		return isBonused;
	}

	public int getNumberBottles() {
		return nbBottles;
	}

	public void setNumberBottles(int number) {
		nbBottles = number;
	}

	public void setLittle(boolean b) {
		isLittle = b;
	}

	public boolean getIsLittle() {
		return isLittle;
	}

	public int getPositionAvion() {
		return position;
	}

	public boolean getIsInvincible() {
		return isInvincible;
	}

	@Override
	public Texture getImage() {
		return avionImage;
	}

	public void setIsShooting(boolean b) {
		this.isShooting = b;
	}

	public boolean getIsShooting() {
		return isShooting;
	}

	public void setInvincible(boolean b) {
		isInvincible = b;
	}

	@Override
	public boolean needsToCreate() {
		if (!isShooting && position == 0 && niveau.getEventSpawner().getIsDenis())
			return true;
		else
			return false;
	}

	@Override
	public GameObject createGameObject() {
		isShooting = true;
		return new Tir(getPositionX() + 40, getPositionY() - 100, niveau, eventSpawner);
	}

	@Override
	public void deplacement(int vitesse) {
		int bonusNum = 0;
		if (isInvincible)
			bonusNum = 1;
		else if (isLittle)
			bonusNum = 3;
		else if (isHighSpeed)
			bonusNum = 2;
		if (Gdx.app.getType() != Application.ApplicationType.Android) {
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				position = 1;
			} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				position = 2;
			} else {
				position = 0;
			}
		}

		if (position == 1) {
			if (getPositionX() > niveau.getPosMin()) {
				setPositionX(getPositionX() - niveau.getVitesse() + 2);
				setImageAvionActuel((bonusNum * 3) + 3);
				
				//positionAvion = 2;
			}
		} else if (position == 2) {
			if (getPositionX() < 500 - getLargeur()) {
				setPositionX(getPositionX() + niveau.getVitesse() - 2);
				setImageAvionActuel((bonusNum * 3) + 1);
				
				//positionAvion = 1;
			}
		} else {
			
			setImageAvionActuel((bonusNum * 3) + 2);
			position = 0;
		}

		if (eventSpawner.getShootingStar() && niveau.getInsane()) {
			if (getPositionX() > 505) {
				setPositionX(-50);
			} else if (getPositionX() < -75) {
				setPositionX(490);
			}
		}
	}

	@Override
	public void whenGetHit() {

	}

	public String toString() {
		return "Avion";
	}

	@Override
	public boolean canHit(GameObject g) {
		if (g.getObjectType() != ObjectType.PLAYER && g.getObjectType() != ObjectType.OFFENSIVE) {
			return true;
		}

		return false;
	}
	
	
	private Polygon createHitboxAvion(){
		hitbox = hitboxCreator.setHitboxAvion();
		return hitbox;
	}
	
	public Polygon getHitboxAvion(){
		createHitboxAvion();
		return hitbox;
	}
	

	// ANDROID

}
