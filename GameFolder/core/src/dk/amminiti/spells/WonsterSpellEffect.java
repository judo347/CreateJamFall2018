package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import dk.amminiti.entity.Player;
import dk.amminiti.entity.TextureObject;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.KinematicBody;

public class WonsterSpellEffect extends TextureObject {

    static Texture cultTexture = new Texture("cult effect.png");

    /**
     * An GameObject that always have a textre drawn at the body's position.
     *
     */
    public WonsterSpellEffect(Player player) {
        super(player.getBody().getWorld(), player.getBodyPos(), createBodyDef() ,createSensorFixtureDef(cultTexture), new TextureRegion(cultTexture));
    }


    private static BodyDef createBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = KinematicBody;
        return bodyDef;
    }




}
