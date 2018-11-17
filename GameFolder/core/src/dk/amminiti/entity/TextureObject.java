package dk.amminiti.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import dk.amminiti.helpers.GameInfo;

public class TextureObject extends RenderObject {

    protected TextureRegion texture;

    /** An GameObject that always have a textre drawn at the body's position. */
    public TextureObject(World world, Vector2 pos, BodyDef bodyDef, FixtureDef fixtureDef, TextureRegion texture) {
        super(world, pos, bodyDef, fixtureDef);
        this.texture = texture;
    }

    /** Render the TextureObjects texture at the body's position. */
    @Override
    public void render(SpriteBatch batch, float delta) {
        if (texture != null) {
            Vector2 pos = body.getPosition();
            float width = texture.getRegionWidth() * GameInfo.PPM;
            float height = texture.getRegionHeight() * GameInfo.PPM;
            batch.draw(texture, pos.x - width / 2, pos.y - height / 2, width / 2f, height / 2f, width, height, 1, 1, body.getAngle());
        }
    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }

    public static FixtureDef createTextureFixtureDef(Texture text){
        float cornerSize = 0.043f;
        float width = text.getWidth()* GameInfo.PPM / 2f;
        float widthShort = text.getWidth()* GameInfo.PPM / 2f - cornerSize;
        float height = text.getHeight()* GameInfo.PPM / 2f;
        float heightShort = text.getHeight()* GameInfo.PPM / 2f - cornerSize;
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
