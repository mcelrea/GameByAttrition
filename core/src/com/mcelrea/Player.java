package com.mcelrea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;

public class Player extends Entity {

    private float speed;
    private float maxSpeed = 300;

    public Player() {
        //a red box
        color = new Color(1,0,0,0);
        width = 30;
        height = 50;
        speed = 150;
    }

    public void act(float delta) {
        speed = maxSpeed - ((width*height)/10);
        xVel = 0;
        yVel = 0;

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            y += speed * delta;
            yVel = speed*3; //used for bullet velocity
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            y -= speed * delta;
            yVel = -speed*3; //used for bullet velocity
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += speed * delta;
            xVel = speed*3; //used for bullet velocity
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= speed * delta;
            xVel = -speed*3; //used for bullet velocity
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
            color.set((float)Math.random(), (float)Math.random(), (float)Math.random(), 1);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            GameScreen.playerBullets.add(new Bullet(x+width/2,y+height/2,7,color, xVel, yVel));
        }
    }

    public float getSpeed() {
        return speed;
    }
}
