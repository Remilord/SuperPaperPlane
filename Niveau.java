
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
    Barre barre = new Barre(0, 0);
    Barre barre2 = new Barre(0, 0);
    Avion avion = new Avion(50, 50);
    this.addNewObject(barre);
    this.addNewObject(barre2);
    this.addNewObject(avion);
    barre2.setPositionX(-500 + (int)(Math.random() * ((150 + 350) + 1)));
    while(true){
      long temps_debut_boucle = System.currentTimeMillis();

      barre.setPositionY(barre.getPositionY()-6);
      barre2.setPositionY(barre2.getPositionY()-6);
      if(barre.getPositionY()<=0-80){
        barre.setPositionY(800);
        barre.setPositionX(280 + (int)(Math.random() * ((400 - 280) + 1)));
      }
      if(barre2.getPositionY()<=0-80){
        barre2.setPositionY(800);
        barre2.setPositionX(-500 + (int)(Math.random() * ((500 + 150 - 250) + 1)));
      }

      this.repaint();
      long temps_consomme = temps_debut_boucle - System.currentTimeMillis();
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
