import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
public class Avion extends GameObject{
  private BufferedImage avionImage;
  private BufferedImage[] PionPlane;
  public Avion(int positionX, int positionY, Niveau niveau){
    super(positionX, positionY, niveau);
    PionPlane=new BufferedImage[14];
    this.avionImage=Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"avion"+File.separator+"PionPlane2.png");
    for(int a =1;a<14;a++) {
    PionPlane[a] = Niveau.loadBufferedImage("res"+File.separator+"image"+File.separator+"avion"+File.separator+"PionPlane"+a+".png");
}
    this.largeur = 100;
    this.hauteur = 75;
  }
  public void setImageAvionActuel(int i){
	  this.avionImage=PionPlane[i];
  }

  @Override
  public BufferedImage getImage() {
    return avionImage;
  }


}
