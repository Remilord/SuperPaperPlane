
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

@SuppressWarnings("serial")

public class Niveau extends JPanel{
  private ArrayList<GameObject> objects = new ArrayList<GameObject>();
  private int score = 0;
  private int vitesse= 8;
  private int bonuseffect=0;
  private int temps = 0;
  private Avion avion;
  private ClavierInsane clavierInsane;
  private BufferedImage background; //Base
  private BufferedImage backgroundBlur; //flou
  private BufferedImage backgroundShootingStar; //Shooting Star
  private BufferedImage backgroundBase; //Base
  private boolean defaite;
  private int entreeUtilisateur;
  private boolean shootingStar = false; //Booleen pour shooting star , true pour actif , false pour inactif
  protected int entree_utilisateur = 0;
  private int posmin = 0;
  private Pattern pattern;
  private boolean insane;
  private JFrame jf;
  private int actualFrame;
  private EventSpawner eventSpawner = new EventSpawner(this);

  public int getEntreeUtilisateur(){
    return this.entree_utilisateur;
  }

  public EventSpawner getEventSpawner(){
    return eventSpawner;
  }

  public Avion getAvion(){
    return avion;
  }

  public ClavierInsane getClavierInsane(){
    return clavierInsane;
  }

  public Pattern getPattern(){
    return pattern;
  }


  public ArrayList<GameObject> getArrayList(){
    return objects;
  }
  public int getBonusEffect(){
    return bonuseffect;
  }
  public int getVitesse(){
    return vitesse;
  }

  public void setEntreeUtilisateur(int x){
    this.entree_utilisateur = x;
  }
  public int getPosMin(){
    return posmin;
  }

  public Niveau(boolean insane, JFrame jf) {
    super(true);
    this.jf = jf;
    this.insane = insane;
    this.defaite = false;
    actualFrame = 0;
    background = loadBufferedImage("res"+File.separator+"image"+File.separator+"fond"+File.separator+"Niveau.png");
    backgroundBlur = loadBufferedImage("res"+File.separator+"image"+File.separator+"fond"+File.separator+"Niveauflou.png");
    backgroundShootingStar = loadBufferedImage("res"+File.separator+"image"+File.separator+"fond"+File.separator+"NiveauShootingStar.png");
    backgroundBase = loadBufferedImage("res"+File.separator+"image"+File.separator+"fond"+File.separator+"Niveau.png");
    setFocusable(true);
    clavierInsane = new ClavierInsane(this);
    addKeyListener(clavierInsane);
    avion = new Avion(50, 50, this, eventSpawner);
    addNewObject(avion);
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
    return this.score;
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
    g2d.drawString(Integer.toString(this.score), 250, 750);
  }


  public void setVitesse(int v){
    vitesse = v;
  }

  public int getActualFrame(){
    return actualFrame;
  }


  public void run(){

    int n = 1,nd = 0,nj = 0,np = 0; //Numero dimage pour les animations de mario , de denis , de jagger et de portail
    int tss = 0,tj = 0,td = 0,tp = 0; //Timer pour Mario , shooting star , l'effet du jagger , denis brogniart et pour les portails
    int vj=0;
    int ecart = 0; // entier calculant la difference (pour les portails)
    int vd=3; //vie de denis brogniart
    int nbouteille=0;
    boolean mini=false;
    Random rss = new Random(); //Random pour le shooting star
    Random rd = new Random();  //Random pour l'apparition de denis
    Random rp = new Random(); //Random pour l'apparition des portails
    //this.avion = new Avion(50,50, this);
    Barre barre = new Barre(0, 0, this, 0);
    Barre barre2 = new Barre(0, 0, this, 1);
    Bonus bonusk = new Bonus(0,0, this);
    Feu fg = new Feu(0,0, this);
    Feu fd = new Feu(435,0, this);
    Portal pone = new Portal(0,0, this);
    Portal ptwo = new Portal(0,0, this);
    Son ss = new Son("/res/son/shootingstar.wav");
    Jagger jg = new Jagger(0,0, this);
    pattern = new Pattern();
    pattern.generatePattern();
    barre.setPositionX(pattern.getPatternX1());
    barre2.setPositionX(pattern.getPatternX2());
    this.addNewObject(barre);
    this.addNewObject(barre2);
    this.addNewObject(bonusk);
    this.addNewObject(fg);
    this.addNewObject(fd);
    this.addNewObject(jg);


    while(!defaite){
      if(actualFrame == 60){
        actualFrame = 0;
        score = score + vitesse / 8;
      }
      actualFrame++;
      pattern.generatePattern();
      entreeUtilisateur = getEntreeUtilisateur();
      long temps_debut_boucle = System.currentTimeMillis();

      if(bonusk.sousEffet(this.bonuseffect)) {
        this.temps++;
      }
      if(insane && score != 0){
        eventSpawner.calculateEvents();


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
        //Si le score est a %5 ajout d'un Dennis
        if((rp.nextInt(900)==0)&&(this.objects.contains(pone)==false)) {
          pone = new Portal(0,0,this);
          ptwo = new Portal(0,0,this);
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
          //this.setInversed(false);

        }




        /*if(isDenisAlive) {
          td++;
          if((td==15)||(td==30)||(td==45)||(td==60)) {
            if(nd==1) {
              Ah ah = new Ah(0,0, this);
              if(!this.objects.contains(ah))
              this.addNewObject(ah);
              //ah.setPositionX(denis.getPositionX());
              //ah.setPositionY(denis.getPositionY());
              Son.playTempSound("/res/son/ah.wav");
            }
            nd++;
            //denis.setImageDenisActuel(nd);
          }
          if(td==75) {
            nd=0;
            td=0;
            //denis.setImageDenisActuel(nd);
          }
        }*/
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

      //---------------------DEPLACEMENT--------------------------
      objectUpdate(this.objects);


      /*
      Si l'avion est pas invinble et touche un objet game over
      if(this.bonuseffect!=1) {
        if(hitbox.detectCollision(objects)==1) {
          ss.stop();
          loose();
        }
      }
      */

      /*Si rencontre un bonus
      if(hitbox.detectCollision(objects)==2) {
        this.bonuseffect=bonusk.getEtatNum();
        bonusk.resetBonusStatus();
        bonusk.setAttributes(this.bonuseffect,this.vitesse,mini);
        this.vitesse=bonusk.getVitesseStatus();
        mini=bonusk.getMiniStatus();
        this.temps=0;
        bonusk.resetPosition();
      }

      */

      /*Si c'est du jagger
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
          this..setInversed(true);
          tj=0;
        }
      }
      */

      /*Si c'est un portail
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
      */

      /* Wtf?
      if(bonusk.timerBonus(this.temps)) {
        this.vitesse=bonusk.getVitesseStatus();
        mini=bonusk.getMiniStatus();
        this.bonuseffect=0;
        this.temps=0;
      }

      */

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
    ArrayList<GameObject> justCreated = new ArrayList<GameObject>();
    while(objIt.hasNext()) {
      GameObject obj = objIt.next();
      obj.deplacement(this.vitesse);
      if(obj.remove())
        objIt.remove();
      if(obj.needsToCreate()){
        justCreated.add(obj.createGameObject());
      }
    }
    for(int i = 0; i < justCreated.size(); i++){
      objects.add(justCreated.get(i));
    }

    avion.createHitbox();
    for(int i = 0; i < objects.size(); i++){
      for(int j = 0; j < objects.size(); j++){
        if(objects.get(i).canHit(objects.get(j))){
          if(objects.get(i).getHitbox().intersects(
            objects.get(j).getHitbox().getBounds2D()
          )){
            objects.get(j).whenGetHit();
          }
        }
      }
    }

    this.repaint();
    justCreated.clear();
  }
  public int getNiveauEntreeUtilisateur(){
    return entreeUtilisateur;
  }
  public boolean getInsane(){
    return insane;
  }
  public boolean getShootingStar(){
    return shootingStar;
  }
  public void loose(){
    if(!avion.getIsInvincible()){
      try{
        avion.setImageAvionActuel(13);
        Thread.sleep(250);
        this.defaite=true;
      }
      catch(InterruptedException e){
        System.err.println("Error when trying to execute loose");
      }
    }
  }
}
