package com.mygdx.superpaperplane;


import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class Boo extends GameObject {
    private Texture boo;
    private Random rand;
    public Boo(int x, int y, Niveau niveau) {
        super(x, y, niveau);
        objectType = ObjectType.ENEMY;
        this.boo = com.mygdx.superpaperplane.ImageBanque.getCaseImage(57);
        this.rand = new Random();
        this.positionX = 0;
        this.positionY = 750 - rand.nextInt(600);
        this.hauteur = 75;
        this.largeur = 75;
    }
    @Override
    public void dispose(){
        boo.dispose();
    }

    public Texture getImage() {
        return this.boo;
    }


    @Override
    public void deplacement(int vitesse) {
        setPositionX(getPositionX() + (vitesse+1));
        if (getPositionX() >= 500) {
            this.needsToBeRemoved=true;
        }
    }

    @Override
    public void whenGetHit() {
       niveau.getAvion().setisFantom(true);
    }

    @Override
    public boolean canHit(com.mygdx.superpaperplane.GameObject g) {
        if (g.getObjectType() == ObjectType.PLAYER)
            return true;
        return false;
    }
}


