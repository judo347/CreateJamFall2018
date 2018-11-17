package dk.amminiti.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import dk.amminiti.helpers.GameInfo;

public class MapBox {

    private World world;
    private int wallThichness = 90;
    private int wallBuffer = 5;

    public MapBox(World world) {
        this.world = world;
        PlatformWithoutTexture down = new PlatformWithoutTexture(world, new Vector2(0, - wallBuffer), GameInfo.MAPSIZE_WIDTH, wallThichness);
    }
}
