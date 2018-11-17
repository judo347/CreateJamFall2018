package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import dk.amminiti.entity.EnergyDrink;
import dk.amminiti.entity.Player;
import dk.amminiti.helpers.GameInfo;


public class WonsterSpellEffect extends SpellEffect {

    static Texture texture = new Texture("energydrinks/wonster.png");
    static float lifeTime = 3f;
    static float speed = 200;


    public WonsterSpellEffect(Player player) {
        super(createFixtureDef(), texture, 1f, 1, 2, lifeTime, player, EnergyDrink.EnergyDrinkType.WONSTER);

        this.body.applyForce(new Vector2(player.getLookingDir()*speed* GameInfo.PPM,0),body.getPosition(),true);
        body.setType(BodyDef.BodyType.DynamicBody);
        body.setGravityScale(1);
        applyMovement();

    }

    //TODO TEMP SHOULD BE CHANGED!
    private static FixtureDef createFixtureDef() {
        CircleShape shape = new CircleShape();
        shape.setRadius(20*GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = false;

        return fixtureDef;
    }

    public Vector2 calculateForce() {
        float power = 1f * (0.2f * level);
        return new Vector2(7, 2).scl(power).scl(directionWhenCast, 1);
    }

    public void applyForce(Player target){
        target.applyHitForce(calculateForce());
    }


    private void applyMovement() {
        body.setLinearVelocity(new Vector2( speed*GameInfo.PPM*owner.getLookingDir(),0));
    }
}

