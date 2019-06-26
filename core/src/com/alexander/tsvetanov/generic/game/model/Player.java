package com.alexander.tsvetanov.generic.game.model;


import com.alexander.tsvetanov.generic.game.GenericGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Player extends Image {

    private GenericGame genericGame;
    private World physicsWorld;
    private Body body;
    private int score;

    public Player(GenericGame genericGame, World physicsWorld, Texture appearance, float x, float y, float diameter) {
        super(appearance);
        this.setX(x);
        this.setY(y);
        setOrigin(diameter,diameter);
        setWidth(diameter);
        setHeight(diameter);
        this.genericGame = genericGame;
        this.physicsWorld = physicsWorld;

        this.score = 0;

        this.initBody();
    }

    private void initBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = physicsWorld.createBody(bodyDef);

        CircleShape bodyShape = new CircleShape();
        bodyShape.setRadius(getWidth() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = bodyShape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f; // 0 - 1f

        body.createFixture(fixtureDef);
        body.setUserData(this);
        //body.setLinearVelocity(5f,0);

        bodyShape.dispose();
    }

    public void jump() {
        body.setLinearVelocity(2f,0);
        body.applyForceToCenter(0,200f, true);
    }

    public void scoreUp() {
        score++;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void act(float delta) {
        this.setPosition(body.getPosition().x, body.getPosition().y);
        this.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
    }

    public void die() {
        genericGame.gameState = GenericGame.GAME_STATE.MENU;
    }
}
