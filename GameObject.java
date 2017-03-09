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
  public GameObject(int x, int y){
    this.positionY = y;
    this.positionX = x;

  }
  public void paintComponent(Graphics pinceau){
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
}
