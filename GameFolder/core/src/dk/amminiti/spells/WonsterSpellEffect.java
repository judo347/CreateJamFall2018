package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import dk.amminiti.entity.EnergyDrink;
import dk.amminiti.entity.Player;
import dk.amminiti.helpers.GameInfo;


public class WonsterSpellEffect extends SpellEffect {

    static Texture texture = new Texture("energydrinks/wonster.png");
    static float lifeTime = 3f;
    static float speed = 2000;
    final float power;


    public WonsterSpellEffect(Player player) {
        super(createFixtureDef(), texture, 1, 2, lifeTime, player, EnergyDrink.EnergyDrinkType.WONSTER);

        this.body.applyForce(new Vector2(player.getLookingDir()*speed* GameInfo.PPM,0),body.getPosition(),true);
        power = owner.getSpellLevel()*basePower;
        applyMovement();

    }

    //TODO TEMP SHOULD BE CHANGED!
    private static FixtureDef createFixtureDef() {
        PolygonShape shape = new PolygonShape();
        shape.set(new Vector2[]{
                new Vector2(40 * GameInfo.PPM / 2f, 60 * GameInfo.PPM / 2f),
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
        return (this.getBody().getLinearVelocity().scl(power));
    }
    public void applyForce(Player target){
        target.getBody().applyForceToCenter(calculateForce(),true);
    }


    private void applyMovement() {
        body.setLinearVelocity(new Vector2( speed*GameInfo.PPM*owner.getLookingDir(),0));
    }
}

