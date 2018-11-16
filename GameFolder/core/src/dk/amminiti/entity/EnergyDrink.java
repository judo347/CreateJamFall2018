package dk.amminiti.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import dk.amminiti.helpers.GameInfo;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.DynamicBody;

public class EnergyDrink extends TextureObject {

    static Texture wonsterTexture = new Texture("energydrinks/wonster.png");
    static Texture foosterTexture = new Texture("energydrinks/fooster.png");
    static Texture redcowTexture = new Texture("energydrinks/redcow.png");
    static Texture fireTexture = new Texture("energydrinks/fire.png");

    public enum energyDrinkType{
        Wonster,
        Fooster,
        Redcow,
        Fire;
    }
    /**
     * An GameObject that always have a textre drawn at the body's position.
     * @param world
     * @param pos
     */
    public EnergyDrink(World world, Vector2 pos,energyDrinkType type ) {
        super(world,pos,createBodyDef(),createFixtureDef(),new TextureRegion(fireTexture));
        this.texture = getTexture(type);

    }
    private static FixtureDef createFixtureDef() {
        float cornerSize = 0.043f;
        float width = fireTexture.getWidth()* GameInfo.PPM / 2f;
        float widthShort = fireTexture.getWidth()* GameInfo.PPM / 2f - cornerSize;
        float height = fireTexture.getHeight()* GameInfo.PPM / 2f;
        float heightShort = fireTexture.getHeight()* GameInfo.PPM / 2f - cornerSize;
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
        bodyDef.type = DynamicBody;
        return bodyDef;
    }
    TextureRegion getTexture(energyDrinkType type){
        switch (type){
            case Fire:
                return new TextureRegion(fireTexture);
            case Redcow:
                return new TextureRegion(redcowTexture);
            case Fooster:
                return new TextureRegion(foosterTexture);
            case Wonster:
                return new TextureRegion(wonsterTexture);
        }
        return null;
    }


}
