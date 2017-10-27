package com.mygdx.superpaperplane;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class Bat extends GameObject {
    private Texture bat;
    public Bat(int x, int y, Niveau niveau) {
        super(x, y, niveau);
        this.largeur = 500;
        this.hauteur = 800;
        bat = ImageBanque.getCaseImage(59);
        objectType = ObjectType.DECOR;
    }
    @Override
    public void whenGetHit() {}
    @Override
    public boolean remove() {
        if(getPositionY()>=880) {
            return true;
        }
        return false;
    }
    @Override
    public void dispose(){
        bat.dispose();
    }
    @Override
    public Texture getImage() {
            return this.bat;
    }
    @Override
    public void deplacement(int vitesse) {
        setPositionY(getPositionY()+33);
    }
}
