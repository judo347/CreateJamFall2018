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

    static Texture textureLeft = new Texture("fire_effekt_left.png");
    static Texture textureRight = new Texture("fire_effekt_right.png");
    static float lifeTime = 6f;

    public FireSpellEffect(Player player) {
        super(createFixtureDef(), getTexture(player), 1f, 3, 0.1f, lifeTime, player, EnergyDrink.EnergyDrinkType.FIRE);
        applyMovement();
        loop = true;
    }

    private static Texture getTexture(Player player){
        return (player.getLookingDir() < 0) ? textureLeft : textureRight;
    }

    @Override
    public void applyForce(Player target){
        float power = 1f + (0.5f * level);
        Vector2 force = new Vector2(10f, 4.2f).scl(power).scl(directionWhenCast, 1);
        target.applyHitForce(force);
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
        body.setLinearVelocity(new Vector2( 300*GameInfo.PPM*owner.getLookingDir(),0));
    }

}
