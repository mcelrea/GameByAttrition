package com.mcelrea;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Bullet extends Entity {

    private int radius;

    public Bullet() {
        super();
    }

    public Bullet(float x, float y, int radius, Color color) {
        super(x,y,radius,radius,color);
        this.radius = radius;
    }

    public Bullet(float x, float y, int radius, Color color, float xVel, float yVel) {
        super(x,y,radius,radius,color);
        this.radius = radius;
        this.xVel = xVel;
        this.yVel = yVel;
    }

    @Override
    public void act(float delta) {
        x += xVel * delta;
        y += yVel * delta;
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.circle(x, y, radius);
    }

    @Override
    public Rectangle getCollisionBox() {
        return new Rectangle(x-radius,y-radius,radius*2,radius*2);
    }

}
