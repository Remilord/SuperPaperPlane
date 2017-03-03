import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Scanner;


public class Niveau extends JPanel{

  private ArrayList<GameObject> objects = new ArrayList<GameObject>();

  private BufferedImage background;

  public Niveau() {
    super();
    background = loadBufferedImage(".."+File.separator+"image"+File.separator+"Niveau.png");
  }

  public static BufferedImage loadBufferedImage(String path) {
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(path));
      return img;
    }
    catch (IOException e) {
      System.err.println("LOADING ERROR "+path);
      System.exit(1);
    }
    return img;
  }


  public void addNewObject(GameObject object){
    this.objects.add(object);
  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background, null, 0, 0);
    int size = objects.size();
    for(int i = 0; i < size; i++){
      g2d.drawImage(objects.get(i).getImage(), null, objects.get(i).getPositionX(), objects.get(i).getPositionY());
    }
  }
  public void run(){
    Avion avion = new Avion(50, 50);
    Barre barre = new Barre(250, 50);
    Barre barre2 = new Barre(500, 50);
    this.addNewObject(avion);
    this.addNewObject(barre);
    this.addNewObject(barre2);
    while(true){
      long temps_début_boucle = System.currentTimeMillis();



      barre.setPositionY(barre.getPositionY()-1);
      barre2.setPositionY(barre2.getPositionY()-1);

      this.repaint();
      long temps_consomme = temps_début_boucle - System.currentTimeMillis();
      try{
        Thread.sleep(16-temps_consomme); // 1000/60 = 16ms environ,.16..66666 arronti à 17. On y soustrait le temps consommé pour update et dessiner la frame
      }
      catch(InterruptedException e){
        System.err.println("Interruped while sleeping between two frames");
        System.exit(1);
      }
    }
  }
}
