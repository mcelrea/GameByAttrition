package com.mcelrea;

import com.badlogic.gdx.graphics.Color;

public class PatrolEntity extends Entity {
    private float speed = 100;
    private Entity target;

    public PatrolEntity(Entity target) {
        super();
        this.target = target;
    }

    public PatrolEntity(float x, float y, int height, int width, Color color, Entity target) {
        super(x, y, height, width, color);
        this.target = target;
    }

    @Override
    public void act(float delta) {
        if (x < 30 && y < 670)
            y += speed * delta;
        else if (y >= 670 && x < 1230)
            x += speed * delta;

        else if (x >= 1230 && y >= 30)
            y -= speed * delta;
        else if (y < 30 && x >= 30)
            x -= speed * delta;
    }

}



