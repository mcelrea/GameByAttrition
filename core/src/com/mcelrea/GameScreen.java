package com.mcelrea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class GameScreen implements Screen {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    private boolean gameOver = false;

    public static Player player;

    ArrayList<Entity> entities;
    public static ArrayList<Bullet> playerBullets;

    EntitySpawner entitySpawner;

    public static long startTime;

    //SpriteBatch allows the drawing of sprites (2D images) to the screen
    private SpriteBatch spriteBatch;

    //ShapeRendered allows the drawing of shapes to the screen
    private ShapeRenderer shapeRenderer;

    //default font
    private BitmapFont defaultFont;

    //OrthographicCamera is top-down camera
    private OrthographicCamera camera;

    //Controls the world view through he camera
    private Viewport viewport;

    //Breadcrumb back to MyGdxGame
    MyGdxGame game;

    public GameScreen(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        viewport = new FitViewport(WIDTH, HEIGHT, camera);
        defaultFont = new BitmapFont();
        gameOver = false;
        player = new Player();
        entities = new ArrayList<Entity>();
        playerBullets = new ArrayList<Bullet>();
        entitySpawner = new EntitySpawner(1000);

        createEntities();

        startTime = System.currentTimeMillis();//time stamp
    }

    public void renderGUI(SpriteBatch spriteBatch) {
        defaultFont.draw(spriteBatch, "" + ((System.currentTimeMillis()-startTime)/1000), 600, 650);
    }

    public void createEntities() {
        entities.add(new EatableEntity(400, 400, 20, 20, new Color(0,0,1,1)));
        entities.add(new PoisonEntity(600, 200, 20, 20, new Color(0,1,0,1)));
        entities.add(new SimpleChaseEnemy((int)(Math.random() * WIDTH),
                (int)(Math.random()*HEIGHT),
                30,
                30,
                new Color(1,0,1,1),
                player));
    }

    public void renderEntities() {
        for(Entity e: entities) {
            if(e.isAlive()) {
                e.render(shapeRenderer);
                e.renderCollisionBox(shapeRenderer);
            }
        }
    }

    public void renderPlayerBullets() {
        for(Bullet b: playerBullets) {
            if(b.isAlive()) {
                b.render(shapeRenderer);
                b.renderCollisionBox(shapeRenderer);
            }
        }
    }

    public void checkCollisions() {
        for(int i=entities.size()-1; i >= 0; i--) {
            if(player.isColliding(entities.get(i))) {
                if(entities.get(i) instanceof EatableEntity) {
                    entities.remove(i);
                    player.setHeight(player.getHeight()+5);
                    player.setWidth(player.getWidth()+5);
                }
                else if(entities.get(i) instanceof PoisonEntity && entities.get(i).isAlive()) {
                    //entities.remove(i);
                    entities.get(i).setAlive(false);
                    player.setHeight(player.getHeight()-5);
                    player.setWidth(player.getWidth()-5);
                }
                else if(entities.get(i) instanceof SimpleChaseEnemy) {
                    gameOver = true;
                }
                else if (entities.get(i) instanceof  NewEnemy) {
                    if (((NewEnemy) entities.get(i)).isEatable()) {
                        entities.remove(i);
                        player.setHeight(player.getHeight()-2);
                        player.setWidth(player.getWidth()-2);
                    }
                    else {
                        gameOver = true;
                    }
                }

            }
        }
    }

    public void updateEntities(float delta) {
        for(Entity e: entities) {
            e.act(delta);
        }
    }

    public void updatePlayerBullets(float delta) {
        for(int i = playerBullets.size()-1; i >= 0; i--) {
            Bullet b = playerBullets.get(i);
            b.act(delta);
            if(b.isOffScreen())
                playerBullets.remove(i);
        }
    }

    @Override
    public void render(float delta) {
        clearScreen();

        if(!gameOver) {
            //updates, input, AI
            player.act(delta);
            updateEntities(delta);
            updatePlayerBullets(delta);
            entitySpawner.act(entities);

            //check for collisions
            checkCollisions();

            spriteBatch.begin();
            renderGUI(spriteBatch);
            spriteBatch.end();

            //draw the updates
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            renderEntities();
            renderPlayerBullets();
            player.render(shapeRenderer);
            player.renderCollisionBox(shapeRenderer);
            shapeRenderer.end();
        }
        else { //game is over
            game.changeScreentoStart();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public void clearScreen() {
        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void dispose() {

    }
}
