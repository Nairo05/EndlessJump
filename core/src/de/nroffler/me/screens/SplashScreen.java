package de.nroffler.me.screens;

import com.badlogic.gdx.Screen;

import de.nroffler.me.main.EndlessGame;

public class SplashScreen implements Screen {

    private EndlessGame endlessGame;

    public SplashScreen(EndlessGame endlessGame){
        this.endlessGame = endlessGame;
    }

    @Override
    public void show() {
        System.out.println("---> SplashScreen");
        endlessGame.setScreen(new PlayScreen(endlessGame));
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

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
}
