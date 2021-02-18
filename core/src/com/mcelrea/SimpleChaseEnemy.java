package com.mcelrea;

import com.badlogic.gdx.graphics.Color;

public class SimpleChaseEnemy extends Entity {

    private float speed = 60;
    private Entity target;

    public SimpleChaseEnemy(Entity target) {
        super();
        this.target = target;
    }

    public SimpleChaseEnemy(float x, float y, int height, int width, Color color, Entity target) {
        super(x,y,height,width,color);
        this.target = target;
    }

    @Override
    public void act(float delta) {
        if(x < target.x)
            x += speed * delta;
        else
            x -= speed * delta;

        if(y < target.y)
            y += speed * delta;
        else
            y -= speed * delta;
    }
}
