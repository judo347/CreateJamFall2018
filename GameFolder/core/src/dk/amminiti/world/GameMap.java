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
        wonster = new EnergyDrink(this.world, new Vector2(0,3), EnergyDrink.energyDrinkType.Wonster);
        fire = new EnergyDrink(this.world, new Vector2(0,4), EnergyDrink.energyDrinkType.Fire);
        fooster = new EnergyDrink(this.world, new Vector2(0,5), EnergyDrink.energyDrinkType.Fooster);
        redcow = new EnergyDrink(this.world, new Vector2(0,6), EnergyDrink.energyDrinkType.Redcow);

        gameObjects.addAll(Arrays.asList(wonster, fire, fooster, redcow));
    }

    public void render(SpriteBatch batch, float delta){
        for (Platform platform : platforms) {
            platform.render(batch, delta);
        }

        for (TextureObject gameObject : gameObjects) {
            gameObject.render(batch, delta);
        }
    }
}
