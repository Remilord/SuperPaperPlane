package com.mygdx.superpaperplane;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;


public class Bonus extends GameObject {
	private int vitesse;
	private boolean mini;
	private Texture bonusactuel;
	private int etatnum;
	private Random rand;

	public Bonus(int x, int y, Niveau niveau) {
		super(0, 0, niveau);
		objectType = ObjectType.BONUS;
		needsToBeRemoved = false;
		this.rand = new Random();
		this.positionX = 280 + (int) (Math.random() * ((400 - 280) + 1));
		this.positionY = -50;
		this.largeur = 50;
		this.hauteur = 50;
		this.etatnum = rand.nextInt(3) + 1;
		this.setImageBonus(this.etatnum);
		this.vitesse = niveau.getVitesse() + 4;
		
		this.mini = false;
	}

	@Override
	public Texture getImage() {
		return bonusactuel;
	}

	public int getVitesseStatus() {
		return this.vitesse;
	}

	public boolean getMiniStatus() {
		return this.mini;
	}

	private void setImageBonus(int r) {
		if (r == 1) {
			this.bonusactuel = ImageBanque.getCaseImage(18);
		} else if (r == 2) {
			this.bonusactuel = ImageBanque.getCaseImage(19);
		} else {
			this.bonusactuel = ImageBanque.getCaseImage(20);
		}
	}

	public int getEtatNum() {
		return this.etatnum;
	}

	@Override
	public void deplacement(int vitesse) {
		
		this.setPositionY(this.getPositionY() + this.vitesse);
		if (getPositionY() > 850) {
			needsToBeRemoved = true;
		}
	}

	@Override
	public boolean remove() {
		return needsToBeRemoved;
	}

	@Override
	public void whenGetHit() {
		niveau.getAvion().setIsBonused(true);
		if (etatnum == 1) {
			niveau.getAvion().setInvincible(true);
		} else if (etatnum == 2) {
			niveau.setVitesse(12);
			niveau.getAvion().setHighSpeed(true);
		} else
			niveau.getAvion().setLittle(true);

		needsToBeRemoved = true;
	}

	@Override
	public boolean canHit(GameObject g) {
		if (g.getObjectType() == ObjectType.PLAYER)
			return true;
		return false;
	}
	@Override
	public void dispose(){
		bonusactuel.dispose();
	}

}
