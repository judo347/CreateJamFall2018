package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import dk.amminiti.entity.EnergyDrink;
import dk.amminiti.entity.Player;
import dk.amminiti.helpers.GameInfo;


public class FoosterSpellEffect extends SpellEffect {

    static Texture texture = new Texture("energydrinks/fooster.png");
    static float lifeTime = 5f;
    static float speed = 2000;
    static float power;

    /**
     * An GameObject that always have a textre drawn at the body's position.
     *
     */
    public FoosterSpellEffect(Player player) {
        super(player,  lifeTime, texture, EnergyDrink.EnergyDrinkType.FOOSTER);
        this.basePower = 4000;
        this.power = player.getSpellLevel()*basePower;
        applyMovement();

    }
    public Vector2 calculateForce() {
        return (this.getBody().getLinearVelocity().scl(power));
    }
    public void applyForce(Player target){
        target.getBody().applyForceToCenter(calculateForce(),true);
    }


    private void applyMovement() {
        body.setLinearVelocity(new Vector2(2000*GameInfo.PPM*owner.getLookingDir(),0));
    }

}
