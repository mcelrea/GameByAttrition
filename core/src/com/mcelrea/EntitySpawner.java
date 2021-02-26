package com.mcelrea;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

public class EntitySpawner {

    private long delay;
    private long lastSpawnTime;

    public EntitySpawner(long delay) {
        this.delay = delay;
        this.lastSpawnTime = System.currentTimeMillis();
    }

    public void act(ArrayList<Entity> entities) {
        //has enough time passed
        if(lastSpawnTime+delay < System.currentTimeMillis()) {
            /*
            int choice = (int)(1 + Math.random() * 2);
            if(choice == 1) {
                entities.add(new EatableEntity((float) (Math.random() * GameScreen.WIDTH),
                        (float) (Math.random() * GameScreen.HEIGHT),
                        20,
                        20,
                        new Color(0, 0, 1, 1)));
            }
            else {
                entities.add(new PoisonEntity((float) (Math.random() * GameScreen.WIDTH),
                        (float) (Math.random() * GameScreen.HEIGHT),
                        20,
                        20,
                        new Color(0, 1, 0, 1)));
            }*/
            entities.add(new NewEnemy((float) (Math.random() * GameScreen.WIDTH),
                    (float) (Math.random() * GameScreen.HEIGHT),
                    20,
                    20,
                    new Color(0, 0, 1, 1),
                    GameScreen.player));
            lastSpawnTime = System.currentTimeMillis();//reset the timer
        }
    }
}
