package com.mygdx.game;

import com.badlogic.gdx.math.Polygon;

public class Hitbox {
	private Avion avion;
	private Polygon polygon;

	public Hitbox(Avion avion) {
		super();
		this.avion = avion;
		polygon = new Polygon();
	}

	public Polygon getHitbox() {
		return polygon;
	}

	public Polygon setHitboxAvion() {
		float[] tab = new float[6];
		if (!avion.getIsLittle()) {
			if (avion.getPositionAvion() == 0) { /* Position vers le bas */
				tab[0] = avion.getPositionX() + 27;
				tab[1] = avion.getPositionY() + 73;
				tab[2] = avion.getPositionX() + 80;
				tab[3] = avion.getPositionY() + 75; //
				tab[4] = avion.getPositionX() + 47;
				tab[5] = avion.getPositionY();
				
			}
			else if(avion.getPositionAvion() == 1){ /*droite*/
				tab[0] = avion.getPositionX() + 52;
				tab[1] = avion.getPositionY() + 73;
				tab[2] = avion.getPositionX();
				tab[3] = avion.getPositionY() + 30;
				tab[4] = avion.getPositionX() + 100;
				tab[5] = avion.getPositionY() + 3;
				
			}
			else if(avion.getPositionAvion() == 2){ /*gauche*/
				
				tab[0] = avion.getPositionX() + 48;
				tab[1] = avion.getPositionY() + 74;
				tab[2] = avion.getPositionX() + 97;
				tab[3] = avion.getPositionY() + 30;
				tab[4] = avion.getPositionX() + 2;
				tab[5] = avion.getPositionY() + 3;
			}
				
		}
		else{
			if (avion.getPositionAvion() == 0) { 
				tab[0] = avion.getPositionX() + 14;
				tab[1] = avion.getPositionY() + 37;
				tab[2] = avion.getPositionX() + 40;
				tab[3] = avion.getPositionY() + 38;
				tab[4] = avion.getPositionX() + 23;
				tab[5] = avion.getPositionY() + 2;
			}
			else if(avion.getPositionAvion() == 1){
				tab[0] = avion.getPositionX() + 25;
				tab[1] = avion.getPositionY() + 32;
				tab[2] = avion.getPositionX() + 0;
				tab[3] = avion.getPositionY() + 12;
				tab[4] = avion.getPositionX() + 48;
				tab[5] = avion.getPositionY() + 2;
			}
			else if(avion.getPositionAvion() == 2){
				tab[0] = avion.getPositionX() + 24;
				tab[1] = avion.getPositionY() + 32;
				tab[2] = avion.getPositionX() + 49;
				tab[3] = avion.getPositionY() + 12;
				tab[4] = avion.getPositionX() + 1;
				tab[5] = avion.getPositionY() + 2;
			}
		} 
		polygon.setVertices(tab);
		return polygon;
	}
}



