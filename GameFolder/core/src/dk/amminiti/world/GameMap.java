package dk.amminiti.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import dk.amminiti.InputController;
import dk.amminiti.entity.EnergyDrink;
import dk.amminiti.entity.Platform;
import dk.amminiti.entity.Player;
import dk.amminiti.entity.TextureObject;
import dk.amminiti.screens.GameScreen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GameMap {

    private World world;
    private InputController inputs;

    private List<TextureObject> gameObjects;

    private ArrayList<TextureObject> itemsToBeRemoved;
    private ArrayList<TextureObject> itemsToBeAdded;

    private Platform groundBox;
    private Platform levelOneLeft;
    private Platform levelOneRight;
    private Platform levelTwoMiddle;
    private ArrayList<Platform> platforms;

    private Player p1, p2;
    private DrinkSpawner drinkSpawner;

    public GameMap(GameScreen screen, InputController inputs) {
        this.world = screen.getWorld();
        this.inputs = inputs;
        this.gameObjects = new ArrayList<TextureObject>();
        this.itemsToBeRemoved = new ArrayList<TextureObject>();
        this.itemsToBeAdded = new ArrayList<TextureObject>();

        initializePlatforms();
        initializePlayers();
        drinkSpawner = new DrinkSpawner(this);
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
        this.p1 = new Player(this, new Vector2(0, 2),inputs.getPlayerInput(0));
        this.p2 = new Player(this,new Vector2(0,6),inputs.getPlayerInput(1));

        gameObjects.addAll(Arrays.asList(p1, p2));
    }

    public void update(float delta){
        removeProcess();
        drinkSpawner.update(delta);
        addProcess();
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

    private void addProcess(){

        while(itemsToBeAdded.size() != 0){
            for (TextureObject textureObject : new ArrayList<TextureObject>(itemsToBeAdded)) {


                gameObjects.add(textureObject);
                itemsToBeAdded.remove(textureObject);
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
    public void addToWorldQueue(TextureObject to){
        this.itemsToBeAdded.add(to);
    }

}
