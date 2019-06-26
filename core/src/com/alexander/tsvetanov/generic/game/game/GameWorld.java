package com.alexander.tsvetanov.generic.game.game;

import com.alexander.tsvetanov.generic.game.GenericGame;
import com.alexander.tsvetanov.generic.game.assets.Assets;
import com.alexander.tsvetanov.generic.game.listener.B2dContactListener;
import com.alexander.tsvetanov.generic.game.model.Player;
import com.alexander.tsvetanov.generic.game.model.TileFloor;
import com.alexander.tsvetanov.generic.game.screen.MenuScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {

    private GenericGame genericGame;
    private World physicsWorld;
    private Player player;
    private Stage stage;
    private List<TileFloor> tileFloors;
    private float worldWidth;

    public GameWorld(GenericGame genericGame){
        this.genericGame = genericGame;
        this.physicsWorld = new World(new Vector2(0,-9.8f),false);
        this.physicsWorld.setContactListener(new B2dContactListener());
        this.player = new Player(genericGame,physicsWorld,genericGame.assets.manager.get(Assets.player, Texture.class),0.25f,GenericGame.WORLD_HEIGHT/2,1);
        float ratio = (float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
        this.worldWidth = GenericGame.WORLD_HEIGHT / ratio;
        this.stage = new Stage(new StretchViewport(worldWidth,GenericGame.WORLD_HEIGHT));

        this.stage.addActor(player);

        this.initWalls();

        //this.batch = new SpriteBatch();
        //this.debugRenderer = new Box2DDebugRenderer();

    }

    public void render(){
        this.stage.draw();
        physicsWorld.step(Gdx.graphics.getDeltaTime(),6,2);

        /*this.batch.begin();
        this.debugRenderer.render(physicsWorld,stage.getCamera().combined);
        this.batch.end();*/
    }

    public Player getPlayer() {
        return player;
    }

    public void update(){
        this.stage.act();

        if(Gdx.input.justTouched()){
            this.player.jump();
        }

        this.regenerateWall();
        this.endGame();

        if(genericGame.gameState == GenericGame.GAME_STATE.MENU){
            if(genericGame.highscore < player.getScore()){
                genericGame.highscore = player.getScore();
                genericGame.updateHighscore(player.getScore());
            }
            genericGame.setScreen(new MenuScreen(genericGame));
        }
    }

    private void initWalls(){
        tileFloors = new ArrayList<TileFloor>(8);
        TileFloor first = new TileFloor(genericGame,physicsWorld,stage,0);
        tileFloors.add(first);
        for(int i = 1; i < 8; i++){
            TileFloor tileFloor = new TileFloor(genericGame,physicsWorld,stage,tileFloors.get(i -1).getX() + tileFloors.get(i-1).getWidth());
            tileFloors.add(tileFloor);
        }
    }

    private void regenerateWall(){
        if(tileFloors.get(0).getX() < stage.getCamera().position.y - worldWidth / 2){
            tileFloors.remove(0);
        }
        if(tileFloors.size() == 7){
            TileFloor tileFloor = new TileFloor(genericGame,physicsWorld,stage,tileFloors.get(tileFloors.size() -1).getX() + tileFloors.get(tileFloors.size() -1).getWidth());
            tileFloors.add(tileFloor);
        }
    }

    private void endGame() {
        if(player.getY() < tileFloors.get(0).getY()) {
            player.die();
        }
    }
}
