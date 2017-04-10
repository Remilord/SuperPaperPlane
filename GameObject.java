import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
abstract class GameObject extends JComponent{
  protected int positionX;
  protected int positionY;
  protected int largeur;
  protected int hauteur;
  protected BufferedImage img;
  private final Niveau niveau;
  public GameObject(int x, int y, Niveau niveau){
	  this.setDoubleBuffered(true);
    this.niveau = niveau;
    this.positionY = y;
    this.positionX = x;

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
    return false;
  }

  public Niveau getNiveau(){
    return niveau;
  }

  public boolean create(){
    return false;
  }

}
