package de.nroffler.me.tools;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import javax.swing.text.MutableAttributeSet;

import de.nroffler.me.main.Statics;
import de.nroffler.me.screens.PlayScreen;

public class MyInputPorcessor implements InputProcessor {

    Vector2 down = new Vector2();
    Vector2 up = new Vector2();

    private PlayScreen playScreen;

    public MyInputPorcessor(PlayScreen playScreen){
        this.playScreen = playScreen;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.ESCAPE:
                break;
            case Input.Keys.SPACE:
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        down.x = screenX;
        down.y = screenY;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        up.y = screenY;
        up.x = screenX;

        float yvel = Math.abs(down.y-up.y)*1f;
        float xvel = (down.x-up.x)*1f;

        if (yvel > 130){
            yvel = 130;
        }

        if (xvel > 140){
            xvel = 140;
        }
        if (xvel < -140){
            xvel = -140;
        }

        playScreen.getPlayer().jump(new Vector2(xvel / Statics.PPM / 2, yvel / Statics.PPM));

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
