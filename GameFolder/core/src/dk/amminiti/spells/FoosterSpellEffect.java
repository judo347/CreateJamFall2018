package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import dk.amminiti.entity.EnergyDrink;
import dk.amminiti.entity.Player;
import dk.amminiti.helpers.GameInfo;

public class FoosterSpellEffect extends SpellEffect {

    private static Texture texture = new Texture("gustEffectLeft.png");

    private static float lifeTime = 0.4f;

    public FoosterSpellEffect(Player owner) {
        super(createFixtureDef(), texture, 0.3f, 1, 1f, lifeTime, owner, EnergyDrink.EnergyDrinkType.FOOSTER);
        Vector2 pvel = owner.getBody().getLinearVelocity();
        pvel.scl(0.3f);
        pvel.add(directionWhenCast * 15f, 2.5f);
        owner.getBody().setLinearVelocity(pvel);

        body.setLinearVelocity(new Vector2(580 * GameInfo.PPM * owner.getLookingDir(), 0));
    }

    @Override
    public void render(SpriteBatch batch, float delta) {
        return;
    }

    private static FixtureDef createFixtureDef() {
        //Circle shape = new Circle(0,0, 1);

        CircleShape shape = new CircleShape();
        shape.setRadius(0.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;

        return fixtureDef;
    }

    @Override
    public void applyForce(Player target) {
        float power = 1f + (0.5f * level);
        Vector2 force = new Vector2(10f, 4.2f).scl(power).scl(directionWhenCast, 1);
        target.applyHitForce(force);
    }
}
