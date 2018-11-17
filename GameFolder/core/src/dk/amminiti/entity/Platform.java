package dk.amminiti.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import dk.amminiti.helpers.GameInfo;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.DynamicBody;

public class Platform extends TextureObject{

    static Texture platformTexTemp = new Texture("platformTemp.png");

    public Platform(World world, Vector2 pos) {
        super(world, pos, createBodyDef(), createFixtureDef(), new TextureRegion(platformTexTemp));
    }

    private static FixtureDef createFixtureDef() {
        float cornerSize = 0.043f;
        float width = platformTexTemp.getWidth()* GameInfo.PPM / 2f;
        float widthShort = platformTexTemp.getWidth()* GameInfo.PPM / 2f - cornerSize;
        float height = platformTexTemp.getHeight()* GameInfo.PPM / 2f;
        float heightShort = platformTexTemp.getHeight()* GameInfo.PPM / 2f - cornerSize;
        PolygonShape shape = new PolygonShape();
        shape.set(new Vector2[]{
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

    /** The BodyDef used for something like players */
    private static BodyDef createBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.StaticBody;
        return bodyDef;
    }
}
