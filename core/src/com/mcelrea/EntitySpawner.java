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

            int choice = (int)(1 + Math.random() * 5);
            if(choice == 1) {
                entities.add(new NewEnemy((float) (Math.random() * GameScreen.WIDTH),
                        (float) (Math.random() * GameScreen.HEIGHT),
                        20,
                        20,
                        new Color(0, 0, 1, 1),
                        GameScreen.player));
            }
            else if(choice == 2) {
                entities.add(new TrashEntity((float) (Math.random() * GameScreen.WIDTH),
                        (float) (Math.random() * GameScreen.HEIGHT),
                        20,
                        20,
                        new Color(0, 0, 1, 1),
                        GameScreen.player));
            }
            else if(choice == 3) {
                entities.add(new ZamoraEntity((float) (Math.random() * GameScreen.WIDTH),
                        (float) (Math.random() * GameScreen.HEIGHT),
                        20,
                        20,
                        new Color(0, 0, 1, 1),
                        GameScreen.player));
            }
            else if(choice == 4) {
                entities.add(new FastChaseEnemy((float) (Math.random() * GameScreen.WIDTH),
                        (float) (Math.random() * GameScreen.HEIGHT),
                        20,
                        20,
                        new Color(0, 0, 1, 1),
                        GameScreen.player, 1000, 150));
            }
            else if(choice == 5) {
                entities.add(new PatrolEntity((float) (10),
                        (float) (10),
                        20,
                        20,
                        new Color(0, 0, 1, 1),
                        GameScreen.player));
            }
            lastSpawnTime = System.currentTimeMillis();//reset the timer
        }
    }
}
