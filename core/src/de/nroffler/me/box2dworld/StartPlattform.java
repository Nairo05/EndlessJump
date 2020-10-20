package de.nroffler.me.box2dworld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import de.nroffler.me.main.Statics;

public class StartPlattform {

    private float x;
    private World world;

    public StartPlattform(World world, float x){
        this.world = world;
        this.x = x;

        definePlatform();
    }

    private void definePlatform(){
        BodyDef bodydef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();

        bodydef.type = BodyDef.BodyType.StaticBody;
        bodydef.position.set(0,0);

        ChainShape groundshape = new ChainShape();
        groundshape.createChain(new Vector2[]{new Vector2((x-50) / Statics.PPM, 0), new Vector2((x+50) / Statics.PPM,0)});

        fixtureDef.shape = groundshape;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0;

        world.createBody(bodydef).createFixture(fixtureDef);
    }

}
