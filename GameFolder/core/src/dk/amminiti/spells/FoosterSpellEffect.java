package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import dk.amminiti.entity.Player;
import dk.amminiti.helpers.GameInfo;


public class FoosterSpellEffect extends SpellEffect {

    static Texture texture = new Texture("cult effect.png");
    static float lifeTime = 5f;
    /**
     * An GameObject that always have a textre drawn at the body's position.
     *
     */
    public FoosterSpellEffect(Player player) {
        super(player,  lifeTime, texture);
        this.body.applyForce(new Vector2(2000* GameInfo.PPM,0),body.getPosition(),true);
    }
}
