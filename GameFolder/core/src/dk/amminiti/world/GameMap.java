package dk.amminiti.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import dk.amminiti.entity.Platform;
import dk.amminiti.screens.GameScreen;

import java.util.ArrayList;
import java.util.Arrays;


public class GameMap {

    private World world;

    private Platform groundBox;
    private Platform levelOneLeft;
    private Platform levelOneRight;
    private Platform levelTwoMiddle;

    private ArrayList<Platform> platforms;

    public GameMap(GameScreen screen) {
        this.world = screen.getWorld();

        initializePlatforms();
    }

    private void initializePlatforms(){

        this.groundBox = new Platform(world, new Vector2(0,0), Platform.PlatformSizeType.LONG);
        this.levelOneLeft = new Platform(world, new Vector2(-6, 4), Platform.PlatformSizeType.SMALL);
        this.levelOneRight = new Platform(world, new Vector2(6, 4), Platform.PlatformSizeType.SMALL);
        this.levelTwoMiddle = new Platform(world, new Vector2(0, 7), Platform.PlatformSizeType.SMALL);

        this.platforms = new ArrayList<Platform>();
        platforms.addAll(Arrays.asList(groundBox, levelOneLeft, levelOneRight, levelTwoMiddle));
    }

    public void render(SpriteBatch batch, float delta){
        for (Platform platform : platforms) {
            platform.render(batch, delta);
        }
    }
}
