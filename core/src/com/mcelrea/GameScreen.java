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

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    private Player player = new Player();

    ArrayList<Entity> entities = new ArrayList<Entity>();

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

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        viewport = new FitViewport(WIDTH, HEIGHT, camera);
        defaultFont = new BitmapFont();

        createEntities();
    }

    public void createEntities() {
        entities.add(new EatableEntity(400, 400, 20, 20, new Color(0,0,1,1)));
        entities.add(new PoisonEntity(600, 200, 20, 20, new Color(0,1,0,1)));
    }

    public void renderEntities() {
        for(Entity e: entities) {
            e.render(shapeRenderer);
            e.renderCollisionBox(shapeRenderer);
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
                else if(entities.get(i) instanceof PoisonEntity) {
                    entities.remove(i);
                    player.setHeight(player.getHeight()-5);
                    player.setWidth(player.getWidth()-5);
                }
            }
        }
    }

    @Override
    public void render(float delta) {
        clearScreen();

        //updates, input, AI
        player.updatePlayer(delta);

        //check for collisions
        checkCollisions();

        //draw the updates
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        renderEntities();
        player.render(shapeRenderer);
        player.renderCollisionBox(shapeRenderer);
        shapeRenderer.end();
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
