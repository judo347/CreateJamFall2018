package dk.amminiti.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import dk.amminiti.ContactManager;
import dk.amminiti.InputController;
import dk.amminiti.MainGame;
import dk.amminiti.ui.UiManager;
import dk.amminiti.world.GameMap;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {


    private MainGame game;
    private SpriteBatch spriteBatch;
    private OrthographicTargetedCamera camera;
    private UiManager ui;
    private GameMap gameMap;
    private InputController inputs = new InputController();
    private Box2DDebugRenderer debugRenderer;
    private World world;
    private Texture background = new Texture("background.png");
    private final float bgWidth = 2500 / 40f;
    private final float bgHeight = 1500 / 40f;

    public GameScreen(MainGame game) {
        this.game = game;


        //Position of the camera
        this.camera = new OrthographicTargetedCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.update();
        this.debugRenderer = new Box2DDebugRenderer();

        this.world = new World(new Vector2(0, -18f), true);
        this.gameMap = new GameMap(this, inputs);
        world.setContactListener(new ContactManager(world, gameMap)); //TODO CHRIS!
        this.spriteBatch = new SpriteBatch();

        this.ui = new UiManager(this);
        this.ui.setShowTut();

        spriteBatch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputs);
    }

    @Override
    public void render(float delta) {

        if(ui.isShowTut()){
            if(Gdx.input.isKeyJustPressed(Input.Keys.PERIOD) || Gdx.input.isKeyJustPressed(Input.Keys.Q))
                ui.setShowGameUi();
        }

        gameMap.update(delta);

        world.step(delta, 3, 3);
        Gdx.gl.glClearColor(0f, 0.0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(background, -bgWidth / 2f, 7 - bgHeight / 2f, bgWidth, bgHeight);
        gameMap.render(spriteBatch, delta);

        spriteBatch.end();

        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        debugRenderer.render(world, camera.combined);

        ui.render(camera, gameMap.getP1(), gameMap.getP2());
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportHeight = height;
        camera.viewportWidth = width;
        camera.update();
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

    }

    public World getWorld() {
        return world;
    }

    public OrthographicTargetedCamera getCamera() {
        return camera;
    }

    public UiManager getUi() {
        return ui;
    }
}
