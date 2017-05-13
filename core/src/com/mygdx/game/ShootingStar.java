package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class ShootingStar extends GameObject {
	/**
	 * 
	 */
	
	private EventSpawner e;
	private boolean needsToCreate;
	private Niveau niveau;
	private Music shootingStarMusic = Gdx.audio.newMusic(Gdx.files.internal("son/shootingstar.ogg"));
	private int time = 0;

	public ShootingStar(int x, int y, Niveau niveau, EventSpawner e) {
		super(x, y, niveau);

		img = ImageBanque.getCaseImage(16);
		niveau.setBackground(img);
		objectType = ObjectType.FANTOM;
		this.e = e;
		this.niveau = niveau;
		shootingStarMusic.play();
		System.out.println("Creating ss");
		needsToBeRemoved = false;
	}

	@Override
	public boolean remove() {
		if (needsToBeRemoved) {
			img = ImageBanque.getCaseImage(15);
			shootingStarMusic.stop();
			return true;
		}
		return false;
	}

	@Override
	public void whenGetHit() {
	}

	@Override
	public boolean needsToCreate() {
		return needsToCreate;
	}

	@Override
	public GameObject createGameObject() {
		return new Fillon(0, 0, niveau, e);
	}

	@Override
	public void deplacement(int vitesse) {
		if(needsToCreate){
			needsToCreate = false;
		}
		time++;
		if (time == 2000) {
			e.setShootingStar(false);
			needsToBeRemoved = true;
		}
		if (e.shouldThisBeInsane(100)) {
			needsToCreate = true;
		}
	}

	@Override
	public boolean canHit(GameObject g) {
		return false;
	}

	/*@Override
	public Polygon getHitbox() {
		return new Polygon();
	}*/

}
