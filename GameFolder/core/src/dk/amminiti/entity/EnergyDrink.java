package dk.amminiti.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import dk.amminiti.spells.CultSpell;
import dk.amminiti.spells.FireSpell;
import dk.amminiti.spells.RedCowSpell;
import dk.amminiti.spells.Spell;
import dk.amminiti.helpers.GameInfo;
import dk.amminiti.world.GameMap;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.DynamicBody;

public class EnergyDrink extends TextureObject {

    static Texture wonsterTexture = new Texture("energydrinks/wonster.png");
    static Texture foosterTexture = new Texture("energydrinks/fooster.png");
    static Texture redcowTexture = new Texture("energydrinks/redcow.png");
    static Texture fireTexture = new Texture("energydrinks/fire.png");

    public enum EnergyDrinkType {
        WONSTER,
        CULT,
        FOOSTER,
        REDCOW,
        FIRE;

        public static Spell getSpellFromType(EnergyDrinkType type){
            switch (type){
                case CULT: return new CultSpell();
                case FIRE: return new FireSpell();
                case FOOSTER: return null;
                case REDCOW: return new RedCowSpell();
                case WONSTER: return null;
            }

            System.out.println("SHOULD NOT GET HERE! EnergyDrink.EnergyDrinkType.getSpellFromType!");
            return null;
        }
    }

    private EnergyDrinkType type;
    private GameMap map;

    /**
     * An GameObject that always have a textre drawn at the body's position.
     */
    public EnergyDrink(GameMap map, Vector2 pos, EnergyDrinkType type ) {
        super(map.getWorld(), pos, createBodyDef(), createSensorFixtureDef(fireTexture), new TextureRegion(fireTexture));
        this.map = map;
        this.texture = getTexture(type);
        this.type = type;
    }

    public EnergyDrinkType getType(){
        return this.type;
    }

    /** The BodyDef used for something like players */
    private static BodyDef createBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = DynamicBody;
        return bodyDef;
    }

    TextureRegion getTexture(EnergyDrinkType type){
        switch (type){
            case FIRE:
                return new TextureRegion(fireTexture);
            case REDCOW:
                return new TextureRegion(redcowTexture);
            case FOOSTER:
                return new TextureRegion(foosterTexture);
            case WONSTER:
                return new TextureRegion(wonsterTexture);
        }
        return null;
    }
    public void removeFromWorld() {
        map.addToDestroyQueue(this);
    }

}
