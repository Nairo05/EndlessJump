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

public class CompleteVertical extends StaticCollidableWorldObject {

    private int angle = 0;
    private boolean left = false;

    public CompleteVertical(World world, float x, float y) {
        super(world, x, y);
    }

    @Override
    public void defineObject() {
        if (x > (200 / Statics.PPM)) { // SPieler ist links
            left = true;
        }


        width = 400 / Statics.PPM;
        x = 0.1f;

        angle = new Random().nextInt(100);

        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(x, y);
        body = world.createBody(bdef);

        PolygonShape shape;
        FixtureDef fixtureDef = new FixtureDef();

        if (left){
            shape = new PolygonShape();
            Vector2[] vertice = new Vector2[4];
            vertice[0] = new Vector2(0, 80 + angle).scl(1 / Statics.PPM);
            vertice[1] = new Vector2(width * Statics.PPM, 0).scl(1 / Statics.PPM);
            vertice[2] = new Vector2(width * Statics.PPM, 10).scl(1 / Statics.PPM);
            vertice[3] = new Vector2(0, 70 + angle).scl(1 / Statics.PPM);
            shape.set(vertice);
            fixtureDef.shape = shape;
            fixtureDef.filter.categoryBits = BitFilter.COLLIDE_OBJECT;
            body.createFixture(fixtureDef).setUserData(this);

            shape = new PolygonShape();
            vertice[0].y = vertice[0].y + 0.02f;
            vertice[1].y = vertice[1].y + 0.02f;
            vertice[2].y = vertice[2].y + 0.02f;
            vertice[3].y = vertice[3].y + 0.02f;
            shape.set(vertice);
            fixtureDef.shape = shape;
            fixtureDef.filter.categoryBits = BitFilter.TRIGGER_OBJECT;
            triggerFixture = body.createFixture(fixtureDef);
            triggerFixture.setUserData(this);
        } else {
            shape = new PolygonShape();
            Vector2[] vertice = new Vector2[4];
            vertice[0] = new Vector2(0, 0).scl(1 / Statics.PPM);
            vertice[1] = new Vector2(width * Statics.PPM, 80 + angle).scl(1 / Statics.PPM);
            vertice[2] = new Vector2(width * Statics.PPM, 70 + angle).scl(1 / Statics.PPM);
            vertice[3] = new Vector2(0, 10).scl(1 / Statics.PPM);
            shape.set(vertice);
            fixtureDef.shape = shape;
            fixtureDef.filter.categoryBits = BitFilter.COLLIDE_OBJECT;
            body.createFixture(fixtureDef).setUserData(this);

            shape = new PolygonShape();
            vertice[0].y = vertice[0].y + 0.02f;
            vertice[1].y = vertice[1].y + 0.02f;
            vertice[2].y = vertice[2].y + 0.02f;
            vertice[3].y = vertice[3].y + 0.02f;
            shape.set(vertice);
            fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.filter.categoryBits = BitFilter.TRIGGER_OBJECT;
            triggerFixture = body.createFixture(fixtureDef);
            triggerFixture.setUserData(this);
        }

        shape.dispose();
    }
}
