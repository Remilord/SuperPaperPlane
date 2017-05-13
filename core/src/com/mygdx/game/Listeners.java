package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class Listeners implements InputProcessor {
	private Avion avion;

	public Listeners(Avion avion) {
		this.avion = avion;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (x > Gdx.app.getGraphics().getWidth() / 2) {
			avion.setPosition(2);
		}
		else if(x < Gdx.app.getGraphics().getWidth() / 2){
			avion.setPosition(1);
		}
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		avion.setPosition(0);
		return false; 
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		return false;
	}

	public boolean touchMoved(int x, int y) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

}

// if(x>Gdx.app.getGraphics().getWidth()/2){position=2;}}else position=0;
