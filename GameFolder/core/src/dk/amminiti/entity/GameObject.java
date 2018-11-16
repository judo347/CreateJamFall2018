package dk.amminiti.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import dk.amminiti.helpers.GameInfo;

public class GameObject {

    protected TextureRegion textureRegion;
    protected final Body body;
    protected final World world;

    public GameObject(World world, Vector2 pos, TextureRegion textureRegion, BodyDef.BodyType bodyType) {
        this.textureRegion = textureRegion;
        this.world = world;
        this.body = createBody(world, pos, bodyType);

    }

    /** Creates the body and fixture for an entity. */
    public Body createBody(World world, Vector2 pos, BodyDef.BodyType bodyType){

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;

        bodyDef.position.set(((pos.x + textureRegion.getRegionWidth() / 2f) / GameInfo.PPM), (pos.y + textureRegion.getRegionHeight() / 2f) / GameInfo.PPM);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((textureRegion.getRegionWidth() / 2f) / GameInfo.PPM, (textureRegion.getRegionHeight() / 2f) / GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this); //TODO

        shape.dispose();

        return body;
    }

    public void render(SpriteBatch batch, float delta) {
        if (textureRegion != null) {
            Vector2 pos = body.getPosition();
            float width = textureRegion.getRegionWidth();// * GameInfo.PPM;
            float height = textureRegion.getRegionHeight();// * GameInfo.PPM;
            //batch.draw(textureRegion, pos.x - width / 2, pos.y - height / 2, width / 2f, height / 2f, width, height, 1, 1, body.getAngle());
            batch.draw(textureRegion, pos.x - width / 2, pos.y - height / 2, width / 2f, height / 2f, width, height, 1, 1, body.getAngle());
        }
    }

    public Body getBody() {
        return body;
    }
}
