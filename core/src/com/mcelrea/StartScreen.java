package com.mcelrea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class StartScreen implements Screen {

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

    static boolean readTitle = true;
    Sound titleAnnouncement = Gdx.audio.newSound(Gdx.files.internal("title screen announcement.wav"));
    Texture titleScreen = new Texture("titleScreen.jpg");
    Sound music = Gdx.audio.newSound(Gdx.files.internal("After Combat 1.mp3"));

    public StartScreen(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera(GameScreen.WIDTH, GameScreen.HEIGHT);
        viewport = new FitViewport(GameScreen.WIDTH, GameScreen.HEIGHT, camera);
        defaultFont = new BitmapFont();

        if(readTitle) {
            titleAnnouncement.play();
            readTitle = false;
        }
        music.play();
    }

    public void userInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            music.stop();
            game.changeScreenToGameplay();
        }
    }

    @Override
    public void render(float delta) {
        clearScreen();

        userInput();

        spriteBatch.begin();
        spriteBatch.draw(titleScreen, 0, 0);
        //defaultFont.draw(spriteBatch,"Press Spacebar To Play", 500, 300);
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
