package com.mcelrea;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Entity {
    protected float x;
    protected float y;
    protected float xVel;
    protected float yVel;
    protected int height;
    protected int width;
    protected Color color;
    protected boolean alive = true;

    public Entity() {
        color = new Color(254.0f/255,83.0f/255,78.0f/255,1);
        width = 50;
        height = 50;
        xVel = (float)(-150 + Math.random() * 301);
        yVel = (float)(-150 + Math.random() * 301);
    }

    public Entity(float x, float y, int height, int width, Color color) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = color;
        xVel = (float)(-150 + Math.random() * 301);
        yVel = (float)(-150 + Math.random() * 301);
    }

    public void act(float delta) {
        x += xVel * delta;
        y += yVel * delta;

        //right side of screen
        if(x > GameScreen.WIDTH) {
            x = -width+1;
        }
        //left side of the screen
        else if (x+width < 0) {
            x = GameScreen.WIDTH-1;
        }

        //top side of the screen
        if(y > GameScreen.HEIGHT) {
            y = -height+1;
        }
        //bottom side of the screen
        else if(y+height < 0) {
            y = GameScreen.HEIGHT-1;
        }
    }

    public boolean isColliding(Entity other) {
        return getCollisionBox().overlaps(other.getCollisionBox());
    }

    public Rectangle getCollisionBox() {
        return new Rectangle(x,y,width,height);
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rect(x,y,width,height);
    }

    public void renderCollisionBox(ShapeRenderer shapeRenderer) {
        Rectangle r = getCollisionBox();
        shapeRenderer.setColor(1,1,0,1);
        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(r.x,r.y,r.width,r.height);
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getxVel() {
        return xVel;
    }

    public void setxVel(float xVel) {
        this.xVel = xVel;
    }

    public float getyVel() {
        return yVel;
    }

    public void setyVel(float yVel) {
        this.yVel = yVel;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
