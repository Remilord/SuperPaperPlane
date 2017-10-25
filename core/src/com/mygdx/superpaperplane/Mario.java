package com.mygdx.superpaperplane;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;


public class Mario extends GameObject  {
	private Texture mario;
	private int timer;
	private int actualImageNumber;
	private EventSpawner eventSpawner;
	private boolean really_insane;
	private static Music music;
	public Mario(int positionX, int positionY, Niveau niveau, EventSpawner e, boolean really_insane) {
		super(positionX,positionY, niveau);
		objectType = ObjectType.BONUS;
		this.really_insane = really_insane;
		if(really_insane){
			this.mario = niveau.loadBufferedImage("image/normal/grillepain.png");
			this.music = Gdx.audio.newMusic(Gdx.files.internal("son/grillepain.ogg"));
		} else {
			this.music = Gdx.audio.newMusic(Gdx.files.internal("son/Non.ogg"));
			mario = com.mygdx.superpaperplane.ImageBanque.getCaseImage(25);
			actualImageNumber = 0;
			setImageMarioActuel(actualImageNumber);
		}
		timer = 0;

		eventSpawner = e;
		eventSpawner.setIsMario(true);
		this.largeur=250;
		this.hauteur=400;
		music.play();
	}

	@Override
	public void dispose(){
		music.dispose();
		mario.dispose();
	}
	@Override
	public void deplacement(int vitesse){
		timer++;
		if(!really_insane){
			if(timer == 150) {
				needsToBeRemoved = true;
			} else if ((timer == 15) || (timer == 30) || (timer == 45) || (timer == 60)) {
				actualImageNumber++;
				setImageMarioActuel(actualImageNumber);
			}
		} else {
			if(timer == 220) {
				needsToBeRemoved = true;
			}
		}


	}


	public void setImageMarioActuel(int n){
		this.mario = com.mygdx.superpaperplane.ImageBanque.getCaseImage(25+n);
	}

	@Override
	public boolean remove(){
		if(needsToBeRemoved){
			eventSpawner.setIsMario(false);
			return true;
		}
		return false;
	}

	@Override
	public Texture getImage() {
		return mario;
	}

	@Override
	public void whenGetHit(){

	}
}
