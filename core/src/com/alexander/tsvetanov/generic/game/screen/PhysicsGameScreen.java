package com.alexander.tsvetanov.generic.game.screen;


//public class PhysicsGameScreen implements Screen {
//
//    private GenericGame genericGame;
//    private World world;
//    private Stage stage;
//    private OrthographicCamera camera;
//    private Player ball;
//    private Tile wall;
//
//    public PhysicsGameScreen(GenericGame genericGame) {
//        this.genericGame = genericGame;
//        this.world = new World(new Vector2(0f, -9.82f), false);
//        float ratio = (float)(Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
//        this.stage = new Stage(new StretchViewport(GenericGame.WORLD_HEIGHT / ratio, GenericGame.WORLD_HEIGHT));
//        this.camera = (OrthographicCamera)stage.getCamera();
//        this.ball = new Player(genericGame,world,new Texture("redball.png"),3f,18f,1f);
//        this.wall = new Tile(genericGame,world,new Texture("wall.jpg"), -0.5f,1f,6f,1f);
//        this.stage.addActor(ball);
//        this.stage.addActor(wall);
//    }
//
//    @Override
//    public void show() {
//
//    }
//
//    @Override
//    public void render(float delta) {
//        Gdx.gl.glClearColor(39/255f, 39/255f, 39/255f, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        stage.act();
//        camera.update();
//        world.step(delta, 6, 2);
//        stage.draw();
//
//        if(Gdx.input.justTouched()) {
//            ball.jump();
//        }
//    }
//
//    @Override
//    public void resize(int width, int height) {
//
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//    @Override
//    public void resume() {
//
//    }
//
//    @Override
//    public void hide() {
//
//    }
//
//    @Override
//    public void dispose() {
//
//    }
//}
