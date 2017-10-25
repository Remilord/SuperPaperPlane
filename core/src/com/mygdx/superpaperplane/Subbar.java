package com.mygdx.superpaperplane;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;

public class Subbar {
	private String actualSentence;
	private ArrayList<String> sentences;
	private Texture image;
	private Niveau niveau;
	private Random random = new Random();

	public Subbar(Niveau niveau) {
		this.niveau = niveau;
		sentences = new ArrayList<String>();
		actualSentence = new String("");
		
		if (niveau.getInsane()){
			sentences.add("INSAAAANE !");
			sentences.add("404");
			sentences.add("AH !");
			sentences.add("DON'T EVEN TRY");
		}

		else{
			sentences.add("BORING LEVEL");
		}

		actualSentence = sentences.get(0);
		
		image = niveau.loadBufferedImage("image" + File.separator + "barreinfo" + File.separator + "base.png");
	}

	public String getActualSentence() {

		if(niveau.getActualFrame() == 40) {
			randomChange();
		}
		return actualSentence;
	}

	public void setActualSentence(String actualSentence) {
		this.actualSentence = actualSentence;
	}
	public void setActualSentence(int x){
		this.actualSentence = sentences.get(x);
	}
	public Texture getImage() {
		return image;
	}

	private void randomChange(){
		if(random.nextInt(3) == 0){
			setActualSentence(random.nextInt(sentences.size()));
		}
	}

}
