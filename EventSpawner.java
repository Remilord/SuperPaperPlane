import java.util.Random;

public class EventSpawner{

  private Niveau niveau;
  private boolean isMario = false;
  private boolean isDenis = false;
  private boolean isDenisShouting = false;

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


  private void addEvent(GameObject g){
    niveau.addNewObject(g);
  }

  private void addEvent(int i){

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

  public void calculateEvents(){
    if(score != niveau.getScore()){
      score = niveau.getScore();
      if(score % 10 == 0 && !isMario && shouldThisBeInsane()) {
        Mario mario = new Mario(0, 0, niveau, this);
        niveau.addNewObject(mario);
      }
      if(score % 1 == 0 && !isDenis && shouldThisBeInsane()) {
        Denis denis = new Denis(0, 650, niveau, this);
        niveau.addNewObject(denis);
      }
      if(score % 10 == 0 && !niveau.getAvion().getIsBonused() && shouldThisBeInsane()){
        Bonus bonus = new Bonus(random.nextInt(5)*20+65, 900, niveau);
        niveau.addNewObject(bonus);
      }
    }
  }

  private boolean shouldThisBeInsane(){
    return random.nextInt(3) == 0;
  }
}
