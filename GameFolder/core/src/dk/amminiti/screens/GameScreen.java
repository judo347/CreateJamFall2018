package dk.amminiti.screens;

import com.badlogic.gdx.Gdx;
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
import dk.amminiti.entity.EnergyDrink;
import dk.amminiti.entity.Player;
import dk.amminiti.entity.TextureObject;
import dk.amminiti.helpers.GameInfo;
import dk.amminiti.world.GameMap;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {

    Texture img;

    private MainGame game;
    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;
    private Stage stage;
    private GameMap gameMap;
    private InputController inputs;
    private Box2DDebugRenderer debugRenderer;
    private World world;
    private Player p1;
    private EnergyDrink wonster;
    private EnergyDrink fooster;
    private EnergyDrink fire;
    private EnergyDrink redcow;
    private List<TextureObject> gameObjectsListforRender; //TODO GET A REAL NAME


    public GameScreen(MainGame game) {
        this.game = game;

        img = new Texture("badlogic.jpg");

        this.world = new World(new Vector2(0, -18f), true);
        this.gameMap = new GameMap(this);
        world.setContactListener(new ContactManager(world, gameMap)); //TODO CHRIS!
        this.spriteBatch = new SpriteBatch();

        //Position of the camera
        this.camera = new OrthographicCamera(GameInfo.SCREEN_WIDTH, GameInfo.SCREEN_HEIGHT);
        this.camera.zoom = GameInfo.ZOOM;
        //camera.position.y = 500;
        camera.position.y = camera.position.y + 8;
        this.camera.update();
        this.debugRenderer = new Box2DDebugRenderer();
        gameObjectsListforRender = new ArrayList<TextureObject>();
        p1 = new Player(this.world, new Vector2(0, 2));
        wonster = new EnergyDrink(this.world, new Vector2(0,3), EnergyDrink.energyDrinkType.Wonster);
        fire = new EnergyDrink(this.world, new Vector2(0,4), EnergyDrink.energyDrinkType.Fire);
        fooster = new EnergyDrink(this.world, new Vector2(0,5), EnergyDrink.energyDrinkType.Fooster);
        redcow = new EnergyDrink(this.world, new Vector2(0,6), EnergyDrink.energyDrinkType.Redcow);

        gameObjectsListforRender.add(p1);
        gameObjectsListforRender.add(wonster);
        gameObjectsListforRender.add(fire);
        gameObjectsListforRender.add(fooster);
        gameObjectsListforRender.add(redcow);





        //TODO Stage MAYBE
        spriteBatch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputs);
    }

    @Override
    public void render(float delta) {
        camera.update();

        world.step(delta, 3, 3);
        Gdx.gl.glClearColor(0f, 0.0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
//        p1.render(spriteBatch, delta);

        for (TextureObject to:gameObjectsListforRender) {
            to.render(spriteBatch,delta);
        }


        gameMap.render(spriteBatch, delta); //TODO
        //TODO STAGE THINGS
        spriteBatch.end();
        debugRenderer.render(world, camera.combined);
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

    }

    public World getWorld() {
        return world;
    }
}
