package com.mcelrea;

import com.badlogic.gdx.graphics.Color;

public class TrashEntity extends Entity {
    private float speed = 60;
    private Entity target;


    public TrashEntity(Entity target) {
        super();
        this.target = target;
    }

    public TrashEntity(float x, float y, int height, int width, Color color, Entity target) {
        super(x, y, height, width, new Color((float) Math.random(), (float) Math.random(), (float) Math.random(), (float) Math.random()));
        this.target = target;
    }

    @Override
    public void act(float delta) {
        boring();
        if (x < target.x) {
            x += speed * delta;
        } else
            x -= speed * delta;

        if (y < target.y) {
            y += speed * delta;
        } else
            y -= speed * delta;


        if (y - target.y > 200 && width <= 40) {
            y += speed * delta;
            width += 1;
        }
        if (x - target.x > 500 && height <= 40) {
            x += speed * delta;
            height += 1;
        }

    }

    public void boring() {
        if (isColliding(target)) {
            target.setColor(color);
            height = 0;
            width=0;
            int a=Math.round(color.a*100);
            int b=Math.round(color.b*100);
            int c=Math.round(color.g*100);
            int d=Math.round(color.r*100);
            //System.out.println(a);
            if(d>b ){
                target.setHeight(target.height+1);
            }
            if(c>b){
                target.setWidth(target.width+1);
            }
            if(b>a){
                target.setWidth(target.width-10);
                target.setHeight(target.height-10);
            }
            else{
                if( target.height<100&&target.width<100){
                    target.setWidth(target.width+1);
                    target.setHeight(target.height+1);
                }
            }
        }

    }

}

