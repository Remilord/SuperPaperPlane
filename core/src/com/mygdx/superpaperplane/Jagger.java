package com.mygdx.superpaperplane;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;


public class Jagger extends GameObject {
	private Texture jagger;
	private Random rand;
	private int time = 0;
	private int imageNumber = 0;
	private EventSpawner eventSpawner;
	private static Music gechar = Gdx.audio.newMusic(Gdx.files.internal("son/j_char.ogg"));
	private static Music ahah = Gdx.audio.newMusic(Gdx.files.internal("son/rire.ogg"));

	public Jagger(int x, int y, Niveau niveau, EventSpawner eventSpawner) {
		super(x, y, niveau);
		objectType = ObjectType.BONUS;
		this.eventSpawner = eventSpawner;
		this.jagger = ImageBanque.getCaseImage(32);
		this.rand = new Random();
		this.positionX = 500 + rand.nextInt(50);
		this.positionY = -80 - rand.nextInt(50);
		this.hauteur = 120;
		this.largeur = 80;
		
	}

	@Override
	public void dispose(){
		gechar.dispose();
		ahah.dispose();
		jagger.dispose();
	}

	public void setImageJagger(int n) {
		this.jagger = ImageBanque.getCaseImage(32 + n);
	}

	public Texture getImage() {
		return this.jagger;
	}

	@Override
	public void deplacement(int vitesse) {
		setPositionY(getPositionY() + (vitesse + 5));
		setPositionX(getPositionX() - 5);
		if ((getPositionX() < -80) || (getPositionY() > 880)) {
                this.needsToBeRemoved=true;
		}
		if ((time == 5) || (time == 10) || (time == 15) || (time == 20)) {
			imageNumber = imageNumber + 1;
			setImageJagger(imageNumber);
		}
		if (time == 25) {
			imageNumber = 0;
			time = 0;
		}
		time = time + 1;
	}

	@Override
	public void whenGetHit() {
		int nb = niveau.getAvion().getNumberBottles();
		if (nb == 1) {
			if (!eventSpawner.getShootingStar()) {
				ImageBanque.imageFlou();
				niveau.refreshBackground();
				ahah.play();
			}
		}
		else{
			gechar.play();
		}
		niveau.getAvion().setNumberBottles(nb + 1);
		needsToBeRemoved = true;
	}

	@Override
	public boolean canHit(GameObject g) {
		if (g.getObjectType() == ObjectType.PLAYER)
			return true;
		return false;
	}
}
