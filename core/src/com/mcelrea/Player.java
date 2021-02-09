package com.mcelrea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player extends Entity {

    private float speed;

    public Player() {
        //a red box
        color = new Color(1,0,0,0);
        width = 30;
        height = 50;
        speed = 150;
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(color);
        shapeRenderer.rect(x,y,width,height);
        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        renderCollisionBox(shapeRenderer);
    }

    public void updatePlayer(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            y += speed * delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            y -= speed * delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += speed * delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= speed * delta;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
            color.set((float)Math.random(), (float)Math.random(), (float)Math.random(), 1);
        }
    }
}
