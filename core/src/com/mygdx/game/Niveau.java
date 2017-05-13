package com.mygdx.game;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class Niveau extends ApplicationAdapter {
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private int score = 0;
	private int vitesse = 8;

	private Avion avion;
	private Texture background;
	private boolean defaite;
	private int entreeUtilisateur;
	protected int entree_utilisateur = 0;
	private int posmin = 0;
	private Pattern pattern;
	private boolean insane;
	private int actualFrame;
	private EventSpawner eventSpawner = new EventSpawner(this);
	SpriteBatch batch;
	private BitmapFont bitmapFont;
	OrthographicCamera camera;

	@Override
	public void create() {

		BitmapFont font = new BitmapFont();
		font.getData().setScale(3);
		bitmapFont = font;

		avion = new Avion(50, 800, this, eventSpawner);
		addNewObject(avion);
		batch = new SpriteBatch();
		ImageBanque.InitialiserImageBanque();
		background = ImageBanque.getCaseImage(15);
		ImageBanque.setValue(0);
		pattern = new Pattern();
		pattern.generatePattern();

		Barre barre = new Barre(0, 800, this, 0);
		this.addNewObject(barre);
		barre.setPositionX(pattern.getPatternX1());

		Barre barre2 = new Barre(0, 800, this, 1);

		this.addNewObject(barre2);
		this.addNewObject(new Feu(0, 0, this));
		this.addNewObject(new Feu(435, 0, this));
		this.addNewObject(new Jagger(50, 50, this, eventSpawner));

		barre2.setPositionX(pattern.getPatternX2());
		camera = new OrthographicCamera(500, 800);
		camera.position.set(500 / 2, 800 / 2, 0);
		Listeners listener = new Listeners(avion);
		Gdx.input.setInputProcessor(listener);

	}

	@Override
	public void render() {

		camera.update();

		if (actualFrame == 60) {
			actualFrame = 0;
			score++;
		}
		actualFrame++;

		pattern.generatePattern();
		if (insane && score != 0) {
			eventSpawner.calculateEvents();
		}
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);

		batch.begin();

		objectUpdate(this.objects, batch);

		batch.end();
		if (defaite) {
			System.exit(0);
		}

	}

	@Override
	public void dispose() {
		batch.dispose();

	}

	public EventSpawner getEventSpawner() {
		return eventSpawner;
	}

	public Avion getAvion() {
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
		ImageBanque.setValue(0); // On change le skin ici (0 pour normal , 1
									// pour 911,2 pour twenty one pilots)

	}

	public static Texture loadBufferedImage(String path) {
		Texture img = null;
		img = new Texture(path);
		return img;
	}

	public void addNewObject(GameObject object) {
		this.objects.add(object);
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
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.draw(background, 0, 0);
		int size = objects.size();
		for (int i = 0; i < size; i++) {
			batch.draw(objects.get(i).getImage(), objects.get(i).getPositionX(), objects.get(i).getPositionY());
		}
		bitmapFont.setColor(Color.GREEN);
		bitmapFont.draw(batch, Gdx.graphics.getFramesPerSecond() + "", 0, 800);
		bitmapFont.setColor(Color.WHITE);
		String scoreString = Integer.toString(score);
		int widthString = (int) bitmapFont.getSpaceWidth() * scoreString.length();
		
		if (Gdx.app.getType() != Application.ApplicationType.Android) {
			widthString = Gdx.app.getGraphics().getWidth() / 2 - widthString * 2;
			bitmapFont.draw(batch, scoreString, widthString, 50);
		} else {
			
			bitmapFont.draw(batch, scoreString, 500 / 2 - widthString, 50);
		}

	}

	public void refreshBackground() {
		background = ImageBanque.getCaseImage(15);
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
	}

	private boolean isCollision(Polygon p, Rectangle r) {

		Polygon rPoly = new Polygon(new float[] { 0, 0, r.width, 0, r.width, r.height, 0, r.height });
		rPoly.setPosition(r.x, r.y);
		if (Intersector.overlapConvexPolygons(rPoly, p))
			return true;
		return false;
	}

	public int getNiveauEntreeUtilisateur() {
		return entreeUtilisateur;
	}

	public boolean getInsane() {
		return insane;
	}

	public void loose() {
		if (!avion.getIsInvincible()) {
			try {
				avion.setImageAvionActuel(13);
				Thread.sleep(2000);
				this.defaite = true;
			} catch (InterruptedException e) {
				System.err.println("Error when trying to execute loose");
			}
		}
	}

}
