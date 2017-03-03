import java.awt.*;
import javax.swing.*;

abstract class GameObject extends JComponent{
  protected int positionX;
  protected int positionY;
  protected int largeur;
  protected int hauteur;
  protected Image img;
  public GameObject(int x, int y){
    this.positionY = y;
    this.positionX = x;

  }
  public void paintComponent(Graphics pinceau){
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
  public  Image getImage() {
    return null;
  }
}
