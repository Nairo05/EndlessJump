package de.nroffler.me.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.nroffler.me.screens.SplashScreen;

public class EndlessGame extends Game{

	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		System.out.println("---> EndlessGame.java");
		setScreen(new SplashScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
	}
}
