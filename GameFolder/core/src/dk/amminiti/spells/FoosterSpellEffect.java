package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import dk.amminiti.entity.EnergyDrink;
import dk.amminiti.entity.Platform;
import dk.amminiti.entity.Player;
import dk.amminiti.helpers.GameInfo;


public class FoosterSpellEffect extends SpellEffect {

    static Texture texture = new Texture("energydrinks/fooster.png");
    static float lifeTime = 5f;
    private Player owner;

    /**
     * An GameObject that always have a textre drawn at the body's position.
     *
     */
    public FoosterSpellEffect(Player player) {
        super(createFixtureDef(), texture, 1f, 1, 2, lifeTime, player, EnergyDrink.EnergyDrinkType.FOOSTER);
        this.owner = player;
        applyMovement();
    }

    //TODO TEMP SHOULD BE CHANGED!
    private static FixtureDef createFixtureDef(){
        PolygonShape shape = new PolygonShape();
        shape.set(new Vector2[]{
                new Vector2(40 * GameInfo.PPM / 2f, 60* GameInfo.PPM / 2f),
                new Vector2(0, 40 * GameInfo.PPM / 2f),
                new Vector2(0, 5 * GameInfo.PPM / 2f),
                new Vector2(5 * GameInfo.PPM / 2f, 0),
        });

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;

        return fixtureDef;
    }

    @Override
    public void applyForce(Player target){
        float power = 1f + (0.5f * level);
        Vector2 force = new Vector2(7.5f, 3f).scl(power).scl(directionWhenCast, 1);
        target.applyHitForce(force);
    }

    private void applyMovement() {
        owner.getBody().applyForce(new Vector2(10*GameInfo.PPM*owner.getLookingDir(), 500), owner.getBodyPos(), true);
        //owner.getBody().setLinearVelocity(new Vector2(speed*GameInfo.PPM*owner.getLookingDir(),2000));
    }
}
