package com.mygdx.superpaperplane;

import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.Random;

/**
 * Created by Nofix on 08/09/2017.
 */

public class Shake {
    private float duration;
    private float elapsed;
    private int baseCameraPositionX;
    private int baseCameraPositionY;
    private boolean isShaking;
    public Shake(float duration, int baseCameraPositionX, int baseCameraPositionY) {
        this.elapsed = 0;
        this.duration = duration;
        this.baseCameraPositionX = baseCameraPositionX;
        this.baseCameraPositionY = baseCameraPositionY;
        this.isShaking = false;
    }


    public void update(float delta, OrthographicCamera camera) {

        // Only shake when required.
        if(elapsed < duration) {

            // Calculate the amount of shake based on how long it has been shaking already
            float currentPower = 10 * camera.zoom * ((duration - elapsed) / duration);
            Random random = new Random();
            float x = (random.nextFloat() - 0.5f) * 1.5f * currentPower;
            float y = (random.nextFloat() - 0.5f) * 1.5f * currentPower;
            camera.translate(-x, -y);

            // Increase the elapsed time by the delta provided.
            this.elapsed += delta;
            System.out.println("Elasped = "+elapsed+" Duration = "+duration);
        } else {
            this.isShaking = false;
        }
    }
    public void setIsShaking(boolean b){
        this.isShaking = b;
        this.elapsed = 0;
    }

    public boolean getIsShaking(){
        return this.isShaking;
    }
}
