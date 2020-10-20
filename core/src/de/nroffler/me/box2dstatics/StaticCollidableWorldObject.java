package de.nroffler.me.box2dstatics;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import de.nroffler.me.main.BitFilter;

public abstract class StaticCollidableWorldObject {

    protected float x;
    protected float y;
    protected float width;
    protected float height;

    protected World world;
    protected Body body;

    protected Fixture triggerFixture;

    public StaticCollidableWorldObject(World world, float x, float y){
        this.x = x;
        this.y = y;

        this.world = world;

        defineObject();
    }

    public abstract void defineObject();
    public void setUsed(){
        Filter filter = new Filter();
        filter.categoryBits = BitFilter.NON_COLLIDE_OBJECCT;
        triggerFixture.setFilterData(filter);
    }

    public float getY(){return y;}
    public float getX(){return x;}

    public void destroy() {
        world.destroyBody(body);
    }

}
