package com.mcelrea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class EndScreen implements Screen {

    //SpriteBatch allows the drawing of sprites (2D images) to the screen
    private SpriteBatch spriteBatch;

    //default font
    private BitmapFont defaultFont;

    //OrthographicCamera is top-down camera
    private OrthographicCamera camera;

    //Controls the world view through he camera
    private Viewport viewport;

    //Breadcrumb back to MyGdxGame
    MyGdxGame game;

    public EndScreen(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera(GameScreen.WIDTH, GameScreen.HEIGHT);
        viewport = new FitViewport(GameScreen.WIDTH, GameScreen.HEIGHT, camera);
        defaultFont = new BitmapFont();
    }

    public void userInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.changeScreentoStart();
        }
    }

    @Override
    public void render(float delta) {
        clearScreen();

        userInput();

        spriteBatch.begin();
        defaultFont.draw(spriteBatch,"GAME OVER", 500, 300);
        defaultFont.draw(spriteBatch,"FINAL SCORE: " + GameScreen.finalTime, 500, 250);
        defaultFont.draw(spriteBatch,"Press Spacebar to Continue...", 500, 200);
        spriteBatch.end();
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

    @Override
    public void dispose() {

    }

    public void clearScreen() {
        Gdx.gl20.glClearColor(0,0,0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
