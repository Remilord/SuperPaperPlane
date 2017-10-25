package com.mygdx.superpaperplane;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;


public class Ah extends GameObject {
	private EventSpawner eventSpawner;
	private static Music ah = Gdx.audio.newMusic(Gdx.files.internal("son/ah.ogg"));
	private Texture image;
	public Ah(int x, int y, Niveau niveau, EventSpawner e) {
		super(x, y, niveau);
		eventSpawner = e;
		this.largeur = 40;
		this.hauteur = 30;
		ah.play();
		image = ImageBanque.getCaseImage(44);
	}

	@Override
	public Texture getImage() {
		return image;
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
		niveau.getAvion().loosingLife();
	}

	@Override
	public boolean canHit(GameObject g) {
		if (g.getObjectType() == ObjectType.PLAYER) {
			return true;
		}
		return false;
	}
	
	@Override
	public void dispose(){
		image.dispose();
		ah.dispose();
	}

}
