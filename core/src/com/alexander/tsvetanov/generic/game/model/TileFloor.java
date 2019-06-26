package com.alexander.tsvetanov.generic.game.model;

import com.alexander.tsvetanov.generic.game.GenericGame;
import com.alexander.tsvetanov.generic.game.assets.Assets;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TileFloor {

    private GenericGame genericGame;
    private World physicsWorld;
    private List<Tile> tilesList;
    private float x;
    private float y;
    private boolean scored;
    private Stage stage;


    public TileFloor(GenericGame genericGame, World physicsWorld, Stage stage, float x){
        this.genericGame = genericGame;
        this.physicsWorld = physicsWorld;
        this.stage = stage;
        this.x = x;
        this.y = 3;
        this.scored = scored;
        initWall();
    }

    private void initWall() {
        tilesList = new ArrayList<Tile>(3);
        Random random = new Random();

        int index = random.nextInt(3);
        for(int i = 0; i < 3; i++){
            if(i != index){
                Tile tile = new Tile(genericGame,physicsWorld,genericGame.assets.manager.get(Assets.tile, Texture.class),
                        x, y, 3,1);
                tilesList.add(tile);
            }
        }

        for(Tile e: tilesList) {
            stage.addActor(e);
        }
    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }

    public float getHeight() {
        return tilesList.get(0).getHeight();
    }

    public float getWidth() {
        int floorWidth = 0;
        for (Tile e: tilesList){
            floorWidth += e.getWidth();
        }

        return floorWidth;
    }

    public boolean getScored() {
        return scored;
    }

    public void score() {
        scored = true;
    }
}
