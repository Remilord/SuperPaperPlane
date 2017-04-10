
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyListener;
import java.util.Iterator;


public class Niveau extends JPanel{
  private ArrayList<GameObject> objects = new ArrayList<GameObject>();
  private int score = 0;
  private int vitesse=6;
  private int bonuseffect=0;
  private int temps = 0;
  private ClavierInsane ci;
  private BufferedImage background; //Base
  private BufferedImage backgroundBlur; //flou
  private BufferedImage backgroundShootingStar; //Shooting Star
  private BufferedImage backgroundBase; //Base
  private boolean defaite;

  protected int entree_utilisateur = 0;
  private boolean insane;
  private JFrame jf;
  public int getEntreeUtilisateur(){
    return this.entree_utilisateur;
  }

  public void setEntreeUtilisateur(int x){
    this.entree_utilisateur = x;
  }

  public Niveau(boolean insane, JFrame jf) {
    super(true);
    this.jf = jf;
    this.insane = insane;
    this.defaite = false;

    background = loadBufferedImage("res"+File.separator+"image"+File.separator+"fond"+File.separator+"Niveau.png");
    backgroundBlur = loadBufferedImage("res"+File.separator+"image"+File.separator+"fond"+File.separator+"Niveauflou.png");
    backgroundShootingStar = loadBufferedImage("res"+File.separator+"image"+File.separator+"fond"+File.separator+"NiveauShootingStar.png");
    backgroundBase = loadBufferedImage("res"+File.separator+"image"+File.separator+"fond"+File.separator+"Niveau.png");
    setFocusable(true);
    ci = new ClavierInsane(this);
    addKeyListener(ci);

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

  private Font font = new Font("TimesRoman", Font.BOLD, 24);

  public void addNewObject(GameObject object){
    this.objects.add(object);
  }
  public void setFlou(boolean on) {
    if(on) {
      this.background=this.backgroundBlur;
    }else {
      this.background = this.backgroundBase;
    }
  }
  private void setBackgroundShootingStar(boolean onss) {
    if(onss) {
      this.background=this.backgroundShootingStar;
    }else {
      this.background=this.backgroundBase;
    }
  }
  public int getScore() {
    return this.score/60;
  }

  @Override
  public void paintComponent(Graphics g) {

    super.paintComponent(g);
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

    int n=1,nd=0,nj=0,np=0; //Numero dimage pour les animations de mario , de denis , de jagger et de portail
    int tm=0,tss=0,tj=0,td=0,tp=0; //Timer pour Mario , shooting star , l'effet du jagger , denis brogniart et pour les portails
    int vj=0;
    int ecart = 0; // entier calculant la diff�rence (pour les portails)
    int vd=3; //vie de denis brogniart
    int nbouteille=0;
    int nbrtir=0;
    int posmin = 0;
    boolean shootingStar = false; //Booleen pour shooting star , true pour actif , false pour inactif
    boolean mini=false;
    Random rss = new Random(); //Random pour le shooting star
    Random rd = new Random();  //Random pour l'apparition de denis
    Random rp = new Random(); //Random pour l'apparition des portails
    Avion avion = new Avion(50,50, this);
    Barre barre = new Barre(0, 0, this);
    Barre barre2 = new Barre(0, 0, this);
    Bonus bonusk = new Bonus(0,0, this);
    Mario mario = new Mario(0,0, this);
    Denis denis = new Denis(0,650, this);
    Tir tir = new Tir(0,0, this);
    Feu fg = new Feu(0,0, this);
    Feu fd = new Feu(435,0, this);
    Portal pone = new Portal(0,0, this);
    Portal ptwo = new Portal(0,0, this);
    Son ss = new Son("/res/son/shootingstar.wav");
    Jagger jg = new Jagger(0,0, this);
    Pattern pattern = new Pattern();
    pattern.generatePattern();
    barre.setPositionX(pattern.getPatternX1());
    barre2.setPositionX(pattern.getPatternX2());
    this.addNewObject(barre);
    this.addNewObject(barre2);
    this.addNewObject(bonusk);
    this.addNewObject(fg);
    this.addNewObject(fd);
    this.addNewObject(jg);
    this.addNewObject(avion);
    //barre2.setPositionX(-500 + (int)(Math.random() * ((150) + 1)));
    Hitbox hitbox = new Hitbox(); //Hitbox de l'avion
    Rectangle tirh = new Rectangle(); //Hitbox du tir
    Rectangle denish = new Rectangle(); //Hitbox de denis


    while(!defaite){

      this.score=this.score+this.vitesse/6;
      long temps_debut_boucle = System.currentTimeMillis();
      if(bonusk.sousEffet(this.bonuseffect)) {
        this.temps++;
      }
      if(insane){
        if(this.score%600==0) {
          this.addNewObject(mario);
          mario.setImageMarioActuel(1);
        }

        if((rss.nextInt(3500)==0)&&(shootingStar==false)) {
          this.setBackgroundShootingStar(true);
          shootingStar=true;
          barre.setImageSS(true);
          barre2.setImageSS(true);
          this.objects.remove(fg);
          this.objects.remove(fd);
          posmin=-105;
          ss.play();
        }
        /*
        List<String> lines = Files.lines(Paths.get("myfile.txt")).collect(Collectors.toList());
        */
        //Si le score est a %5 ajout d'un Dennis
        if((((this.score/60)%5)==0) && (this.objects.contains(denis)==false)&&((this.score/60)!=0)) {
          this.addNewObject(denis);
        }
        if((rp.nextInt(900)==0)&&(this.objects.contains(pone)==false)) {
          this.addNewObject(pone);
          this.addNewObject(ptwo);
          pone.setPositions(1);
          ptwo.setPositions(2);
        }
        if(shootingStar) {
          tss++;
        }
        if(nbouteille > 0) {
          tj++;
        }
        if(tss==2000) {
          shootingStar=false;
          this.setBackgroundShootingStar(false);
          barre.setImageSS(false);
          barre2.setImageSS(false);
          this.addNewObject(fg);
          this.addNewObject(fd);
          posmin=0;
          tss=0;
          ss.stop();
        }
        if(tj==2000) {
          nbouteille=0;
          this.setFlou(false);
          barre.setFlou(false);
          barre2.setFlou(false);
          fg.setFlou(false);
          fd.setFlou(false);
          this.ci.setInversed(false);

        }



        if(this.objects.contains(mario)) {
          tm=tm+1;
          if(tm==1) {
            Son.playTempSound("/res/son/Non.wav");
          }
          if(tm==200) {
            n=1;
            tm=0;
            objects.remove(mario);
          }else if((tm==15)||(tm==30)||(tm==45)||(tm==60)) {
            n=n+1;
            mario.setImageMarioActuel(n);
          }

        }

        if(this.objects.contains(denis)) {
          td++;
          if((td==15)||(td==30)||(td==45)||(td==60)) {
            if(nd==1) {
              Ah ah = new Ah(0,0, this);
              if(!this.objects.contains(ah))
              this.addNewObject(ah);
              ah.setPositionX(denis.getPositionX());
              ah.setPositionY(denis.getPositionY());
              Son.playTempSound("/res/son/ah.wav");
            }
            nd++;
            denis.setImageDenisActuel(nd);
          }
          if(td==75) {
            nd=0;
            td=0;
            denis.setImageDenisActuel(nd);
          }
        }
        if((vj==5)||(vj==10)||(vj==15)||(vj==20)) {
          nj=nj+1;
          jg.setImageJagger(nj);
        }
        if(vj==25) {
          nj=0;
          vj=0;
        }
        vj=vj+1;
        if(this.objects.contains(pone)) {
          tp++;
          if((tp==5)||(tp==10)||(tp==15)||(tp==20)||(tp==25)) {
            np=np+1;
            pone.setImagePortalActuel(np);
            ptwo.setImagePortalActuel(np);
          }
          if(tp==30) {
            tp=0;
            np=0;
          }
        }
      }

      //DEP DE L'AVION
      if(this.getEntreeUtilisateur() == 2){
        if(avion.getPositionX() > posmin){
          avion.setPositionX(avion.getPositionX()-5);
          avion.setImageAvionActuel((bonuseffect*3)+3);
        }
      }
      else if(this.getEntreeUtilisateur() == 1){
        if(avion.getPositionX() < 605){
          avion.setPositionX(avion.getPositionX()+5);
          avion.setImageAvionActuel((bonuseffect*3)+1);
        }
      }
      else if(this.getEntreeUtilisateur() == 0){
        avion.setImageAvionActuel((bonuseffect*3)+2);
        if(this.objects.contains(denis)&&(this.objects.contains(tir)==false)) {
          this.addNewObject(tir);
          tir.setPositionX(avion.getPositionX()+40);
          tir.setPositionY(avion.getPositionY()+100);
        }
      }


      if(shootingStar && insane) {
        if(avion.getPositionX() > 505) {
          avion.setPositionX(-50);
        }else if (avion.getPositionX() < -75) {
          avion.setPositionX(490);
        }
      }


      //---------------------DEPLACEMENT--------------------------
      objectUpdate(this.objects);


      if(insane){

        if(this.objects.contains(tir)) {
          if((this.objects.contains(denis))&&(this.objects.contains(tir))) {
            tirh.setBounds(tir.getPositionX(), tir.getPositionY(), 15, 30);
            denish.setBounds(denis.getPositionX(), denis.getPositionY(), 100, 100);
          }
          if(tirh.intersects(denish)) {
            this.objects.remove(tir);
          }
        }

        if(tirh.intersects(denish)) {
          vd --;
          tirh.setBounds(0, 0, 0, 0);
          if(vd==0) {
            this.objects.remove(denis);
          }
        }
      }

      pattern.generatePattern();
      if(barre.getPositionY()<=0-80){
        barre.setPositionY(800);
        barre.setPositionX(pattern.getPatternX1());
      }
      if(barre2.getPositionY()<=0-80){
        barre2.setPositionY(800);
        barre2.setPositionX(pattern.getPatternX2());
      }

      if(this.getEntreeUtilisateur() == 2) {
        hitbox.setEtat(2,avion,mini);
      } else if (this.getEntreeUtilisateur()==1) {
        hitbox.setEtat(1,avion,mini);
      } else if(this.getEntreeUtilisateur() == 0) {
        hitbox.setEtat(0,avion,mini);
      }
      if(this.bonuseffect!=1) {
        if(hitbox.detectCollision(objects)==1) {
          try{
            avion.setImageAvionActuel(13);
            Thread.sleep(500);
            this.defaite=true;
            ss.stop();
          }
          catch(InterruptedException e){
            System.err.println("Erreur hitbox");
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




      if(hitbox.detectCollision(objects)==3) {
        if(nbouteille==0) {
          if(shootingStar==false) {
            this.setFlou(true);
            barre.setFlou(true);
            barre2.setFlou(true);
          }
          fg.setFlou(true);
          fd.setFlou(true);
          jg.resetPosition();
          nbouteille++;
        }else {
          this.ci.setInversed(true);
          tj=0;
        }
      }

      if(hitbox.detectCollision(objects)==4) {
        ecart = ptwo.getPositionY() - avion.getPositionY();
        ptwo.setPositionY(avion.getPositionY()-38);
        avion.setPositionX(ptwo.getPositionX()+20);
        barre.setPositionY(barre.getPositionY()-ecart);
        barre2.setPositionY(barre2.getPositionY()-ecart);
        bonusk.setPositionY(bonusk.getPositionY()-ecart);
        if(this.objects.contains(jg)) {
          jg.setPositionY(jg.getPositionY()-ecart);
        }
        this.objects.remove(pone);
        this.objects.remove(ptwo);
      }



      if(bonusk.timerBonus(this.temps)) {
        this.vitesse=bonusk.getVitesseStatus();
        mini=bonusk.getMiniStatus();
        this.bonuseffect=0;
        this.temps=0;
      }



      hitbox.reset();

      this.repaint();

      long temps_consomme = System.currentTimeMillis() - temps_debut_boucle;
      if(temps_consomme<16.6666) {
        try{
          Thread.sleep((int)(16.6666-temps_consomme)); // 1000/60 = 16ms environ,.16..66666 arronti à 17. On y soustrait le temps consommé pour update et dessiner la frame
        }
        catch(InterruptedException e){
          System.err.println("Interruped while sleeping between two frames");
        }
      }
    }
  }

  private void objectUpdate(ArrayList<GameObject> objects){
    Iterator<GameObject> objIt = objects.iterator();
    while(objIt.hasNext()) {
      GameObject obj = objIt.next();
      obj.deplacement(this.vitesse);
      if(obj.remove())
        objIt.remove();

    }
  }
}
