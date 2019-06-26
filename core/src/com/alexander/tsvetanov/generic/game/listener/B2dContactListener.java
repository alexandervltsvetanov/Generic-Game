package com.alexander.tsvetanov.generic.game.listener;

import com.alexander.tsvetanov.generic.game.model.Player;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class B2dContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        String classA = contact.getFixtureA().getBody().getUserData().getClass().getName();
        String classB = contact.getFixtureB().getBody().getUserData().getClass().getName();

        if(classA.equals("com.alexander.tsvetanov.generic.game.model.Player") && classB.equals("com.alexander.tsvetanov.generic.game.model.Tile")){
            Player player = (Player)(contact.getFixtureA().getBody().getUserData());
            player.scoreUp();
        }
        else if(classA.equals("com.alexander.tsvetanov.generic.game.model.Tile") && classB.equals("com.alexander.tsvetanov.generic.game.model.Player")){
            Player player = (Player)(contact.getFixtureB().getBody().getUserData());
            player.scoreUp();

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
