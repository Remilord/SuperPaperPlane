

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

    int b,i,k;
    int j,y;


    while(true){
      long temps_debut_boucle = System.currentTimeMillis();

      if(this.getEntreeUtilisateur() == 2){
        this.avion.setPositionX(this.avion.getPositionX()-5);
        this.avion.setImageAvionActuel("gauche");
      }
      else if(this.getEntreeUtilisateur() == 1){
        this.avion.setPositionX(this.avion.getPositionX()+5);
        this.avion.setImageAvionActuel("droite");
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

      if((avion.getPositionX()+100>=barre.getPositionX())) {
        if((avion.getPositionY()+75)>=barre.getPositionY()) {
          if(!(((avion.getPositionX()+26<=barre.getPositionX())&&(avion.getPositionX()+84<=barre.getPositionX()+600)&&(avion.getPositionY()+68>=barre.getPositionY())&&(avion.getPositionY()+73<=barre.getPositionY()+80))
          ||((avion.getPositionX()<=barre.getPositionX())&&(avion.getPositionX()+23<=barre.getPositionX()+600)&&(avion.getPositionY()+47>=barre.getPositionY())&&(avion.getPositionY()+75<=barre.getPositionY()+80))
          ||((avion.getPositionX()<=barre.getPositionX())&&(avion.getPositionX()+28<=barre.getPositionX()+600)&&(avion.getPositionY()>=barre.getPositionY())&&(avion.getPositionY()+25<=barre.getPositionY()+80))
          ||((avion.getPositionX()+66<=barre.getPositionX())&&(avion.getPositionX()+100<=barre.getPositionX()+600)&&(avion.getPositionY()>=barre.getPositionY())&&(avion.getPositionY()+26<=barre.getPositionY()+80)))) {
            System.out.println("perdu");
          }
        }
      }
      if((avion.getPositionX()+100>=barre2.getPositionX())) {
        if((avion.getPositionY()+75)>=barre2.getPositionY()) {
          if(!(((avion.getPositionX()+26<=barre2.getPositionX())&&(avion.getPositionX()+84<=barre2.getPositionX()+600)&&(avion.getPositionY()+68>=barre2.getPositionY())&&(avion.getPositionY()+73<=barre2.getPositionY()+80))
          ||((avion.getPositionX()<=barre2.getPositionX())&&(avion.getPositionX()+23<=barre2.getPositionX()+600)&&(avion.getPositionY()+47>=barre2.getPositionY())&&(avion.getPositionY()+75<=barre2.getPositionY()+80))
          ||((avion.getPositionX()<=barre2.getPositionX())&&(avion.getPositionX()+28<=barre2.getPositionX()+600)&&(avion.getPositionY()>=barre2.getPositionY())&&(avion.getPositionY()+25<=barre2.getPositionY()+80))
          ||((avion.getPositionX()+66<=barre2.getPositionX())&&(avion.getPositionX()+100<=barre2.getPositionX()+600)&&(avion.getPositionY()>=barre2.getPositionY())&&(avion.getPositionY()+26<=barre2.getPositionY()+80)))) {
            System.out.println("perdu");
          }
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
