package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import dk.amminiti.entity.EnergyDrink;
import dk.amminiti.entity.Player;
import dk.amminiti.helpers.GameInfo;


public class CultSpellEffect extends SpellEffect {

    static Texture textureLeft = new Texture("spellEffects/cultLeft.png");
    static Texture textureRight = new Texture("spellEffects/cultRight.png");
    static float lifeTime = 3f;
    static float speed = 0;

    public CultSpellEffect(Player player) {
        super(createFixtureDef(player), getTexture(player), 1.2f, 1, 1, lifeTime, player, EnergyDrink.EnergyDrinkType.CULT);

        power = owner.getSpellLevel()*basePower;
        applyMovement();
    }

    private static Texture getTexture(Player player){
        return (player.getLookingDir() < 0) ? textureLeft : textureRight;
    }

    private static FixtureDef createFixtureDef(Player player){

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = (player.getLookingDir() > 0) ? getRightDirectionShape(): getLeftDirectionShape();
        fixtureDef.isSensor = true;

        return fixtureDef;

    }
    public Vector2 calculateForce() {
        return (this.getBody().getLinearVelocity().scl(power));
    }
    public void applyForce(Player target){
        target.getBody().applyForceToCenter(calculateForce(),true);
    }



    private static Shape getRightDirectionShape(){
        PolygonShape shape = new PolygonShape();
        shape.set(new Vector2[]{
                new Vector2(0.5f, .7f), //Up right
                new Vector2(0.5f, -.7f), //down right
                new Vector2(-.5f, -0.5f), //down left
                new Vector2(-.5f, 0.5f), //up left
        });

        return shape;
    }

    private static Shape getLeftDirectionShape(){
        PolygonShape shape = new PolygonShape();
        shape.set(new Vector2[]{
                new Vector2(-0.5f, .7f), //Up right
                new Vector2(-0.5f, -.7f), //down right
                new Vector2(.5f, -0.5f), //down left
                new Vector2(.5f, 0.5f), //up left
        });

        return shape;
    }

    private void applyMovement() {
        body.setLinearVelocity(new Vector2( speed*GameInfo.PPM*owner.getLookingDir(),0));
    }
}
