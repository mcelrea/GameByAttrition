package com.mcelrea;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {

	@Override
	public void create() {
		setScreen(new StartScreen(this));
	}

	public void changeScreenToGameplay() {
		setScreen(new GameScreen(this));
	}

	public void changeScreentoStart() {
		setScreen(new StartScreen(this));
	}
}
