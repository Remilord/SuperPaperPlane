package com.mygdx.superpaperplane;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;


public class Bonbon extends GameObject {
    private Texture bonbon;
    private Random rand;
    public Bonbon(int x, int y, Niveau niveau) {
        super(x, y, niveau);
        objectType = ObjectType.BONUS;
        this.bonbon = com.mygdx.superpaperplane.ImageBanque.getCaseImage(53);
        this.hauteur = 42;
        this.largeur = 41;
    }
    @Override
    public void dispose(){
        bonbon.dispose();
    }

    public Texture getImage() {
        return this.bonbon;
    }


    @Override
    public void deplacement(int vitesse) {
        setPositionY(getPositionY() + 15);
        setPositionX(getPositionX() + 5);
        if (getPositionY() >=850 || getPositionX()<=0) {
            this.needsToBeRemoved=true;
        }
    }

    @Override
    public void whenGetHit() {
        niveau.gainScore();
        needsToBeRemoved = true;
    }

    @Override
    public boolean canHit(com.mygdx.superpaperplane.GameObject g) {
        if (g.getObjectType() == ObjectType.PLAYER)
            return true;
        return false;
    }
}
