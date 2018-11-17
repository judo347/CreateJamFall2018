package dk.amminiti.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import dk.amminiti.helpers.GameInfo;

public class MapBox {

    private World world;
    private int wallThichness = 10;
    private int wallBuffer = 2;

    public MapBox(World world) {
        this.world = world;

        PlatformWithoutTexture left = new PlatformWithoutTexture(world, new Vector2((-GameInfo.MAPSIZE_WIDTH) / 100f - wallBuffer,(GameInfo.MAPSIZE_HEIGHT / 100f)), wallThichness, GameInfo.MAPSIZE_HEIGHT);
        PlatformWithoutTexture right = new PlatformWithoutTexture(world, new Vector2(GameInfo.MAPSIZE_WIDTH / 100f + wallBuffer,(GameInfo.MAPSIZE_HEIGHT / 100f)), wallThichness, GameInfo.MAPSIZE_HEIGHT);
        PlatformWithoutTexture up = new PlatformWithoutTexture(world, new Vector2(0,GameInfo.MAPSIZE_HEIGHT / 100f + wallBuffer * 4), GameInfo.MAPSIZE_WIDTH, wallThichness);
        PlatformWithoutTexture down = new PlatformWithoutTexture(world, new Vector2(0, - wallBuffer), GameInfo.MAPSIZE_WIDTH, wallThichness);
    }
}
