package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;


public class Mario extends GameObject  {
	private Texture mario;
	private int timer;
	private int actualImageNumber;
	private EventSpawner eventSpawner;
	private static Music music = Gdx.audio.newMusic(Gdx.files.internal("son/Non.ogg"));
	public Mario(int positionX,int positionY, Niveau niveau, EventSpawner e) {
		super(positionX,positionY, niveau);
		objectType = ObjectType.BONUS;
		mario = ImageBanque.getCaseImage(21);
		timer = 0;
		actualImageNumber = 0;
		setImageMarioActuel(actualImageNumber);
		eventSpawner = e;
		eventSpawner.setIsMario(true);
		this.largeur=250;
		this.hauteur=400;
		music.play();
	}


	@Override
	public void deplacement(int vitesse){
		timer++;
		
		if(timer == 150) {
			needsToBeRemoved = true;
		} else if ((timer == 15) || (timer == 30) || (timer == 45) || (timer == 60)) {
			actualImageNumber++;
			setImageMarioActuel(actualImageNumber);
		}

	}


	public void setImageMarioActuel(int n){
		this.mario = ImageBanque.getCaseImage(21+n);
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
