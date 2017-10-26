package com.mygdx.superpaperplane;

import com.badlogic.gdx.graphics.Texture;


public class Os extends GameObject {
    private Texture image;
    public Os(int x, int y, Niveau niveau) {
        super(x, y, niveau);
        this.largeur = 40;
        this.hauteur = 30;
        image = ImageBanque.getCaseImage(55);
    }
}
