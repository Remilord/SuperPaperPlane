package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;



public class Ah extends GameObject {
	private EventSpawner eventSpawner;
	private static Music ah = Gdx.audio.newMusic(Gdx.files.internal("son/ah.ogg"));
	public Ah(int x, int y, Niveau niveau, EventSpawner e) {
		super(x, y, niveau);
		eventSpawner = e;
		this.largeur = 40;
		this.hauteur = 30;
		ah.play();
	}

	@Override
	public Texture getImage() {
		return ImageBanque.getCaseImage(40);
	}

	@Override
	public void deplacement(int vitesse) {
		setPositionY(getPositionY() + 30);
	}

	@Override
	public boolean remove() {
		if (getPositionY() >= 860) {
			eventSpawner.setIsDenisShouting(false);
			return true;
		}
		return false;
	}

	@Override
	public void whenGetHit() {
		niveau.loose();
	}

	@Override
	public boolean canHit(GameObject g) {
		if (g.getObjectType() == ObjectType.PLAYER) {
			return true;
		}
		return false;
	}

}
