package com.mcelrea;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class DimensionBreaker extends Entity {
    private float yaw;
    private Player target;
    private int widthX = 50;
    private int heightY = 70;
    private float centerX;
    private float centerY;
    private float[] vertexX = new float[4];
    private float[] vertexY = new float[4];
    private float speed = 100;
    private int health = 6000;

    private float[] destination = new float[]{100f,100f};
    private boolean isArrive = true;

    private int longestLength = 50;

    public DimensionBreaker(Player player) {
        x = 500;
        y = 500;
        centerX = (float) (x + widthX/2.0);
        centerY = (float) (y + heightY/2.0);
        width = 50;
        height = 50;
        target = player;
        yaw = getAngle(x, y, target.x, target.y);
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(new Color(227,6,0,0));
        shapeRenderer.rect(10,690, (float) (health/3), 20);
        if (health <= 0) {return;}

        centerX = (float) (x + widthX/2.0);
        centerY = (float) (y + heightY/2.0);
        yaw = getAngle(centerX, centerY, target.x, target.y);

        getPlaneVertexes();
        //shapeRenderer.rect(centerX,centerY,5,5);
        //for (int i = 0; i < vertexX.length; i++) {shapeRenderer.rect(vertexX[i],vertexY[i],5,5);}

        shapeRenderer.setColor(new Color(53,152,254,0));

        float[] Xvectors = getDirectionVectorsPerUnit(vertexX[0],vertexY[0],vertexX[3],vertexY[3]);
        float[] Yvectors = new float[]{0f,2.5f};

        float[] pos1 = new float[]{vertexX[0],vertexY[0]};
        float[] pos2 = new float[]{vertexX[1],vertexY[1]};

        for (int i = 0; i < longestLength; i++) {
            shapeRenderer.line(pos1[0], pos1[1], pos2[0], pos2[1]);
            pos1 = mutateMoveLoc(pos1, Xvectors);
            pos2 = mutateMoveLoc(pos2, Xvectors);
        }

        int highestVertex = findMaximum(vertexY);
        int lowestVertex = findMinimum(vertexY);

        int[] vertexes = new int[]{0,1,2,3};
        vertexes[highestVertex] = -1;
        vertexes[lowestVertex] = -1;

        for (int i = 0; i < vertexes.length; i++) {
            shapeRenderer.setColor(new Color(1*(i+1),1*(i+1),1*(i+1),1*(i+1)));
            if (vertexes[i] != -1) {
                pos1[0] = vertexX[vertexes[i]];
                pos1[1] = vertexY[vertexes[i]];
                pos2[0] = vertexX[lowestVertex];
                pos2[1] = vertexY[lowestVertex];
                for (int j = 0; j < 30; j++) {
                    pos1 = mutateMoveLoc(pos1, Yvectors);
                    pos2 = mutateMoveLoc(pos2, Yvectors);
                    shapeRenderer.line(pos1[0], pos1[1], pos2[0], pos2[1]);
                }
            }
        }
    }

    @Override
    public void act(float delta) {
        float dx = destination[0]-x;
        float dy = destination[1]-y;
        if ((float) Math.pow(Math.pow(dx, 2) + Math.pow(dy, 2), 0.5) < 50) {
            isArrive = true;
        }
        if (isArrive) {
            destination = new float[]{(float) (Math.random()*GameScreen.WIDTH), (float) (Math.random()*GameScreen.HEIGHT)};
            isArrive = false;
        }
        float[] vectors = new float[]{x-destination[0], y-destination[1]};
        float[] absVectors = absArray(vectors);
        float maxNum = absVectors[findMaximum(absVectors)];

        x += speed*delta*-vectors[0]/maxNum;
        y += speed*delta*-vectors[1]/maxNum;
    }

    public void spawn(ArrayList<Entity> entities) {
        for (int i = 0; i < Math.random()*100000; i++) {
            entities.add(new Chaser(x, y, target));
        }
        if (health <= 100) {
            for (int i = 0; i < Math.random()*100000; i++) {
                entities.add(new Chaser(x, y, target));
            }
        }
    }

    public void getPlaneVertexes() {
        vertexX[0] = centerX - widthX/2;
        vertexY[0] = centerY - heightY/2;
        vertexX[1] = centerX - widthX/2;
        vertexY[1] = centerY + heightY/2;
        vertexX[2] = centerX + widthX/2;
        vertexY[2] = centerY + heightY/2;
        vertexX[3] = centerX + widthX/2;
        vertexY[3] = centerY - heightY/2;

        for (int i = 0; i < vertexX.length; i++) {
            float[] polarLoc = new float[2];
            float dx = vertexX[i] - centerX;
            float dy = vertexY[i] - centerY;

            polarLoc[0] = (float) Math.pow(Math.pow(dx, 2) + Math.pow(dy, 2), 0.5);
            polarLoc[1] = getAngle(centerX, centerY, vertexX[i], vertexY[i]) + yaw;
            float directionVectors[] = getDirectionVectors(polarLoc[1], polarLoc[0]);
            vertexX[i] = centerX + directionVectors[0];
            vertexY[i] = centerY + directionVectors[1];
        }
    }

    public float getAngle(float fromX, float fromY, float toX, float toY) {
        float dx = toX - fromX;
        float dy = toY - fromY;

        float hyp = (float) Math.pow(Math.pow(dx, 2) + Math.pow(dy, 2), 0.5);
        float yaw = radianToDegree((float) Math.asin(dy/hyp));

        if (dx < 0) {yaw = -1*(yaw-180);}
        return yaw;
    }

    public float[] getDirectionVectors(float yaw, float range) {
        float[] vectors = new float[2];
        vectors[0] = (float) (Math.cos(degreeToRadian(yaw)) * range);
        vectors[1] = (float) (Math.sin(degreeToRadian(yaw)) * range);

        return vectors;
    }

    public float[] mutateMoveLoc(float[] loc, float[] vectors) {
        return mutateJumpLoc(loc, new String[]{"x", "y"}, vectors);
    }

    public float[] mutateLoc(float[] loc, String direct, float gap) {
        if (direct.equals("x")) {loc[0] -= gap;} else if (direct.equals("y")) {loc[1] -= gap;}
        return loc;
    }

    public float[] mutateJumpLoc(float[] loc, String[] direct, float[] gap) {
        for (int i = 0; i < direct.length; i++) {loc = mutateLoc(loc, direct[i], gap[i]);}
        return loc;
    }

    public float radianToDegree(float radian) {
        return (float) (180*radian/Math.PI);
    }
    public float degreeToRadian(float degree) {
        return (float) (Math.PI*degree/180);
    }

    public float[] getDirectionVectorsPerUnit(float x1, float y1, float x2, float y2) {
        float[] vectors = new float[]{x1 - x2, y1 - y2};
        float[] absVectors = absArray(vectors);
        float maxNum = absVectors[findMaximum(absVectors)];
        longestLength = (int) maxNum;

        float[] result = new float[]{vectors[0]/maxNum, vectors[1]/maxNum};
        return result;
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

    private int findMinimum(float[] a) {
        float minNumber = a[0];
        int minIndex = 0;
        for (int x = 1; x < a.length; x++) {
            if (a[x] < minNumber) {
                minNumber = a[x];
                minIndex = x;
            }
        }
        return minIndex;
    }

    public boolean loseHealth() {
        health -= 1;
        if (health > 0) {return false;}
        return true;
    }
}
