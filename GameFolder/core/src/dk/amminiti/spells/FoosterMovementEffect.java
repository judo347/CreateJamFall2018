package dk.amminiti.spells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import dk.amminiti.entity.Player;
import dk.amminiti.entity.TextureObject;
import dk.amminiti.helpers.GameInfo;

public class FoosterMovementEffect extends SpellEffect {
    /**
     * An GameObject that always have a textre drawn at the body's position.
     *
     * @param world
     * @param pos
     * @param bodyDef
     * @param fixtureDef
     * @param texture
     */
    private static Texture  textLeft = new Texture("fart_from_left.png");
    private static Texture  textRight = new Texture("fart_from_right.png");
    private float lifetime = 0.3f;
    private Texture text;
    private Sound fart = Gdx.audio.newSound(Gdx.files.internal("sound/spellSounds/fart_dash.wav"));

    //(FixtureDef fixtureDef, Texture texture, float offsetFromHead, int numberOfFrames, float animationSpeed, float lifeTime, Player owner, EnergyDrink.EnergyDrinkType type)

    public FoosterMovementEffect(Player player) {
        super(createSensorFixtureDef(), getTexture(player), 0.3f,3, 0.1f, 0.3f, player, null);
        fart.play();
    }

    private static Texture getTexture(Player player){
        return (player.getLookingDir() < 0) ? textLeft : textRight;
    }

    private static FixtureDef createSensorFixtureDef(){
        float cornerSize = 0.043f;
        float width = textLeft.getWidth()* GameInfo.PPM / 2f;
        float widthShort = textLeft.getWidth()* GameInfo.PPM / 2f - cornerSize;
        float height = textLeft.getHeight()* GameInfo.PPM / 2f;
        float heightShort = textLeft.getHeight()* GameInfo.PPM / 2f - cornerSize;
        PolygonShape shape = new PolygonShape();
        shape.set(new Vector2[]{
                new Vector2(-width, height),
                new Vector2(width, height),
                new Vector2(width, -heightShort),
                new Vector2(0, -height),
                new Vector2(-width, -heightShort),
        });

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;

        return fixtureDef;
    }



    @Override
    public void applyForce(Player target) {
        return;
    }
}
