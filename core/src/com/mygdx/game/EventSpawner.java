package com.mygdx.game;
import java.util.Random;

public class EventSpawner{

  private Niveau niveau;
  private boolean isMario = false;
  private boolean isDenis = false;
  private boolean isDenisShouting = false;
  private boolean isShootingStar = false;

  private Random random = new Random();
  private int score;

  public EventSpawner(Niveau niveau){
    this.niveau = niveau;
    score = niveau.getScore();
  }

  public boolean getIsDenisShouting(){
    return isDenisShouting;
  }

  public void setIsDenisShouting(boolean b){
    isDenisShouting = b;
  }


 

  public void setIsMario(boolean b){
    isMario = b;
  }

  public void setIsDenis(boolean b){
    isDenis = b;
  }

  public boolean getIsDenis(){
    return isDenis;
  }

  public void setShootingStar(boolean b){
	  isShootingStar = b;
  }
  public boolean getShootingStar(){
	  return isShootingStar;
  }

  public void calculateEvents(){
    if(score != niveau.getScore()){
      score = niveau.getScore();
      if(score % 7 == 0 && !isMario && shouldThisBeInsane(3)) {
        Mario mario = new Mario(0, 0, niveau, this);
        niveau.addNewObject(mario);
      }
      if(score % 5 == 0 && !isDenis && shouldThisBeInsane(2)) {
        Denis denis = new Denis(0, 50, niveau, this);
        niveau.addNewObject(denis);
      }
      if(score % 3 == 0 && !niveau.getAvion().getIsBonused() && shouldThisBeInsane(2)){
        Bonus bonus = new Bonus(random.nextInt(5)*20+65, -20, niveau);
        niveau.addNewObject(bonus);
      }
      if(!isShootingStar && shouldThisBeInsane(200)){
          ShootingStar shootingStar = new ShootingStar
        		  (0, 0, niveau, this);
          niveau.addNewObject(shootingStar);
          isShootingStar = true;
      }

    }
  }

  public boolean shouldThisBeInsane(int x){
    return random.nextInt(x) == 0;
  }
}
