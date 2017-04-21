
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



public class NiveauInsane extends JPanel{
  private ArrayList<GameObject> objects = new ArrayList<GameObject>();
  private int score = 0;
  private int vitesse=6;
  private int bonuseffect=0;
  private int temps = 0;

  private BufferedImage background;

  protected Avion avion = new Avion(50,50);

  protected int entree_utilisateur = 0;


  public int getEntreeUtilisateur(){
    return this.entree_utilisateur;
  }

  public void setEntreeUtilisateur(int x){
    this.entree_utilisateur = x;
  }

  public NiveauInsane() {
    super(true);
    background = Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/Niveau.png");
    setFocusable(true);
    addKeyListener(new ClavierInsane(this));

  }

  private Font font = new Font("TimesRoman", Font.BOLD, 24);

  public void addNewObject(GameObject object){
    this.objects.add(object);
  }
  private void setBackground(String s) {
	  background=Niveau.loadBufferedImage("D:/Dossier perso/Programmation/Java/SuperPaperPlane/image/Niveau"+s+".png");
  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.WHITE);
    g2d.setFont(this.font);
    g2d.drawImage(background, null, 0, 0);
    int size = objects.size();
    for(int i = 0; i < size; i++){
      g2d.drawImage(objects.get(i).getImage(), null, objects.get(i).getPositionX(), objects.get(i).getPositionY());
    }

    g2d.drawString(Integer.toString(this.score/60), 250, 750);
  }
  public void run(){
	  int n=1;
	  int t=0;
	  boolean mini=false;
    Barre barre = new Barre(0, 0);
    Barre barre2 = new Barre(0, 0);
    Bonus bonusk = new Bonus(0,0);
    Mario mario = new Mario(0,0);
    Feu fg = new Feu(0,0);
    Feu fd = new Feu(435,0);
    Son ss = new Son("/res/shootingstar.wav");
    this.addNewObject(barre);
    this.addNewObject(barre2);
    this.addNewObject(bonusk);
    this.addNewObject(fg);
    this.addNewObject(fd);
    this.addNewObject(this.avion);
    barre2.setPositionX(-500 + (int)(Math.random() * ((150) + 1)));
    Hitbox hitbox = new Hitbox();


    while(true){
    	 this.score=this.score+this.vitesse/6;
      long temps_debut_boucle = System.currentTimeMillis();
      if(bonusk.sousEffet(this.bonuseffect)) {
          this.temps++;
          }
      if(this.score%600==0) {
    	  this.addNewObject(mario);
    	  mario.setImageMarioActuel(1);
      }
      if(this.score%600==0) {
    	  this.setBackground("InsaneShootingStar");
    	  barre.setImage("SS");
    	  barre2.setImage("SS");
    	  ss.play();
      }
      if(this.objects.contains(mario)) {
    	  t=t+1;
    	  if(t==1) {
        	  Son.playTempSound("/res/Non.wav");
          }
      if(t==200) {
    	  n=1;
    	  t=0;
    	  objects.remove(mario);
      }else if((t==15)||(t==30)||(t==45)||(t==60)) {
      n=n+1;
      mario.setImageMarioActuel(n);
      }
      
      }
      
      if(this.getEntreeUtilisateur() == 2){
        if(this.avion.getPositionX() > 0){
          this.avion.setPositionX(this.avion.getPositionX()-5);
          this.avion.setImageAvionActuel("gauche",bonuseffect);
        }
      }
      else if(this.getEntreeUtilisateur() == 1){
        if(this.avion.getPositionX()+100 < 505){
          this.avion.setPositionX(this.avion.getPositionX()+5);
          this.avion.setImageAvionActuel("droite",bonuseffect);
        }
      }
      else if(this.getEntreeUtilisateur() == 0){
        this.avion.setImageAvionActuel("bas",bonuseffect);
      }

      barre.setPositionY(barre.getPositionY()-vitesse);
      barre2.setPositionY(barre2.getPositionY()-vitesse);
      bonusk.setPositionY(bonusk.getPositionY()-vitesse);
      if(barre.getPositionY()<=0-80){
        barre.setPositionY(800);
        barre.setPositionX(280 + (int)(Math.random() * ((400 - 280) + 1)));
      }
      if(barre2.getPositionY()<=0-80){
        barre2.setPositionY(800);
        barre2.setPositionX(-500 + (int)(Math.random() * ((150) + 1)));
      }
      if(bonusk.getPositionY()<=0-80) {
    	  bonusk.resetBonus();
      }
      this.repaint();
        if(this.getEntreeUtilisateur() == 2) {
       hitbox.setEtat(2,avion,mini);
     } else if (this.getEntreeUtilisateur()==1) {
    	 hitbox.setEtat(1,avion,mini);
     } else if(this.getEntreeUtilisateur() == 0) {
    	 hitbox.setEtat(0,avion,mini);
     }
       if(this.bonuseffect!=1) {
        if((hitbox.detectCollision(objects)==1)||((this.avion.getPositionX()<50)||(this.avion.getPositionX()>350))) {
            try{
              Thread.sleep(5000);
              System.exit(0);
            }
            catch(InterruptedException e){
              System.err.println("Interruped while sleeping between two frames");
              System.exit(1);
            }
           }
       }
       
       
       if(hitbox.detectCollision(objects)==2) {
    	   this.bonuseffect=bonusk.getEtatNum();
    	   bonusk.resetBonusStatus();
    	   bonusk.setAttributes(this.bonuseffect,this.vitesse,mini);
    	   this.vitesse=bonusk.getVitesseStatus();
    	   mini=bonusk.getMiniStatus();
    	   this.temps=0;
    	   bonusk.resetPosition();
       }
       
       
       if(bonusk.timerBonus(this.temps)) {
    	   this.vitesse=bonusk.getVitesseStatus();
    	   mini=bonusk.getMiniStatus();
    	   this.bonuseffect=0;
    	   this.temps=0;
       }
      hitbox.reset();
      long temps_consomme = System.currentTimeMillis() - temps_debut_boucle;
    if(temps_consomme<16.6666) {
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
}