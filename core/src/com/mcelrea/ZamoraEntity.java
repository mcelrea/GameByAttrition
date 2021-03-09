package com.mcelrea;


import com.badlogic.gdx.graphics.Color;

public class ZamoraEntity extends Entity{
    private float speed = 60;
    private Entity target;
    //private long timeDelay = 2000;
    //private long lastIncrement;

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

   /*
   public boolean validSpeed(Entity t){
       return(t.getxVel() == );
   }

   //Instead of checking the speed of each entity, we should check their x and y value and see if they are "out of bounds"
   public boolean validPos(Entity t){
       return(t.getX() == 1281 || //checking if it is beyond max x
              t.getX() == -1 || //min x
              t.getY() == 721 ||
              t.getY() == -1);

   }
    */

    public void actSkeleton(float delta){
        if(x < target.x)//If we want the entity to chase us
            x += speed * delta;
        else
            x -= speed * delta;

        if(y < target.y)
            y += speed * delta;
        else
            y -= speed * delta;
    }

    //So what do I wanna fix with this.
    //I would like to make it smoother. See if I can accelerate this more slowly all while chasing you.
    //Add a System.Millis so it takes time for it to speed up, Later on i would like for it to
    @Override
    public void act(float delta) {
        float initialSpeed = 40;

        if(distance(x,y,target) < 650 && distance(x,y,target) > 150){//&& distance(x,y,target) < 750
            speed *= 1.5;
            //System.out.println(speed);
        }
        else{
            speed = initialSpeed;
        }
        actSkeleton(delta);

    }
}

/*
float initialSpeed = 40;

       if(lastIncrement+timeDelay < System.currentTimeMillis() && distance(x,y,target) > 250){//&& distance(x,y,target) < 750
           speed += speed *2;
           //System.out.println(speed);
       }
       else{
           speed = initialSpeed;
       }
       actSkeleton(delta);
       lastIncrement = System.currentTimeMillis();
*/


