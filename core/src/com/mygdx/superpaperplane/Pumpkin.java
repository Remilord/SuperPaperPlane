package com.mygdx.superpaperplane;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class Pumpkin extends GameObject {
    private Texture pumpskin;
    private boolean pumpkinToTheLeft = false;
    private com.mygdx.superpaperplane.EventSpawner eventSpawner;
    private int type;
    private int numberofobjects;
    private int countdeathtime;
    private static Music pop = Gdx.audio.newMusic(Gdx.files.internal("son/pop.ogg"));
    public Pumpkin(int x, int y, Niveau niveau, com.mygdx.superpaperplane.EventSpawner e) {
        super(x, y, niveau);
        Random randtype = new Random();
        Random randnumberofobjects = new Random();
        objectType = ObjectType.BONUS;
        eventSpawner = e;
        this.largeur = 100;
        this.hauteur = 100;
        countdeathtime =0;
        type = randtype.nextInt(3)+1;
        numberofobjects = randnumberofobjects.nextInt(10)+4;
        switch (type) {
            case 1:
                pumpskin = com.mygdx.superpaperplane.ImageBanque.getCaseImage(52);
                break;
            case 2:
                pumpskin = com.mygdx.superpaperplane.ImageBanque.getCaseImage(54);
                break;
            case 3:
                pumpskin = com.mygdx.superpaperplane.ImageBanque.getCaseImage(56);
                break;
            default:
                pumpskin = com.mygdx.superpaperplane.ImageBanque.getCaseImage(56);
                break;
        }
    }
    @Override
    public void dispose(){
        pumpskin.dispose();
    }
    @Override
    public Texture getImage() {
        return this.pumpskin;
    }

    @Override
    public boolean needsToCreate() {
        if (getPositionY()<=50)
            return true;
        else
            return false;
    }

    @Override
    public com.mygdx.superpaperplane.GameObject createGameObject() {
        switch(this.type) {
            case 1:
                return new com.mygdx.superpaperplane.Fillon(getPositionX(), getPositionY(), niveau);
            case 2:
                return new com.mygdx.superpaperplane.Os(getPositionX(), getPositionY(), niveau);
            case 3:
                return new com.mygdx.superpaperplane.Bonbon(getPositionX(), getPositionY(), niveau);
            default:
                return new com.mygdx.superpaperplane.Bonbon(getPositionX(), getPositionY(), niveau);
        }
    }

    @Override
    public void deplacement(int vitesse) {
        if(getPositionY()<=50 && countdeathtime==0) {
            pumpskin = com.mygdx.superpaperplane.ImageBanque.getCaseImage(58);
            pop.play();
        }
        if(getPositionY()<=50) {
            countdeathtime++;
        } else {
            if (pumpkinToTheLeft) {
                setPositionX(getPositionX() - 25);
            } else {
                setPositionX(getPositionX() + 25);
            }

            if (getPositionX() >= 405) {
                pumpkinToTheLeft = true;
            } else if (getPositionX() <= 0) {
                pumpkinToTheLeft = false;
            }
            setPositionY(getPositionY() - 20);
        }
    }
    @Override
    public void whenGetHit() {
    }
    @Override
    public boolean canHit(GameObject g) {
        return false;
    }
    @Override
    public boolean remove() {
        if (countdeathtime == numberofobjects) {
            return true;
        }
        return false;
    }
}
