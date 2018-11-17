package dk.amminiti.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import dk.amminiti.InputController;
import dk.amminiti.entity.*;
import dk.amminiti.screens.GameScreen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GameMap {

    private World world;
    private InputController inputs;
    private MapBox mapBox;

    private List<TextureObject> gameObjects;

    private ArrayList<TextureObject> itemsToBeRemoved;

    private Platform groundBox;
    private Platform levelOneLeft;
    private Platform levelOneRight;
    private Platform levelTwoMiddle;
    private ArrayList<Platform> platforms;

    private Player p1, p2;
    private EnergyDrink wonster;
    private EnergyDrink fooster;
    private EnergyDrink fire;
    private EnergyDrink redcow;

    public GameMap(GameScreen screen, InputController inputs) {
        this.world = screen.getWorld();
        this.inputs = inputs;
        this.gameObjects = new ArrayList<TextureObject>();
        this.itemsToBeRemoved = new ArrayList<TextureObject>();
        this.mapBox = new MapBox(world);

        initializePlatforms();
        initializePlayers();
        initializeEnergyDrink();
    }

    private void initializePlatforms(){

        this.groundBox = new Platform(world, new Vector2(0,0), Platform.PlatformSizeType.LONG);
        this.levelOneLeft = new Platform(world, new Vector2(-6, 4), Platform.PlatformSizeType.SMALL);
        this.levelOneRight = new Platform(world, new Vector2(6, 4), Platform.PlatformSizeType.SMALL);
        this.levelTwoMiddle = new Platform(world, new Vector2(0, 7), Platform.PlatformSizeType.SMALL);

        this.platforms = new ArrayList<Platform>();
        platforms.addAll(Arrays.asList(groundBox, levelOneLeft, levelOneRight, levelTwoMiddle));
    }

    private void initializePlayers(){
        this.p1 = new Player(this.world, new Vector2(0, 2),inputs.getPlayerInput(0));
        this.p2 = new Player(this.world,new Vector2(0,6),inputs.getPlayerInput(1));

        gameObjects.addAll(Arrays.asList(p1, p2));
    }

    private void initializeEnergyDrink(){
        wonster = new EnergyDrink(this, new Vector2(1,3), EnergyDrink.EnergyDrinkType.WONSTER);
        fire = new EnergyDrink(this, new Vector2(-1,4), EnergyDrink.EnergyDrinkType.FIRE);
        fooster = new EnergyDrink(this, new Vector2(2,5), EnergyDrink.EnergyDrinkType.FOOSTER);
        redcow = new EnergyDrink(this, new Vector2(-2,6), EnergyDrink.EnergyDrinkType.REDCOW);

        gameObjects.addAll(Arrays.asList(wonster, fire, fooster, redcow));
    }

    public void update(float delta){
        removeProcess();
    }

    private void removeProcess(){

        while(itemsToBeRemoved.size() != 0){
            for (TextureObject textureObject : new ArrayList<TextureObject>(itemsToBeRemoved)) {

                textureObject.destroyBody();
                itemsToBeRemoved.remove(textureObject);
                gameObjects.remove(textureObject);
            }
        }
    }

    public void render(SpriteBatch batch, float delta){
        for (Platform platform : platforms) {
            platform.render(batch, delta);
        }

        for (TextureObject gameObject : gameObjects) {
            gameObject.render(batch, delta);
        }
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public World getWorld(){
        return this.world;
    }

    public void addToDestroyQueue(TextureObject to){
        this.itemsToBeRemoved.add(to);
    }
}
