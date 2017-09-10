package com.mygdx.superpaperplane;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;

public class Tacos extends com.mygdx.superpaperplane.GameObject {
    private Texture tacos;
    private Random rand;
    public Tacos(int x, int y, Niveau niveau) {
        super(x, y, niveau);
        objectType = ObjectType.BONUS;
        this.tacos = com.mygdx.superpaperplane.ImageBanque.getCaseImage(47);
        this.rand = new Random();
        this.positionX = rand.nextInt(350)+50;
        this.positionY = -80 - rand.nextInt(50);
        this.hauteur = 75;
        this.largeur = 75;
    }
    @Override
    public void dispose(){
        tacos.dispose();
    }

    public Texture getImage() {
        return this.tacos;
    }


    @Override
    public void deplacement(int vitesse) {
        setPositionY(getPositionY() + vitesse+1);
        if (getPositionY() > 880) {
            this.needsToBeRemoved=true;
        }
    }

    @Override
    public void whenGetHit() {
       int nbvie = niveau.getAvion().getNombreDeViesRestantes()+1;
        niveau.getAvion().setNombreDeViesRestantes(nbvie);
        needsToBeRemoved = true;
    }

    @Override
    public boolean canHit(com.mygdx.superpaperplane.GameObject g) {
        if (g.getObjectType() == ObjectType.PLAYER)
            return true;
        return false;
    }
}
