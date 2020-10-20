package de.nroffler.me.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import de.nroffler.me.box2dstatics.StaticCollidableWorldObject;
import de.nroffler.me.main.BitFilter;
import de.nroffler.me.screens.PlayScreen;

public class MyContactListener implements ContactListener {

    private PlayScreen playScreen;

    public MyContactListener(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getFilterData().categoryBits == BitFilter.PLAYER_OBJECT && fixB.getFilterData().categoryBits == BitFilter.TRIGGER_OBJECT) {
            playScreen.createnewPlattform();
            ((StaticCollidableWorldObject) fixB.getUserData()).setUsed();
        } else if (fixB.getFilterData().categoryBits == BitFilter.PLAYER_OBJECT && fixA.getFilterData().categoryBits == BitFilter.TRIGGER_OBJECT) {
            playScreen.createnewPlattform();
            ((StaticCollidableWorldObject) fixA.getUserData()).setUsed();
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
