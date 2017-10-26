package com.mygdx.superpaperplane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class Menu implements Screen {
	private Texture ecrandebut;
	private Texture ecranchoix;
	private Texture ecrancode;
	private Texture ecran;
	private Texture ecranchargement;
	private int numecran;
	private Skin sk;
	private Stage scen;
	private OrthographicCamera camera;
	private SpriteBatch sbatch;
	private TextField txt;
	private static Music start = Gdx.audio.newMusic(Gdx.files.internal("son/accueil.ogg"));
	Niveau game;

	public Menu(Niveau g) {
		this.game = g;
		numecran = 0;
		ecrandebut = Niveau.loadBufferedImage("image/ecran/EcranDebutDeJeu.png");
		ecranchargement = Niveau.loadBufferedImage("image/ecran/EcranChargement.png");
		ecranchoix = Niveau.loadBufferedImage("image/ecran/ChoixDuNiveau.png");
		ecrancode = Niveau.loadBufferedImage("image/ecran/EcranDeCode.png");
		ecran = ecranchargement;
		sk = new Skin(Gdx.files.internal("ui/comic-ui.json"));
		scen = new Stage();
		sbatch = new SpriteBatch();
		camera = new OrthographicCamera(500, 800);
		camera.position.set(500 / 2, 800 / 2, 0);
		txt = new TextField("Code", this.sk);
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		camera.update();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.sbatch.setProjectionMatrix(camera.combined);
		this.sbatch.begin();
		if (Gdx.input.getY() > Gdx.app.getGraphics().getHeight() / 2.1
				&& Gdx.input.getY() < Gdx.app.getGraphics().getHeight() / 1.8 && Gdx.input.justTouched()
				&& numecran == 1) {
			ecran = ecranchoix;
			numecran = 2;
		} else if (Gdx.input.getY() > Gdx.app.getGraphics().getHeight() / 1.6
				&& Gdx.input.getY() < Gdx.app.getGraphics().getHeight() / 1.4 && Gdx.input.justTouched()
				&& numecran == 1) {
			ecran = ecrancode;

			txt.setPosition(0, (float) (Gdx.app.getGraphics().getHeight() / 2.6));
			txt.setSize(Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight() / 8);
			scen.addActor(txt);
			Gdx.input.setInputProcessor(scen);
			numecran = 3;
		} else if (Gdx.input.getY() > Gdx.app.getGraphics().getHeight() / 1.3
				&& Gdx.input.getY() < Gdx.app.getGraphics().getHeight() / 1.1 && Gdx.input.justTouched()
				&& numecran == 1) {
			System.exit(0);
		} else if (Gdx.input.getY() > Gdx.app.getGraphics().getHeight() / 3
				&& Gdx.input.getY() < Gdx.app.getGraphics().getHeight() / 2 && Gdx.input.justTouched()
				&& numecran == 2) {
			if (com.mygdx.superpaperplane.ImageBanque.isChanged()) {
				com.mygdx.superpaperplane.ImageBanque.InitialiserImageBanque();
				this.game.refreshBackground();
			}
			this.game.setScreen(this.game);
			this.game.addFire();
			this.game.activeListener();
		} else if (Gdx.input.getY() > Gdx.app.getGraphics().getHeight() / 1.5
				&& Gdx.input.getY() < Gdx.app.getGraphics().getHeight() / 1.25 && Gdx.input.justTouched()
				&& numecran == 2) {
			if (com.mygdx.superpaperplane.ImageBanque.isChanged()) {
				com.mygdx.superpaperplane.ImageBanque.InitialiserImageBanque();
				this.game.refreshBackground();

			}
			this.game.setScreen(this.game);
			this.game.addFire();
			this.game.activeListener();

		} else if (Gdx.input.getY() > Gdx.app.getGraphics().getHeight() / 1.6
				&& Gdx.input.getY() < Gdx.app.getGraphics().getHeight() / 1.45
				&& Gdx.input.getX() > Gdx.app.getGraphics().getWidth() / 2.8
				&& Gdx.input.getX() < Gdx.app.getGraphics().getWidth() / 1.6 && Gdx.input.justTouched()
				&& numecran == 3) {
			ecran = ecrandebut;
			numecran = 1;
			String st = txt.getText();
			if (st.equals("911")) {
				com.mygdx.superpaperplane.ImageBanque.setValue(1);
			} else if (st.equals("Twenty One Pilots")) {
				com.mygdx.superpaperplane.ImageBanque.setValue(2);
			} else if (st.equals("Ce genre de bose")) {
				com.mygdx.superpaperplane.ImageBanque.setValue(3);
			}else if(st.equals("I'm an Albatraoz")) {
				com.mygdx.superpaperplane.ImageBanque.setValue(4);
		}else {
				com.mygdx.superpaperplane.ImageBanque.setValue(0);
			}
			scen.clear();

		}
		this.sbatch.draw(ecran, 0, 0);
		this.sbatch.end();
		scen.draw();
		if(numecran==0) {
			Gdx.graphics.setContinuousRendering(false);
			start.play();
			if(Gdx.input.justTouched()) {
				numecran = 1;
				ecran = ecrandebut;
				Gdx.graphics.requestRendering();
				Gdx.graphics.setContinuousRendering(true);
				start.dispose();
			}
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}
}
