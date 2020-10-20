package de.nroffler.me.box2dstatics.Shaped;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

import de.nroffler.me.box2dstatics.StaticCollidableWorldObject;
import de.nroffler.me.main.BitFilter;
import de.nroffler.me.main.Statics;

public class StrictLShaped extends StaticCollidableWorldObject {

    public StrictLShaped(World world, float x, float y) {
        super(world, x, y);
    }

    @Override
    public void defineObject() {
        width = (40 + new Random().nextInt(30)) / Statics.PPM;

        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(x, y);
        body = world.createBody(bdef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width+0.17f,  1 / Statics.PPM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = BitFilter.TRIGGER_OBJECT;
        triggerFixture = body.createFixture(fixtureDef);
        triggerFixture.setUserData(this);

        shape = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-width*Statics.PPM-20 ,-3).scl(1 / Statics.PPM);
        vertice[1] = new Vector2(width*Statics.PPM+20,-3).scl(1 / Statics.PPM);
        vertice[2] = new Vector2(-width*Statics.PPM-20 ,-12).scl(1 / Statics.PPM);
        vertice[3] = new Vector2(width*Statics.PPM+20 ,-12).scl(1 / Statics.PPM);
        shape.set(vertice);
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = BitFilter.COLLIDE_OBJECT;
        body.createFixture(fixtureDef).setUserData(this);

        if (x > (200 / Statics.PPM)) {
            shape = new PolygonShape();
            Vector2[] vertice2 = new Vector2[4];
            vertice2[0] = new Vector2(width * Statics.PPM + 20, 200).scl(1 / Statics.PPM);
            vertice2[1] = new Vector2(width * Statics.PPM, 200).scl(1 / Statics.PPM);
            vertice2[2] = new Vector2(width * Statics.PPM + 20, 0).scl(1 / Statics.PPM);
            vertice2[3] = new Vector2(width * Statics.PPM, 0).scl(1 / Statics.PPM);
            shape.set(vertice2);
            fixtureDef.shape = shape;
            fixtureDef.filter.categoryBits = BitFilter.COLLIDE_OBJECT;
            body.createFixture(fixtureDef).setUserData(this);
        } else {
            shape = new PolygonShape();
            Vector2[] vertice3 = new Vector2[4];
            vertice3[0] = new Vector2(-width * Statics.PPM - 20, 200).scl(1 / Statics.PPM);
            vertice3[1] = new Vector2(-width * Statics.PPM, 200).scl(1 / Statics.PPM);
            vertice3[2] = new Vector2(-width * Statics.PPM - 20, 0).scl(1 / Statics.PPM);
            vertice3[3] = new Vector2(-width * Statics.PPM, 0).scl(1 / Statics.PPM);
            shape.set(vertice3);
            fixtureDef.shape = shape;
            fixtureDef.filter.categoryBits = BitFilter.COLLIDE_OBJECT;
            body.createFixture(fixtureDef).setUserData(this);
        }

        shape.dispose();
    }
}
