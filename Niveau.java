

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyListener;



public class Niveau extends JPanel{

  private ArrayList<GameObject> objects = new ArrayList<GameObject>();

  private BufferedImage background;

  protected Avion avion = new Avion(50,50);

  protected int entree_utilisateur = 0;


  public int getEntreeUtilisateur(){
    return this.entree_utilisateur;
  }

  public void setEntreeUtilisateur(int x){
    this.entree_utilisateur = x;
  }

  public Niveau() {
    super(true);
    background = loadBufferedImage(".."+File.separator+"image"+File.separator+"Niveau.png");
    setFocusable(true);
    addKeyListener(new Clavier(this));

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
    this.addNewObject(barre);
    this.addNewObject(barre2);
    this.addNewObject(this.avion);
    barre2.setPositionX(-500 + (int)(Math.random() * ((150 + 350) + 1)));


    Interval interavionX = new Interval(avion.getPositionX(),avion.getPositionX()+100);
    Interval interavionY = new Interval(avion.getPositionY(),avion.getPositionY()+100);
    Interval interbarre1X = new Interval(barre.getPositionX(),barre.getPositionX()+600);
    Interval interbarre2X = new Interval(barre2.getPositionX(),barre2.getPositionX()+600);
    Interval interbarre1Y = new Interval(barre.getPositionY(),barre.getPositionY()+600);
    Interval interbarre2Y = new Interval(barre2.getPositionY(),barre2.getPositionY()+600);
    Interval hitbox1X = new Interval(avion.getPositionX(),avion.getPositionX()+23);
    Interval hitbox1Y = new Interval(avion.getPositionY()+47,avion.getPositionY()+75);
    Interval hitbox2X = new Interval(avion.getPositionX(),avion.getPositionX()+28);
    Interval hitbox2Y = new Interval(avion.getPositionY(),avion.getPositionY()+25);
    Interval hitbox3X = new Interval(avion.getPositionX()+66,avion.getPositionX()+100);
    Interval hitbox3Y = new Interval(avion.getPositionY(),avion.getPositionY()+26);


    while(true){
      long temps_debut_boucle = System.currentTimeMillis();

      if(this.getEntreeUtilisateur() == 2){
        if(this.avion.getPositionX() > 0){
          this.avion.setPositionX(this.avion.getPositionX()-5);
          this.avion.setImageAvionActuel("gauche");
        }
      }
      else if(this.getEntreeUtilisateur() == 1){
        if(this.avion.getPositionX()+100 < 505){
          this.avion.setPositionX(this.avion.getPositionX()+5);
          this.avion.setImageAvionActuel("droite");
        }
      }
      else if(this.getEntreeUtilisateur() == 0){
        this.avion.setImageAvionActuel("bas");
      }

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

      interavionX.setValues(avion.getPositionX(),avion.getPositionX()+100);
      interavionY.setValues(avion.getPositionY(),avion.getPositionY()+75);
      interbarre1X.setValues(barre.getPositionX(),barre.getPositionX()+600);
      interbarre1Y.setValues(barre.getPositionY(),barre.getPositionY()+75);
      interbarre2X.setValues(barre2.getPositionX(),barre2.getPositionX()+600);
      interbarre2Y.setValues(barre2.getPositionY(),barre2.getPositionY()+75);
      hitbox1X.setValues(avion.getPositionX(),avion.getPositionX()+23);
      hitbox1Y.setValues(avion.getPositionY()+47,avion.getPositionY()+75);
      hitbox2X.setValues(avion.getPositionX(),avion.getPositionX()+28);
      hitbox2Y.setValues(avion.getPositionY(),avion.getPositionY()+25);
      hitbox3X.setValues(avion.getPositionX()+66,avion.getPositionX()+100);
      hitbox3Y.setValues(avion.getPositionY(),avion.getPositionY()+26);
      
      if((interavionX.intersects(interbarre1X)==true)&&(interavionY.intersects(interbarre1Y)==true)) {
        if(!(((hitbox1X.contains(barre.getPositionX()+600)==true)&&(hitbox1Y.contains(barre.getPositionY())==true))
          ||((hitbox2X.contains(barre.getPositionX()+600)==true)&&(hitbox3Y.contains(barre.getPositionY())==true))
          ||((hitbox3X.contains(barre.getPositionX())==true)&&(hitbox3Y.contains(barre.getPositionY())==true)))) {
        System.out.println("perdu");
        }
      }
      if((interavionX.intersects(interbarre2X)==true)&&(interavionY.intersects(interbarre2Y)==true)) {
        if(!(((hitbox1X.contains(barre2.getPositionX()+600)==true)&&(hitbox1Y.contains(barre2.getPositionY())==true))
          ||((hitbox2X.contains(barre2.getPositionX()+600)==true)&&(hitbox3Y.contains(barre2.getPositionY())==true))
          ||((hitbox3X.contains(barre2.getPositionX())==true)&&(hitbox3Y.contains(barre2.getPositionY())==true)))) {
        System.out.println("perdu");
        }
      }


      this.repaint();
      long temps_consomme = System.currentTimeMillis() - temps_debut_boucle;
      try{
        Thread.sleep((int)(16.6666-temps_consomme)); // 1000/60 = 16ms environ,.16..66666 arronti à 17. On y soustrait le temps consommé pour update et dessiner la frame
      }
      catch(InterruptedException e){
        System.err.println("Interruped while sleeping between two frames");
        System.exit(1);
      }
    }
  }

}
