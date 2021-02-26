package com.mcelrea;

import com.badlogic.gdx.graphics.Color;

public class NewEnemy extends Entity{
    private float speed = 60;
    private Entity target;
    private boolean eatable;

    public NewEnemy(Entity target) {
        super();
        this.target = target;
    }

    public NewEnemy(float x, float y, int height, int width, Color color, Entity target) {
        super(x, y, height, width, color);
        this.target = target;
    }

    @Override
    public void act(float delta) {
        if (((System.currentTimeMillis()-GameScreen.startTime)/10000)%2 == 0) {
            this.color = new Color(1,1,0,1);
            eatable = false;
            if (x < target.x) {
                x += speed * delta;
            } else {
                x -= speed * delta;
            }
            if (y < target.y) {
                y += speed * delta;
            } else {
                y -= speed * delta;
            }
        }
        else {
            this.color = new Color(0,1,1,1);
            eatable = true;
            x += xVel * delta;
            y += yVel * delta;
        }
    }

    public boolean isEatable() {
        return eatable;
    }
}


