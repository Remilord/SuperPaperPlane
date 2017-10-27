package com.mygdx.superpaperplane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;


public class Os extends GameObject {
    private Texture os;
    private Random randpositiony;
    private int ydeplacement;
    private static Music osound = Gdx.audio.newMusic(Gdx.files.internal("son/bone.ogg"));
    public Os(int x, int y, Niveau niveau) {
        super(x, y, niveau);
        this.largeur = 50;
        this.hauteur = 25;
        os = ImageBanque.getCaseImage(55);
        osound.play();
        objectType = ObjectType.ENEMY;
        randpositiony = new Random();
        ydeplacement = randpositiony.nextInt(15)+1;
    }
    @Override
    public Texture getImage() {
        return os;
    }

    @Override
    public void deplacement(int vitesse) {
        setPositionY(getPositionY() + ydeplacement);
        setPositionX(getPositionX() + 5);
        if (getPositionY() >=850 || getPositionX()<=0) {
            this.needsToBeRemoved=true;
        }
    }

    @Override
    public boolean remove() {
        if (getPositionY() >= 850 || getPositionX()<=0) {
            return true;
        }
        return false;
    }

    @Override
    public void whenGetHit() {
        needsToBeRemoved = true;
        niveau.getAvion().loosingLife();
    }

    @Override
    public boolean canHit(GameObject g) {
        if (g.getObjectType() == ObjectType.PLAYER) {
            return true;
        }
        return false;
    }

    @Override
    public void dispose(){
        os.dispose();
    }
}
