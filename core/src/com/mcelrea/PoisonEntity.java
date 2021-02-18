package com.mcelrea;

import com.badlogic.gdx.graphics.Color;

public class PoisonEntity extends Entity {

    private long timeDelay = 2000;
    private long vanishTime;

    public PoisonEntity() {
        super();
    }

    public PoisonEntity(float x, float y, int height, int width, Color color) {
        super(x,y,height,width,color);
    }

    @Override
    public void act(float delta) {
        if(alive)
            super.act(delta);
        else { //not alive
            if(vanishTime+timeDelay < System.currentTimeMillis()) {
                alive = true;
                xVel = (float)(-150 + Math.random() * 301);
                yVel = (float)(-150 + Math.random() * 301);
                x = (float)(Math.random() * GameScreen.WIDTH);
                y = (float)(Math.random() * GameScreen.HEIGHT);
            }
        }
    }

    @Override
    public void setAlive(boolean alive) {
        this.alive = alive;
        if(alive == false)
            vanishTime = System.currentTimeMillis();//time stamp
    }
}
