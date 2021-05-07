package com.mcelrea;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

public class Chaser extends Entity {
    private Entity target = null;
    private Chaser holdItem = null;
    private float additionalSpeed = 0;
    private float speed;
    ArrayList<Object> objects = new ArrayList();

    public Chaser(Entity target) {
        super();
        this.target = target;
    }

    public Chaser(float x, float y, Entity target) {
        super(x,y,2,2,new Color(0,0,0,0));
        speed = 75;
        this.target = target;
    }

    public void setHoldItem(Chaser holdItem) {
        if (holdItem.holdItem == null) {
            this.holdItem = holdItem;
        }
    }
    @Override
    public void act(float delta) {
        if (target != null) {
            float[] vectors = new float[]{x-target.x, y-target.y};
            float[] absVectors = absArray(vectors);
            float maxNum = absVectors[findMaximum(absVectors)];

            x += speed*delta*-vectors[0]/maxNum+additionalSpeed*delta*-vectors[0]/maxNum;
            y += speed*delta*-vectors[1]/maxNum+additionalSpeed*delta*-vectors[0]/maxNum;

            if (holdItem != null) {
                holdItem.additionalSpeed = 750;
                holdItem = null;
            }
        }
        if (additionalSpeed != 0) {additionalSpeed = 0;}
    }

    private float[] absArray(float[] a) {
        float[] b = new float[2];
        for (int x = 0; x < a.length; x++) {
            if (a[x] < 0) {b[x] = -a[x];}
            else {b[x] = a[x];}
        }
        return b;
    }

    private int findMaximum(float[] a) {
        float maxNumber = a[0];
        int maxIndex = 0;
        for (int x = 1; x < a.length; x++) {
            if (a[x] > maxNumber) {
                maxNumber = a[x];
                maxIndex = x;
            }
        }
        return maxIndex;
    }
}
