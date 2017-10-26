package com.mygdx.superpaperplane;

import java.io.File;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;

public class Avion extends GameObject {
	private Texture avionImage;
	private Texture avionImageInvulnerable = Niveau.loadBufferedImage("image" + File.separator + "invulnerable.png");
	private Niveau niveau;
	private boolean isBonused = false;
	private boolean isShooting = false;
	private boolean isInvincible = false;
	private boolean isLittle = false;
	private boolean isHighSpeed = false;
	private boolean isMaxScore = false;
	private boolean isFantom = false;
	private Hitbox hitboxCreator = new Hitbox(this);
	private Polygon hitbox;
	private int nbBottles;
	private int nombreDeViesRestantes = 3;
	private EventSpawner eventSpawner;
	private int position = 0;
	private boolean justBeenHit = false;
	private int invulnerabilityTimer = 0;
	private int timerInvincible = 0;
	private int timerLittle = 0;
	private int timerHightSpeed = 0;
	private int timerMaxScore = 0;
	private int timerBottle = 0;
	private int timerFantom = 0;
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
	public void dispose(){
		avionImage.dispose();
		avionImageInvulnerable.dispose();
	}
	public int getNombreDeViesRestantes() {
		return nombreDeViesRestantes;
	}

	public void setNombreDeViesRestantes(int nombreDeViesRestantes) {
		this.nombreDeViesRestantes = nombreDeViesRestantes;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setImageAvionActuel(int i) {
		this.avionImage = com.mygdx.superpaperplane.ImageBanque.getCaseImage(i);
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
	public boolean getIsHighSpeed(){
		return isHighSpeed;
	}
	public void setHighSpeed(boolean b){isHighSpeed = b;}
	public void setMaxScore(boolean b) { isMaxScore = b;}
	public boolean getIsMaxScore() {return isMaxScore;}
	public boolean getIsLittle() {
		return isLittle;
	}

	public int getPositionAvion() {
		return position;
	}

	public boolean getIsInvincible() {
		return isInvincible;
	}
	public void setisFantom(boolean b) {
		this.isFantom=b;
	}
	public boolean isFantom() {
		return this.isFantom;
	}
	@Override
	public Texture getImage() {
		if (justBeenHit && invulnerabilityTimer % 10 > 5 || isFantom()) {
			return avionImageInvulnerable;
		}
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
		
		if(isInvincible){
			timerInvincible++;
			if(timerInvincible == 350){
				isInvincible = false;
				timerInvincible = 0;
				isBonused = false;
			}
		}
		if(isLittle){
			timerLittle++;
			if(timerLittle == 500){
				isLittle = false;
				isBonused = false;
				timerLittle = 0;
			}
		}
		if(isHighSpeed){
			timerHightSpeed++;
			if(timerHightSpeed == 500){
				isHighSpeed = false;
				isBonused = false;
				timerHightSpeed = 0;
			}
		}
		if(isMaxScore){
			timerMaxScore++;
			if(timerMaxScore == 300){
				isMaxScore = false;
				isBonused = false;
				timerMaxScore = 0;
			}
		}
		if(nbBottles > 0){
			timerBottle++;
			if(timerBottle == 500){
				nbBottles--;
				timerBottle=0;
			}
		}
		if(isFantom){
			timerFantom++;
			if(timerFantom == 333){
				isFantom = false;
				timerFantom=0;
			}
		}
		if (justBeenHit) {
			invulnerabilityTimer++;
			if(invulnerabilityTimer == 60){
				justBeenHit = false;
				invulnerabilityTimer = 0;
			}
		}
		int bonusNum = 0;
		if (isInvincible)
			bonusNum = 1;
		else if (isLittle)
			bonusNum = 3;
		else if (isHighSpeed)
			bonusNum = 2;
		else if (isMaxScore)
			bonusNum = 4;
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

				// positionAvion = 2;
			}
		} else if (position == 2) {
			if (getPositionX() < 500 - getLargeur()) {
				setPositionX(getPositionX() + niveau.getVitesse() - 2);
				setImageAvionActuel((bonusNum * 3) + 1);

				// positionAvion = 1;
			}
		} else {

			setImageAvionActuel((bonusNum * 3) + 2);
			position = 0;
		}

		if (eventSpawner.getShootingStar() && niveau.getInsane()) {
			if (getPositionX() > 390) {
				setPositionX(25);
			} else if (getPositionX() < 25) {
				setPositionX(390);
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

	private Polygon createHitboxAvion() {
		hitbox = hitboxCreator.setHitboxAvion();
		return hitbox;
	}

	public Polygon getHitboxAvion() {
		createHitboxAvion();
		return hitbox;
	}

	public void loosingLife() {
		if(!justBeenHit && !isInvincible){
			justBeenHit = true;
			nombreDeViesRestantes--;
		}
			
		if (nombreDeViesRestantes == 0){
			setImageAvionActuel(16);
			niveau.setDefaite(true);
		}
	}
	// ANDROID

}
