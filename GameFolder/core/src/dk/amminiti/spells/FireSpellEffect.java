package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import dk.amminiti.entity.EnergyDrink;
import dk.amminiti.entity.Player;
import dk.amminiti.helpers.GameInfo;


public class FireSpellEffect extends SpellEffect {

    static Texture texture = new Texture("energydrinks/fire.png");
    static float lifeTime = 6f;
    static float speed = 2000;


    /**
     * An GameObject that always have a textre drawn at the body's position.
     *
     */
    public FireSpellEffect(Player player) {
        super(createFixtureDef(), texture, 1f, 1, 2, lifeTime, player, EnergyDrink.EnergyDrinkType.FIRE);
        power = owner.getSpellLevel()*basePower;

        applyMovement();
    }

    public Vector2 calculateForce() {
        return (this.getBody().getLinearVelocity().scl(power));
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

    public void applyForce(Player target){
        target.getBody().applyForceToCenter(calculateForce(),true);
    }


    private void applyMovement() {
        body.setLinearVelocity(new Vector2( speed*GameInfo.PPM*owner.getLookingDir(),0));
    }

}
