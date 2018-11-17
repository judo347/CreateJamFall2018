package dk.amminiti.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import dk.amminiti.helpers.GameInfo;
import dk.amminiti.world.GameMap;

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

    private energyDrinkType type;
    private GameMap map;

    /**
     * An GameObject that always have a textre drawn at the body's position.
     */

    public EnergyDrink(GameMap map, Vector2 pos, energyDrinkType type ) {
        super(map.getWorld(),pos,createBodyDef(),createSensorFixtureDef(fireTexture),new TextureRegion(fireTexture));
        this.map = map;
        this.texture = getTexture(type);
        this.type = type;

    }

    public energyDrinkType getType(){
        return this.type;
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
    public void removeFromWorld() {
        map.addToDestroyQueue(this);
    }

}
