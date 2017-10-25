package com.mygdx.superpaperplane;

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
	private boolean pluie = false;
	public ShootingStar(int x, int y, Niveau niveau, EventSpawner e) {
		super(x, y, niveau);

		image = com.mygdx.superpaperplane.ImageBanque.getCaseImage(19);
		niveau.setBackground(image);
		objectType = ObjectType.FANTOM;
		this.e = e;
		this.niveau = niveau;
		niveau.setMusicPause();
		shootingStarMusic.play();
		needsToBeRemoved = false;
	}

	@Override
	public boolean remove() {
		if (needsToBeRemoved) {
			niveau.refreshBackground();
			shootingStarMusic.stop();
			niveau.setMusicPlay();
			niveau.allumerfeu();
			return true;
		}
		return false;
	}

	@Override
	public void dispose() {
		shootingStarMusic.dispose();
		image.dispose();
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
		if (needsToCreate) {
			needsToCreate = false;
		}
		if(pluie){
			if(e.shouldThisBeInsane(2)){
				needsToCreate = false;
			}
		}
		time++;
		if (time == 999) {
			e.setShootingStar(false);
			needsToBeRemoved = true;
		}
		if (!pluie) {
			if (e.shouldThisBeInsane(20000)) {
				pluie = true;
			} else if (e.shouldThisBeInsane(100)) {
				needsToCreate = true;
			}
		} else {
			needsToCreate = true;
		}
	}

	@Override
	public boolean canHit(GameObject g) {
		return false;
	}

}
