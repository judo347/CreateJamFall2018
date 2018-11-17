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

    static Texture texture = new Texture("slimeball.png");
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
        shape.setRadius(24*GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = false;

        return fixtureDef;
    }

    @Override
    public void applyForce(Player target){
        float power = 1f + (0.5f * level);
        Vector2 force = new Vector2(7.5f, 3f).scl(power).scl(directionWhenCast, 1);
        target.applyHitForce(force);
    }

    private void applyMovement() {
        body.setLinearVelocity(new Vector2( speed*GameInfo.PPM*owner.getLookingDir(),0));
    }
}

