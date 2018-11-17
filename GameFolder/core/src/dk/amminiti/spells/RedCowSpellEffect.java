package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import dk.amminiti.entity.EnergyDrink;
import dk.amminiti.entity.Player;
import dk.amminiti.helpers.GameInfo;


public class RedCowSpellEffect extends SpellEffect {

    static Texture texture = new Texture("energydrinks/redcow.png");
    static float lifeTime = 3f;
    static float speed = 2000;
    static Object savedUserData;
    /**
     * An GameObject that always have a textre drawn at the body's position.
     *
     */
    public RedCowSpellEffect(Player player) {
        super(createFixtureDef(), texture, 1f, 1, 2, lifeTime, player, EnergyDrink.EnergyDrinkType.REDCOW);
        savedUserData = owner.getBody().getUserData();
        owner.getBody().setUserData(this);

        applyMovement();
    }

    public void returnUserData(){
        owner.getBody().setUserData(savedUserData);
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

    public Vector2 calculateForce() {
        float power = 1f + (0.2f * level);
        return new Vector2(7, 2).scl(power).scl(directionWhenCast, 1);
    }

    public void applyForce(Player target){
        target.applyHitForce(calculateForce());
    }

    private void applyMovement() {
        body.setLinearVelocity(new Vector2( speed*GameInfo.PPM*owner.getLookingDir(),0));
    }
}





