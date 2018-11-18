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



public class RedCowSpellEffect extends SpellEffect {

    static float lifeTime = 10f;
    static float speed = 270;
    private static Texture textureRight = new Texture("gustEffectRight.png");
    private static Texture textureLeft = new Texture ( "gustEffectLeft.png");

    private static Sound wingFlabSound = Gdx.audio.newSound(Gdx.files.internal("sound/spellSounds/fire.wav"));


    /**
     * An GameObject that always have a textre drawn at the body's position.
     *
     */
    public RedCowSpellEffect(Player player) {
        super(createFixtureDef(player), getTexture(player), 1f, 1, 2, lifeTime, player, EnergyDrink.EnergyDrinkType.REDCOW);
        applyMovement();
        wingFlabSound.play();
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
        float power = 1f + (0.2f * level);
        return new Vector2(7, 2).scl(power).scl(directionWhenCast, 1);
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


        }





