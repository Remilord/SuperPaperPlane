package com.mygdx.superpaperplane;

import com.badlogic.gdx.graphics.Texture;


public class Os extends GameObject {
    private Texture os;
    public Os(int x, int y, Niveau niveau) {
        super(x, y, niveau);
        this.largeur = 50;
        this.hauteur = 25;
        os = ImageBanque.getCaseImage(55);
    }
    @Override
    public Texture getImage() {
        return os;
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
