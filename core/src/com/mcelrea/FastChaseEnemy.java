package com.mcelrea;

import com.badlogic.gdx.graphics.Color;

public class FastChaseEnemy extends Entity{
    private float speed = 60;
    private float maxSpeed;
    private Entity target;
    private long delay;
    private long currentTime;
    private int minHeight = 10;
    private int minWidth = 10;


    public FastChaseEnemy(Entity target) {
        super();
        this.target = target;
    }

    public FastChaseEnemy(float x, float y, int height, int width, Color color, Entity target, long delay, float maxSpeed) {
        super(x, y, height, width, color);
        this.target = target;
        this.delay = delay;
        this.maxSpeed = maxSpeed;
        currentTime = System.currentTimeMillis();
    }

    @Override
    public void act(float delta) {
        split();

        if(x < target.x){
            x += speed*delta;
        }
        else{
            x -= speed*delta;
        }

        if(y<target.y){
            y+= speed*delta;
        }
        else{
            y-= speed*delta;
        }
    }

    public void split(){
        if(System.currentTimeMillis() - currentTime >= delay && height >= minHeight && width >= minWidth){
            height -= 2;
            width -= 2;
            speed = maxSpeed - ((width*height)/10);
            currentTime = System.currentTimeMillis();
        }
    }


}
