package com.mcelrea;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Entity {
    protected float x;
    protected float y;
    protected int height;
    protected int width;
    protected Color color;

    public Entity() {
        color = new Color(254.0f/255,83.0f/255,78.0f/255,1);
        width = 50;
        height = 50;
    }

    public Entity(float x, float y, int height, int width, Color color) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = color;
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
}
