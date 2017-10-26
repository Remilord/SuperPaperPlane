package com.mygdx.superpaperplane;

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
      if(score % 4 == 0 && !isMario && shouldThisBeInsane(5)) {

        Mario mario;
          if(niveau.getScore() < 50 ){
              mario = new Mario(0, 0, niveau, this, false);
          } else {
              mario = new Mario(0, 0, niveau, this, true);
              niveau.getShake().setIsShaking(true);


          }
        niveau.addNewObject(mario);
      }
      if(score % 3 == 0 && !isDenis && shouldThisBeInsane(3)) {
        com.mygdx.superpaperplane.Denis denis = new com.mygdx.superpaperplane.Denis(0, 70, niveau, this);
        niveau.addNewObject(denis);
      }
      if(score % 3 == 0 && !niveau.getAvion().getIsBonused() && shouldThisBeInsane(3)){
        Bonus bonus = new Bonus(random.nextInt(5)*20+65, -20, niveau);
        niveau.addNewObject(bonus);
      }
      if(score % 3 == 0 && niveau.getAvion().getNombreDeViesRestantes()<3 && shouldThisBeInsane(6)){
        com.mygdx.superpaperplane.Tacos tac = new com.mygdx.superpaperplane.Tacos(random.nextInt(400)+50, -80 - random.nextInt(50), niveau);
        niveau.addNewObject(tac);
      }
      if(score % 3 == 0 && shouldThisBeInsane(5)){
    	  Jagger jagger = new Jagger(50,50,niveau,this);
    	  niveau.addNewObject(jagger);
      }
      if(score % 2 == 0 && shouldThisBeInsane(4)){
        Pumpkin pump = new Pumpkin(0,750,niveau,this);
        niveau.addNewObject(pump);
      }
      if(score % 3 == 0 && shouldThisBeInsane(6)){
        Boo boo = new Boo(0,0,niveau);
        niveau.addNewObject(boo);
      }
      if(!isShootingStar && shouldThisBeInsane(30)){
          ShootingStar shootingStar = new ShootingStar
        		  (0, 0, niveau, this);
          niveau.addNewObject(shootingStar);
        niveau.eteindrefeu();
          isShootingStar = true;
      }

      

    }
  }

  public boolean shouldThisBeInsane(int x){
    return random.nextInt(x) == 0;
  }
}
