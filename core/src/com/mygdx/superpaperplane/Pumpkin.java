package com.mygdx.superpaperplane;
import java.util.Random;
import com.badlogic.gdx.graphics.Texture;

public class Pumpkin extends GameObject {
    private Texture pumpskin;
    private boolean pumpkinToTheLeft = false;
    private com.mygdx.superpaperplane.EventSpawner eventSpawner;
    private int type;
    public Pumpkin(int x, int y, Niveau niveau, com.mygdx.superpaperplane.EventSpawner e) {
        super(x, y, niveau);
        Random rand = new Random();
        objectType = ObjectType.DECOR;
        eventSpawner = e;
        this.largeur = 100;
        this.hauteur = 100;
        type = rand.nextInt(3)+1;
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
        if (getPositionY()>=750)
            return true;
        else
            return false;
    }

    @Override
    public com.mygdx.superpaperplane.GameObject createGameObject() {
        switch(this.type) {
            case 1:
                return new com.mygdx.superpaperplane.Fillon(getPositionX(), getPositionY(), niveau,eventSpawner);
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
        if (pumpkinToTheLeft) {
            setPositionX(getPositionX() - 3);
        } else {
            setPositionX(getPositionX() + 3);
        }

        if (getPositionX() >= 405) {
            pumpkinToTheLeft = true;
        } else if (getPositionX() <= 0) {
            pumpkinToTheLeft = false;
        }
        setPositionY(getPositionY()+2);
    }

    @Override
    public boolean remove() {
        if (getPositionY()>=750) {
            return true;
        }
        return false;
    }
}
