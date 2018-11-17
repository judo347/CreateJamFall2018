package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
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
        super(player,  lifeTime, texture, EnergyDrink.EnergyDrinkType.FIRE);
        power = owner.getSpellLevel()*basePower;

        applyMovement();

    }
    public Vector2 calculateForce() {
        return (this.getBody().getLinearVelocity().scl(power));
    }
    public void applyForce(Player target){
        target.getBody().applyForceToCenter(calculateForce(),true);
    }


    private void applyMovement() {
        body.setLinearVelocity(new Vector2( 2000*GameInfo.PPM*owner.getLookingDir(),0));
    }

}
