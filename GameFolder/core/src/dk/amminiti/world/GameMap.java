package dk.amminiti.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import dk.amminiti.helpers.GameInfo;
import dk.amminiti.screens.GameScreen;

public class GameMap {

    private Body groundBox;
    private World world;

    public GameMap(GameScreen screen) {
        this.world = screen.getWorld();

        initializePlatforms();
    }

    private void initializePlatforms(){

        this.groundBox = createPlatform(world, new Vector2(0,0), 500, 50);
    }

    private void update(float delta){
    }

    public void render(SpriteBatch batch, float delta){

        update(delta);

        //batch.draw();

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
}
