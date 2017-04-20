import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
@SuppressWarnings("serial")

abstract class GameObject extends JComponent{
  protected int positionX;
  protected int positionY;
  protected int largeur;
  protected int hauteur;
  protected BufferedImage img;
  protected final Niveau niveau;
  protected ObjectType objectType;
  protected boolean needsToBeRemoved;
  public GameObject(int x, int y, Niveau niveau){
	  this.setDoubleBuffered(true);
    this.niveau = niveau;
    this.positionY = y;
    this.positionX = x;
    objectType = ObjectType.DECOR;
    needsToBeRemoved = false;
  }

  public int getLargeur(){
    return this.largeur;
  }
  public int getHauteur(){
    return this.hauteur;
  }
  public int getPositionX(){
    return this.positionX;
  }
  public int getPositionY(){
    return this.positionY;
  }
  public void setPositionX(int x){
    this.positionX = x;
  }
  public void setPositionY(int y){
    this.positionY = y;
  }
  public void setImage(BufferedImage image){
    this.img = image;
  }

  public BufferedImage getImage() {
    return null;
  }

  public void deplacement(int vitesse){

  }

  public boolean remove(){
    return needsToBeRemoved;
  }
  public boolean needsToCreate(){
    return false;
  }
  public GameObject createGameObject(){
    return null;
  }

  public Niveau getNiveau(){
    return niveau;
  }


  public void createHitbox(){

  }

  public Polygon getHitbox(){
    int xPoints[] = {getPositionX(), getPositionX() + getLargeur()};
    int yPoints[] = {getPositionY(), getPositionY() + getHauteur()};

    return new Polygon(xPoints, yPoints, xPoints.length);
  }
  /*polygon.intersects(Rectangle2D)*/
  //Pour avoir un Rectangle2D : getBounds2D

  public void whenGetHit(){
    niveau.loose();
  }

  public boolean canHit(GameObject g){
    return false;
  }

  public ObjectType getObjectType(){
    return objectType;
  }



  public enum ObjectType {
    DECOR,
    ENEMY,
    PLAYER,
    OFFENSIVE,
    BONUS;
  }



}
