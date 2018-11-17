package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import dk.amminiti.entity.EnergyDrink;
import dk.amminiti.entity.Player;
import dk.amminiti.helpers.GameInfo;


public class FireSpellEffect extends SpellEffect {

    static Texture textureLeft = new Texture("fireEffectLeft.png");
    static Texture textureRight = new Texture("fireEffectRight.png");
    static float lifeTime = 6f;
    static float speed = 200;

    public FireSpellEffect(Player player) {
        super(createFixtureDef(), getTexture(player), 1f, 3, 0.08f, lifeTime, player, EnergyDrink.EnergyDrinkType.FIRE);

        applyMovement();
    }

    private static Texture getTexture(Player player){
        return (player.getLookingDir() < 0) ? textureLeft : textureRight;
    }

    public Vector2 calculateForce() {
        float power = 1f + (0.2f * level);
        return new Vector2(7, 2).scl(power).scl(directionWhenCast, 1);
    }

    public void applyForce(Player target){
        target.applyHitForce(calculateForce());
    }

    private static FixtureDef createFixtureDef(){
        //Circle shape = new Circle(0,0, 1);

        CircleShape shape = new CircleShape();
        shape.setRadius(0.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;

        return fixtureDef;
    }

    private void applyMovement() {
        body.setLinearVelocity(new Vector2( speed*GameInfo.PPM*owner.getLookingDir(),0));
    }

}
