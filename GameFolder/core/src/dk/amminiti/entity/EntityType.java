package dk.amminiti.entity;

import com.badlogic.gdx.physics.box2d.BodyDef;
import dk.amminiti.helpers.EntityDimensions;


public enum EntityType {

    PLAYER("player", EntityDimensions.PLAYER_DIMENSIONS.getWidth(), EntityDimensions.PLAYER_DIMENSIONS.getHeight(), BodyDef.BodyType.DynamicBody),
    PLATFORM("platform", EntityDimensions.PLATFORM_DIMENSIONS.getWidth(), EntityDimensions.PLATFORM_DIMENSIONS.getHeight(), BodyDef.BodyType.StaticBody);

    private String id;
    private int width, height;
    private BodyDef.BodyType bodyType;

    EntityType(String id, int width, int height, BodyDef.BodyType bodyType) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.bodyType = bodyType;
    }

    public String getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BodyDef.BodyType getBodyType() {
        return bodyType;
    }
}
