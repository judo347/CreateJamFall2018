package dk.amminiti.spells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import dk.amminiti.entity.EnergyDrink;
import dk.amminiti.entity.Player;
import dk.amminiti.helpers.GameInfo;


public class CultSpellEffect extends SpellEffect {

    private static Texture textureLeft = new Texture("spellEffects/cultLeft.png");
    private static Texture textureRight = new Texture("spellEffects/cultRight.png");
    private static int FRAME_COUNT = 4;

    private static Sound screamSound = Gdx.audio.newSound(Gdx.files.internal("sound/spellSounds/cultscream.wav"));

    private static float lifeTime = 0.4f;
    private static float speed = 0.8f;


    public CultSpellEffect(Player player) {
        super(createFixtureDef(player), getTexture(player), 1.2f, FRAME_COUNT, lifeTime/FRAME_COUNT, lifeTime, player, EnergyDrink.EnergyDrinkType.CULT);
        applyMovement();
        screamSound.play();
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

    @Override
    public void applyForce(Player target){
        float power = 0.9f + (0.5f * level);
        Vector2 force = new Vector2(6f, 3f).scl(power).scl(directionWhenCast, 1);
        target.applyHitForce(force);
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
        body.setLinearVelocity(new Vector2( speed * owner.getLookingDir(),0));
    }
}
