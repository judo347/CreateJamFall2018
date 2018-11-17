package dk.amminiti.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import dk.amminiti.helpers.GameInfo;

public class PlatformWithoutTexture extends GameObject{
    public PlatformWithoutTexture(World world, Vector2 pos, int width, int height) {
        super(world, pos, createBodyDef(), createFixtureDef(width, height));
    }

    private static BodyDef createBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.StaticBody;
        return bodyDef;
    }

    public static FixtureDef createFixtureDef(int widthIn, int heightIn){
        float cornerSize = 0.043f;
        float width = widthIn * GameInfo.PPM / 2f;
        float widthShort = widthIn* GameInfo.PPM / 2f - cornerSize;
        float height = heightIn* GameInfo.PPM / 2f;
        float heightShort = heightIn* GameInfo.PPM / 2f - cornerSize;
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
}
