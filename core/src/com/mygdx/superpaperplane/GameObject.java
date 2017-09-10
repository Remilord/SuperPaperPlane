package com.mygdx.superpaperplane;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;


abstract class GameObject implements Disposable{
	protected int positionX;
	protected int positionY;
	protected int largeur;
	protected int hauteur;
	protected Texture image;
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
	
	public void dispose(){
		image.dispose();

	}

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
		this.image = image;
	}

	public Texture getImage() {

		return Niveau.loadBufferedImage("image/invulnerable.png");
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
