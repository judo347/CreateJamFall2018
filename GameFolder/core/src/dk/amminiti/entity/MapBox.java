package dk.amminiti.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import dk.amminiti.helpers.GameInfo;

public class MapBox {

    private World world;
    private int wallThichness = 10;

    public MapBox(World world) {
        this.world = world;

        PlatformWithoutTexture left = new PlatformWithoutTexture(world, new Vector2((-GameInfo.MAPSIZE_WIDTH) / 100f,0), wallThichness, GameInfo.MAPSIZE_HEIGHT);
        PlatformWithoutTexture right = new PlatformWithoutTexture(world, new Vector2(GameInfo.MAPSIZE_WIDTH / 100f,0), wallThichness, GameInfo.MAPSIZE_HEIGHT);
        PlatformWithoutTexture up = new PlatformWithoutTexture(world, new Vector2(0,GameInfo.MAPSIZE_HEIGHT / 100f), GameInfo.MAPSIZE_WIDTH, wallThichness);
        PlatformWithoutTexture down = new PlatformWithoutTexture(world, new Vector2(0,- 2), GameInfo.MAPSIZE_WIDTH, wallThichness);
    }
}
