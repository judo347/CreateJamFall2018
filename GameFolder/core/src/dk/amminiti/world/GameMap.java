package dk.amminiti.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import dk.amminiti.entity.GameObject;
import dk.amminiti.entity.GameObjectOld;
import dk.amminiti.helpers.GameInfo;
import dk.amminiti.screens.GameScreen;

public class GameMap {

    private GameObject groundBox;
    private World world;

    public GameMap(GameScreen screen) {
        this.world = screen.getWorld();

        initializePlatforms();
    }

    private void initializePlatforms(){

        this.groundBox = getPlatform();
    }

    private void update(float delta){
    }

    public void render(SpriteBatch batch, float delta){

        update(delta);

        groundBox.render(batch, delta);

        //batch.draw();

    }

    private GameObject getPlatform(){
        Texture texture = new Texture(Gdx.files.internal("platformTemp.png"));
        //return new TextureObject(world, new Vector2(0,0), GameObjectOld.DEFAULT_STATIC_BODYDEF, createPlatformFixtureDef(), new TextureRegion(new Texture(Gdx.files.internal("platformTemp.png"))));
        return new GameObject(world, new Vector2(0,-texture.getHeight()), new TextureRegion(texture), BodyDef.BodyType.StaticBody);
    }

    private Body createPlatform(World world, Vector2 pos, int width, int height){

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.fixedRotation = true;
        bodyDef.position.set(((pos.x + width / 2) / GameInfo.PPM), (pos.y + height / 2) / GameInfo.PPM);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((width/2) / GameInfo.PPM, (height/2) / GameInfo.PPM);

        FixtureDef fixtureDef = getPlatformFixtureDef();

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData("platform"); //TODO maybe change this

        shape.dispose();

        return body;
    }

    private FixtureDef getPlatformFixtureDef(){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 0.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;
        fixtureDef.friction = 1;
        fixtureDef.restitution = 0;

        return fixtureDef;
    }

    /** The default fixturedef for players */
    private FixtureDef createPlatformFixtureDef(){
        float cornerSize = 0.043f;
        float width = 500/2f;
        float widthShort = 500/2f - cornerSize;
        float height = 100/2f;
        float heightShort = 100/2f - cornerSize;
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
        fixtureDef.density = 1f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0;

        return fixtureDef;
    }
}
