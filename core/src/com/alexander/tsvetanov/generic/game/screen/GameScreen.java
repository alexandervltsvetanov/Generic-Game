package com.alexander.tsvetanov.generic.game.screen;

import com.alexander.tsvetanov.generic.game.GenericGame;
import com.alexander.tsvetanov.generic.game.assets.Assets;
import com.alexander.tsvetanov.generic.game.game.GameWorld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;


public class GameScreen implements Screen {

    private GenericGame genericGame;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private GameWorld gameWorld;
    private Texture background;
    private BitmapFont font;


    public GameScreen(GenericGame genericGame){
        this.genericGame = genericGame;
    }
    @Override
    public void show() {

        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false,GenericGame.WIDTH,GenericGame.HEIGHT);
        this.gameWorld = new GameWorld(this.genericGame);
        this.background = genericGame.assets.manager.get(Assets.background, Texture.class);
        this.font = new BitmapFont();

        this.font.getData().scale(8);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(55/255f, 51/255f, 102/255f, 1); // 	0, 51, 102
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background,0,0);
        batch.end();
        gameWorld.render();
        batch.begin();
        drawScore(gameWorld.getPlayer().getScore(),batch);
        batch.end();

        gameWorld.update();
        camera.update();
    }

    private void drawScore(int score,SpriteBatch batch){

        GlyphLayout glyphLayout = new GlyphLayout();
        String item = "Score: " + score;
        glyphLayout.setText(font,item);
        float w = glyphLayout.width;
        font.draw(batch, glyphLayout, camera.position.x - w/2, GenericGame.HEIGHT - 50);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
