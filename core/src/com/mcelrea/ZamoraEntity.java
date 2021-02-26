package com.mcelrea;

import com.badlogic.gdx.graphics.Color;

public class ZamoraEntity extends Entity{
    private float speed = 60;
    private Entity target;

    public ZamoraEntity(Entity target) {
        super();
        this.target = target;
    }

    public ZamoraEntity(float x, float y, int height, int width, Color color, Entity target) {
        super(x,y,height,width,color);
        this.target = target;
    }

    public int distance(float x, float y, Entity t){
        return (int) Math.sqrt((t.y - y) * (t.y - y) + (t.x - x) * (t.x - x));
    }

    @Override
    public void act(float delta) {
        if(distance(x,y,target) > 250){
            speed += speed + 1;

            //System.out.println(speed);

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
}

