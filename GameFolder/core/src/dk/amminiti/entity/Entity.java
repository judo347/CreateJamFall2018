package dk.amminiti.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import dk.amminiti.helpers.GameInfo;

public abstract class Entity {

    protected World world;
    protected Vector2 pos;
    protected Body body;
    protected EntityType entityType;
    protected Texture texture;

    public Entity(World world, Vector2 pos, EntityType entityType, Texture texture) {
        this.world = world;
        this.pos = pos;
        this.entityType = entityType;
        this.texture = texture;

        if(entityType == EntityType.PLAYER)
            this.body = createPlayerBody(world, pos);
        else
            this.body = createBody(world, pos);
    }

    private Body createPlayerBody(World world, Vector2 pos){

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = entityType.getBodyType();

        bodyDef.fixedRotation = true;


        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((entityType.getWidth() / 2f) / GameInfo.PPM, (entityType.getHeight() / 2f) / GameInfo.PPM);

        FixtureDef fixtureDef = createPlayerFixtureDef();

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(entityType.getId());

        shape.dispose();

        return body;
    }

    private FixtureDef createPlayerFixtureDef(){
        float cornerSize = 0.005f;
        float width = (entityType.getWidth() / 2f) / GameInfo.PPM;
        float height = (entityType.getHeight() / 2f) / GameInfo.PPM;
        float heightShort = ((entityType.getHeight() / 2f) / GameInfo.PPM) - cornerSize;
        PolygonShape shape = new PolygonShape();
        shape.set(new Vector2[] {
                new Vector2(-width, height),
                new Vector2(width, height),
                new Vector2(width, -heightShort),
                new Vector2(0, -height),
                new Vector2(-width, -heightShort),
        });

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;
        fixtureDef.friction = 1;
        fixtureDef.restitution = 0.0f;

        return fixtureDef;
    }

    /** Creates the body and fixture for an entity. */
    public Body createBody(World world, Vector2 pos){

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = entityType.getBodyType();

        if(entityType == EntityType.PLAYER){
            bodyDef.fixedRotation = true;
        }
        bodyDef.position.set(((pos.x + entityType.getWidth() / 2)), (pos.y + entityType.getHeight() / 2));

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((entityType.getWidth() / 2), (entityType.getHeight() / 2) );

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(entityType.getId());

        shape.dispose();

        return body;
    }

    public abstract void update(float delta);

    /** The method used to render the entity. */
    public abstract void render (SpriteBatch batch, float delta);

    public abstract void dispose();

    public World getWorld() {
        return world;
    }

    public Body getBody() {
        return body;
    }

    public Vector2 getPos() {
        return pos;
    }

    public float getWidth() {
        return entityType.getWidth();
    }

    public float getHeight() {
        return entityType.getHeight();
    }

    public String getDefaultTypeId() {
        return entityType.getId();
    }

    public void setId(String id) {
        this.body.getFixtureList().get(0).setUserData(id);
    }

    public Texture getTexture() {
        return texture;
    }
}
