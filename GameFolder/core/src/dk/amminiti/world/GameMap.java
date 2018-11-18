package dk.amminiti.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import dk.amminiti.InputController;
import dk.amminiti.entity.*;
import dk.amminiti.helpers.GameInfo;
import dk.amminiti.screens.GameScreen;
import dk.amminiti.ui.UiManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GameMap {

    private World world;
    private InputController inputs;
    private MapBox mapBox;
    private GameScreen screen;

    private List<TextureObject> gameObjects;
    private ArrayList<AnimatedObject> animatedObjects;

    private ArrayList<TextureObject> itemsToBeRemoved;
    private ArrayList<TextureObject> itemsToBeAdded;
    private ArrayList<AnimatedObject> aniObjToBeAdded;
    private ArrayList<AnimatedObject> aniObjToBeRemoved;

    private Platform groundBox;
    private Platform levelOneLeft;
    private Platform levelOneRight;
    private Platform levelTwoMiddle;
    private PlatformWithoutTexture levelOneLeftBottom;
    private PlatformWithoutTexture levelTwoMiddleBottom;
    private PlatformWithoutTexture levelOneRightBottom;
    private ArrayList<Platform> platforms;

    private float playerOneDeathTimer;
    private float playerTwoDeathTimer;
    private boolean isPlayerOneDead;
    private boolean isPlayerTwoDead;

    private Player p1, p2;
    private DrinkSpawner drinkSpawner;

    public GameMap(GameScreen screen, InputController inputs) {
        this.screen = screen;
        this.world = screen.getWorld();
        this.inputs = inputs;
        this.gameObjects = new ArrayList<TextureObject>();
        this.animatedObjects = new ArrayList<AnimatedObject>();
        this.itemsToBeRemoved = new ArrayList<TextureObject>();
        this.animatedObjects = new ArrayList<AnimatedObject>();
        this.aniObjToBeRemoved = new ArrayList<AnimatedObject>();
        this.aniObjToBeAdded = new ArrayList<AnimatedObject>();
        this.mapBox = new MapBox(world);
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

        this.levelOneRightBottom = new PlatformWithoutTexture(world, new Vector2(-6, 4), 3,4);
        levelOneRightBottom.getBody().setUserData("Nothing");

        this.levelOneLeftBottom = new PlatformWithoutTexture(world, new Vector2(6, 4), 3,4);
        levelOneLeftBottom.getBody().setUserData("Nothing");

        this.levelTwoMiddleBottom = new PlatformWithoutTexture(world, new Vector2(0, 0), 3,4);
        levelTwoMiddleBottom.getBody().setUserData("Nothing");

        this.platforms = new ArrayList<Platform>();
        platforms.addAll(Arrays.asList(groundBox, levelOneLeft, levelOneRight, levelTwoMiddle));

    }

    private void initializePlayers(){
        initializePlayerOne();
        initializePlayerTwo();
    }

    private void initializePlayerOne(){
        this.p1 = new Player(this, new Vector2(-6, 5),inputs.getPlayerInput(1));
        this.isPlayerOneDead = false;
        this.playerOneDeathTimer = 0;
        this.gameObjects.add(p1);
        this.screen.getCamera().targets.add(p1);
    }

    private void initializePlayerTwo(){
        this.p2 = new Player(this,new Vector2(6,5),inputs.getPlayerInput(0));
        this.isPlayerTwoDead = false;
        this.playerTwoDeathTimer = 0;
        this.gameObjects.add(p2);
        this.screen.getCamera().targets.add(p2);
    }

    public void update(float delta){
        removeProcess();
        removeAniObjProcess();
        drinkSpawner.update(delta);
        addProcess();

        if(isPlayerOneDead)
            playerOneDeathTimer += delta;
        if(isPlayerTwoDead)
            playerTwoDeathTimer += delta;

        if(playerOneDeathTimer > GameInfo.RESPAWN_COOLDOWN)
            initializePlayerOne();

        if(playerTwoDeathTimer > GameInfo.RESPAWN_COOLDOWN)
            initializePlayerTwo();
    }

    private void removeProcess(){

        while(itemsToBeRemoved.size() != 0){
            for (TextureObject textureObject : new ArrayList<TextureObject>(itemsToBeRemoved)) {
                if(textureObject instanceof Player)
                    ((Player)textureObject).destroyFeet();

                textureObject.destroyBody();
                itemsToBeRemoved.remove(textureObject);
                gameObjects.remove(textureObject);
            }
        }
    }

    private void removeAniObjProcess(){
        while(aniObjToBeRemoved.size() != 0){
            for (AnimatedObject animatedObject : new ArrayList<AnimatedObject>(aniObjToBeRemoved)) {
                world.destroyBody(animatedObject.getBody());
                aniObjToBeRemoved.remove(animatedObject);
                animatedObjects.remove(animatedObject);

            }
        }
    }

    private void addProcess(){
        while(itemsToBeAdded.size() != 0){
            for (TextureObject textureObject : new ArrayList<TextureObject>(itemsToBeAdded)) {
                if (!gameObjects.contains(textureObject)) {
                    gameObjects.add(textureObject);
                }
                itemsToBeAdded.remove(textureObject);
            }
        }

        while(aniObjToBeAdded.size() != 0){
            for(AnimatedObject aniObj : new ArrayList<AnimatedObject>(aniObjToBeAdded)){
                if (!animatedObjects.contains(aniObj)){
                    animatedObjects.add(aniObj);
                }
                aniObjToBeAdded.remove(aniObj);
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

        for (AnimatedObject animatedObject : animatedObjects) {
            animatedObject.render(batch, delta);
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
        if (!itemsToBeRemoved.contains(to)) {
            this.itemsToBeRemoved.add(to);
        }
    }

    public void addToDestroyQueue(AnimatedObject aniObj){
        if (!aniObjToBeRemoved.contains(aniObj)){
        this.aniObjToBeRemoved.add(aniObj);}
    }
    public void addToWorldQueue(TextureObject to){
        if (!itemsToBeAdded.contains(to)) {
            this.itemsToBeAdded.add(to);
        }
    }
    public void addToWorldQueue(AnimatedObject aniObj){
        if (!aniObjToBeRemoved.contains(aniObj)) {
            this.aniObjToBeAdded.add(aniObj);
        }
    }

    public void killPlayer(Player player){
        player.killPlayer();
        this.screen.getCamera().targets.remove(player);
        if (!itemsToBeRemoved.contains(player)) {
            this.itemsToBeRemoved.add(player);
        }

        if(player == p1)
            isPlayerOneDead = true;
        else
            isPlayerTwoDead = true;

    }
}
