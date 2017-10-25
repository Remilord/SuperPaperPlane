package com.mygdx.superpaperplane;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class Niveau extends Game implements Screen {
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private int score = 0;
	private int vitesse = 8;

	private Subbar subbar;
	private com.mygdx.superpaperplane.Avion avion;

    private com.mygdx.superpaperplane.Feu feugauche;
    private com.mygdx.superpaperplane.Feu feudroite;
	private Texture background;
	private Texture hearth;
	private boolean defaite;
	private int entreeUtilisateur;
	protected int entree_utilisateur = 0;
	private int posmin = 0;
	private Pattern pattern;
	private boolean insane;
	private int actualFrame;
	private com.mygdx.superpaperplane.EventSpawner eventSpawner = new com.mygdx.superpaperplane.EventSpawner(this);
	SpriteBatch batch;
	private BitmapFont bitmapFont;
	OrthographicCamera camera;
	private Music music;
	private boolean isPlaying;
	private ShapeRenderer shapeRenderer;
	private int cameraBaseX = 500 / 2;
	private int cameraBaseY = 800/2;
	private Shake shake = new Shake(3.6f, cameraBaseX, cameraBaseY);
	//Shaking camera

	private int elapsed = 0;
	private int duration = 0;

	@Override
	public void resize(int width, int height) {
	}; /*
		 * on ecrase cette methode sinon ça bug , mais on peut quand meme
		 * redimensionner la fenetre
		 */

	@Override
	public void create() {

		this.setScreen(new Menu(this));
		BitmapFont font = new BitmapFont();
		font.getData().setScale(3);
		bitmapFont = font;
		avion = new com.mygdx.superpaperplane.Avion(250, 750, this, eventSpawner);
		addNewObject(avion);
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		com.mygdx.superpaperplane.ImageBanque.InitialiserImageBanque();
		/* On charge les images de base du niveau */
		background = com.mygdx.superpaperplane.ImageBanque.getCaseImage(18);
		hearth = loadBufferedImage("image" + File.separator + "coeur.png");
		pattern = new Pattern();
		pattern.generatePattern();
		Barre barre = new Barre(0, 800, this, 0);
		this.addNewObject(barre);
		barre.setPositionX(pattern.getPatternX1());
		Barre barre2 = new Barre(0, 800, this, 1);
		this.addNewObject(barre2);
		barre2.setPositionX(pattern.getPatternX2());
		camera = new OrthographicCamera(500, 800);
		camera.position.set(cameraBaseX, cameraBaseY, 0);
		isPlaying = false;
		subbar = null;


	}

	public void setInsane(boolean b) {
		insane = b;
	}

	public boolean isShootingStar() {
		return eventSpawner.getShootingStar();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void show() {

	}

	@Override
	public void pause() {
	}

	public void setMusicPause() {
		music.pause();
	}

	public void setMusicPlay() {
		music.play();
	}

	public Shake getShake(){
		return this.shake;
	}
	public OrthographicCamera getCamera(){
		return this.camera;
	}
	@Override
	public void render(float delta) {
		if(score == 0 && shake.getIsShaking()){
			shake.setIsShaking(false);
		}
		if (subbar == null) {
			subbar = new Subbar(this);
		}

		if (!isPlaying) {
			isPlaying = true;
			if (com.mygdx.superpaperplane.ImageBanque.getValue() == 3) {
				music = Gdx.audio.newMusic(Gdx.files.internal("son/weed.ogg"));
			} else if (com.mygdx.superpaperplane.ImageBanque.getValue() == 4) {
				music = Gdx.audio.newMusic(Gdx.files.internal("son/albatraoz.ogg"));
			} else {
				music = Gdx.audio.newMusic(Gdx.files.internal("son/upset.ogg"));
		}
			music.isLooping();
			music.play();
		}

		camera.update();
		if (avion.getIsHighSpeed())
			vitesse = 12;
		else
			vitesse = 8;
		if((actualFrame == 30 || actualFrame == 60)&&(avion.getIsHighSpeed())) {
			score++;
		}
		if (actualFrame == 60) {
			actualFrame = 0;
			if(avion.getIsMaxScore()) {
				score=score+2;
			}else if(!avion.getIsHighSpeed()) {
				score++;
			}
		}
		actualFrame++;

		pattern.generatePattern();
		if (insane && score != 0) {
			eventSpawner.calculateEvents();
		}
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.rect(0,0,2500,2500);
		shapeRenderer.end();
		if(shake.getIsShaking()){
			shake.update(0.02f, this.camera);
		} else {
			camera.position.set(cameraBaseX, cameraBaseY, 0);
		}
		batch.setProjectionMatrix(camera.combined);
		camera.update();
		batch.begin();

		objectUpdate(this.objects, batch);

		batch.end();

		if (defaite) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException IE) {
				System.err.println("Erreur frame");
				System.exit(-1);
			}
			this.setScreen(new MenuFin(this));
		}
	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		batch.dispose();

	}

	public com.mygdx.superpaperplane.EventSpawner getEventSpawner() {
		return eventSpawner;
	}

	public com.mygdx.superpaperplane.Avion getAvion() {
		return avion;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public ArrayList<GameObject> getArrayList() {
		return objects;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setEntreeUtilisateur(int x) {
		this.entree_utilisateur = x;
	}

	public int getPosMin() {
		return posmin;
	}

	public Niveau(boolean insane) {

		this.insane = insane;
		this.defaite = false;
		actualFrame = 0;

	}

	public static Texture loadBufferedImage(String path) {
		Texture img = null;
		img = new Texture(path);
		return img;
	}

	public void addNewObject(GameObject object) {
		this.objects.add(object);
	}

	public void addFire() {
		feugauche = new com.mygdx.superpaperplane.Feu(0, 0, this);
		feudroite = new com.mygdx.superpaperplane.Feu(450, 0, this);
		this.addNewObject(feugauche);
		this.addNewObject(feudroite);
	}
	public void eteindrefeu() {
        feugauche.eteindrefeu();
        feudroite.eteindrefeu();
    }
    public void allumerfeu() {
        feugauche.allumerfeu();
        feudroite.allumerfeu();
    }
	public void setBackground(Texture img) {
		this.background = img;
	}

	public void lessScore() {
		if (this.score >= 3) {
			this.score = this.score - 3;
		}
	}

	public int getScore() {
		return this.score;
	}

	private void refreshScreen(SpriteBatch batch) {
		// Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		// Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.draw(background, 0, 0);
		int size = objects.size();
		for (int i = 0; i < size; i++) {
			batch.draw(objects.get(i).getImage(), objects.get(i).getPositionX(), objects.get(i).getPositionY());
		}
		for (int i = 0; i < avion.getNombreDeViesRestantes(); i++) {
			int taille = 0;
			if (avion.getNombreDeViesRestantes() == 3)
				taille = 500 / 2 - 25 - (avion.getNombreDeViesRestantes() / 2) * 50 + 47 * i;
			else if (avion.getNombreDeViesRestantes() == 2) {
				taille = 500 / 2 - (avion.getNombreDeViesRestantes() / 2) * 50 + 47 * i;
			} else
				taille = 500 / 2 - 25;
			batch.draw(this.hearth, taille, 760);
		}

		/*
		 * bitmapFont.setColor(Color.GREEN); bitmapFont.draw(batch,
		 * Gdx.graphics.getFramesPerSecond() + "", 0, 800);

		bitmapFont.setColor(Color.GREEN); bitmapFont.draw(batch,
				 getActualFrame() + "", 50, 800);

		*/
		if(avion.getIsMaxScore()) {
			bitmapFont.setColor(Color.RED);
		}else {
			bitmapFont.setColor(Color.WHITE);
		}
		String scoreString = Integer.toString(score);
		int widthString = (int) bitmapFont.getSpaceWidth() * scoreString.length();

		batch.draw(subbar.getImage(), 0, 0);

		if (Gdx.app.getType() != Application.ApplicationType.Android) { //Sur PC

			widthString = Gdx.app.getGraphics().getWidth() / 2 - widthString * 2;
			bitmapFont.draw(batch, scoreString, widthString * 2, 120);
			bitmapFont.setColor(Color.WHITE);
			if (insane) {

				if (actualFrame < 40) {
					widthString = (int) bitmapFont.getSpaceWidth() * subbar.getActualSentence().length();
					widthString = Gdx.app.getGraphics().getWidth() / 2 - widthString * 2;
					bitmapFont.draw(batch, subbar.getActualSentence(), widthString, 48);
				} else {

					subbar.getActualSentence();
				}
			} else {
				widthString = (int) bitmapFont.getSpaceWidth() * subbar.getActualSentence().length();
				widthString = Gdx.app.getGraphics().getWidth() / 2 - widthString * 2;
				bitmapFont.draw(batch, subbar.getActualSentence(), widthString, 48);
			}

		} else { //Sur Android
			widthString = (int) bitmapFont.getSpaceWidth() * scoreString.length();
			bitmapFont.draw(batch, scoreString, 500 / 2 - widthString * 2, 120);
			bitmapFont.setColor(Color.WHITE);
			if (insane) {
				if (actualFrame < 40) {
					widthString = (int) bitmapFont.getSpaceWidth() * subbar.getActualSentence().length();
					bitmapFont.draw(batch, subbar.getActualSentence(), 500 / 2 - widthString * 2, 48);
				} else {
					subbar.getActualSentence();
				}
			} else {
				widthString = (int) bitmapFont.getSpaceWidth() * subbar.getActualSentence().length();
				bitmapFont.draw(batch, subbar.getActualSentence(), 500 / 2 - widthString * 2, 48);
			}

		}

	}

	public void refreshBackground() {
		background = com.mygdx.superpaperplane.ImageBanque.getCaseImage(18);
	}

	public void activeListener() {
		Listeners listener = new Listeners(avion);
		Gdx.input.setInputProcessor(listener);
	}

	public void setVitesse(int v) {
		vitesse = v;
	}

	public int getActualFrame() {
		return actualFrame;
	}

	private void objectUpdate(ArrayList<GameObject> objects, SpriteBatch batch) {
		Iterator<GameObject> objIt = objects.iterator();
		ArrayList<GameObject> justCreated = new ArrayList<GameObject>();
		while (objIt.hasNext()) {
			GameObject obj = objIt.next();
			obj.deplacement(this.vitesse);
			if (obj.remove()) {
				objIt.remove();

			}

			if (obj.needsToCreate()) {
				justCreated.add(obj.createGameObject());
			}
		}
		for (int i = 0; i < justCreated.size(); i++) {
			objects.add(justCreated.get(i));
		}

		for (int i = 0; i < objects.size() - 1; i++) {
			for (int j = i + 1; j < objects.size(); j++) {

				if (objects.get(i).canHit(objects.get(j))) {

					if (objects.get(i) == avion) {
						if (isCollision(avion.getHitboxAvion(), objects.get(j).getHitbox())) {
							objects.get(j).whenGetHit();
							objects.get(i).whenGetHit();
						}
					} else if (objects.get(i).getHitbox().overlaps(objects.get(j).getHitbox())) {
						objects.get(j).whenGetHit();
						objects.get(i).whenGetHit();
					}
				}
			}
		}

		refreshScreen(batch);
		justCreated.clear();
		// System.gc();
		if(this.defaite){
			this.loose();
		}
	}

	private boolean isCollision(Polygon p, Rectangle r) {

		Polygon rPoly = new Polygon(new float[] { 0, 0, r.width, 0, r.width, r.height, 0, r.height });
		rPoly.setPosition(r.x, r.y);
		if (Intersector.overlapConvexPolygons(rPoly, p))
			return true;
		return false;
	}

	public void resetGame() {
		isPlaying = false;
		this.defaite = false;
		this.vitesse = 8;
		this.objects.clear();
		com.mygdx.superpaperplane.ImageBanque.InitialiserImageBanque();
		this.refreshBackground();
		eventSpawner = new com.mygdx.superpaperplane.EventSpawner(this);
		this.score = 0;
		avion = new com.mygdx.superpaperplane.Avion(250, 750, this, eventSpawner);
		addNewObject(avion);
		pattern.generatePattern();
		Barre barre = new Barre(0, 800, this, 0);
		this.addNewObject(barre);
		barre.setPositionX(pattern.getPatternX1());
		Barre barre2 = new Barre(0, 800, this, 1);
		this.addNewObject(barre2);
		if (insane) {
			feugauche = new com.mygdx.superpaperplane.Feu(0,0,this);
			feudroite = new com.mygdx.superpaperplane.Feu(450,0,this);
			this.addFire();
			this.addNewObject(new com.mygdx.superpaperplane.Jagger(50, 50, this, eventSpawner));
		}
		barre2.setPositionX(pattern.getPatternX2());
	}

	public int getNiveauEntreeUtilisateur() {
		return entreeUtilisateur;
	}

	public boolean getInsane() {
		return insane;
	}
	public void setDefaite(boolean b){
		this.defaite = b;
	}
	public void loose() {
		if (!avion.getIsInvincible()) {
			avion.setImageAvionActuel(16);
			int size = objects.size();
			for (int i = 0; i < size; i++) {
				objects.get(i).dispose();
			}
			music.dispose();

		}
	}



}
