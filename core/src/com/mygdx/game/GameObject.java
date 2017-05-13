package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;


abstract class GameObject {
	protected int positionX;
	protected int positionY;
	protected int largeur;
	protected int hauteur;
	protected Texture img;
	protected final Niveau niveau;
	protected ObjectType objectType;
	protected boolean needsToBeRemoved;
	//protected Body body;
	public GameObject(int x, int y, Niveau niveau) {
		
		this.niveau = niveau;
		this.positionY = y;
		this.positionX = x;
		objectType = ObjectType.DECOR;
		needsToBeRemoved = false;
		//generateBody();
		
	}

	/*public Body getBody() {
		return body;
	}

	private void generateBody(){
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
		bodyDef.position.set(positionX, positionY);
		body = niveau.getWorld().createBody(bodyDef);
		
		PolygonShape hitbox = new PolygonShape();
		hitbox.setAsBox(largeur, hauteur);
		FixtureDef fixture = new FixtureDef();
		fixture.shape = hitbox;
		body.createFixture(fixture);
		this.body = this.niveau.getWorld().createBody(bodyDef);
	}*/
	
	public int getLargeur() {
		return this.largeur;
	}

	public int getHauteur() {
		return this.hauteur;
	}

	public int getPositionX() {
		return this.positionX;
	}

	public int getPositionY() {
		return this.positionY;
	}

	public void setPositionX(int x) {
		this.positionX = x;
	}

	public void setPositionY(int y) {
		this.positionY = y;
	}

	public void setImage(Texture image) {
		this.img = image;
	}

	public Texture getImage() {

		return Niveau.loadBufferedImage("image/normal/image210.png");
	}

	public void deplacement(int vitesse) {

	}

	public boolean remove() {
		return needsToBeRemoved;
	}

	public boolean needsToCreate() {
		return false;
	}

	public GameObject createGameObject() {
		return null;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public Rectangle createHitbox() {
		return new Rectangle(getPositionX(), getPositionY(), largeur, hauteur);
	}

	public Rectangle getHitbox() {
		return (Rectangle)createHitbox();
	}
	

	

	public void whenGetHit() {
		niveau.loose();
	}

	public boolean canHit(GameObject g) {
		return false;
	}

	public ObjectType getObjectType() {
		return objectType;
	}

	public enum ObjectType {
		DECOR, ENEMY, PLAYER, OFFENSIVE, BONUS, FANTOM;
	}

}
