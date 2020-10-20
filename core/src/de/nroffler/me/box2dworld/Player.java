package de.nroffler.me.box2dworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import de.nroffler.me.main.BitFilter;
import de.nroffler.me.main.Statics;
import de.nroffler.me.screens.PlayScreen;

public class Player {

    public Body body;

    public Player(PlayScreen playScreen, float startx){

        BodyDef bodydef = new BodyDef();
        bodydef.type = BodyDef.BodyType.DynamicBody;
        bodydef.position.set(startx / Statics.PPM, 19 / Statics.PPM);
        bodydef.fixedRotation = false;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16 / Statics.PPM, 16 / Statics.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.filter.categoryBits = BitFilter.PLAYER_OBJECT;
        fixtureDef.shape = shape;
        fixtureDef.density = 2.5f;
        fixtureDef.friction = 0.25f;

        body = playScreen.getWorld().createBody(bodydef);
        body.createFixture(fixtureDef);

    }

    public void update(){
        if (Gdx.input.isKeyPressed(Input.Keys.W) && body.getLinearVelocity().y < (200f / Statics.PPM)){
            body.applyLinearImpulse(new Vector2(0, 1.5f / Statics.PPM), body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) && body.getLinearVelocity().y > (-200f / Statics.PPM)){
            body.applyLinearImpulse(new Vector2(0, -1.5f / Statics.PPM), body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) && body.getLinearVelocity().x < (200f / Statics.PPM)){
            body.applyLinearImpulse(new Vector2(1.5f / Statics.PPM, 0), body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && body.getLinearVelocity().x > (-200f / Statics.PPM)){
            body.applyLinearImpulse(new Vector2(-1.5f / Statics.PPM, 0), body.getWorldCenter(), true);
        }
    }

    public float getY(){
        return body.getPosition().y;
    }
    public float getX(){
        return body.getPosition().x;
    }


    public void jump(Vector2 impuls){
        body.applyLinearImpulse(impuls, body.getWorldCenter(), true);
    }
}
