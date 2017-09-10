package com.mygdx.superpaperplane;
import java.util.Random;

public class Pattern{
  private int[] barre_1_x = new int[9];
  private int[] barre_2_x = new int[9];
  private Random r;
  private int random;

  public Pattern(){
    int x = 150;
    for(int i = 0; i < 9; i++){
      this.barre_2_x[i] = x - 600 - 120;
      this.barre_1_x[i] = x;
      x += 37;
    }
    this.r = new Random();
    this.random = r.nextInt(9);

  }

  public void generatePattern(){
    this.random = this.r.nextInt(9);
  //  System.out.println(this.random);
  }

  public  int getPatternX1(){
  //  System.out.println(this.barre_1_x[this.random]);
    return this.barre_1_x[this.random];
  }
  public  int getPatternX2(){
  //  System.out.println(this.barre_2_x[this.random]);

    return this.barre_2_x[this.random];
  }
}
