package dk.amminiti.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import dk.amminiti.helpers.GameInfo;

public class Platform extends TextureObject{

    static Texture platformTexLong = new Texture("platformLong.png");
    static Texture platformTexMedium = new Texture("platformMedium.png");
    static Texture platformTexSmall = new Texture("platformSmall.png");

    public enum PlatformSizeType {

        LONG(platformTexLong), MEDIUM(platformTexMedium), SMALL(platformTexSmall);

        private Texture texture;

        PlatformSizeType(Texture texture) {
            this.texture = texture;
        }

        public Texture getTexture() {
            return texture;
        }
    }
    
    private PlatformSizeType type;

    public Platform(World world, Vector2 pos, PlatformSizeType type) {
        super(world, pos, createBodyDef(), TextureObject.createTextureFixtureDef(type.getTexture()), new TextureRegion(type.getTexture()));
        this.type = type;
    }

    @Override
    public void render(SpriteBatch batch, float delta) {

    }

    /** The BodyDef used for something like players */
    private static BodyDef createBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.StaticBody;
        return bodyDef;
    }
}
