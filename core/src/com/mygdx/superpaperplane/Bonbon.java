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
        this.hauteur = 75;
        this.largeur = 75;
    }
}
