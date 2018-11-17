package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import dk.amminiti.entity.Player;
import dk.amminiti.helpers.GameInfo;


public class RedCowSpellEffect extends SpellEffect {

    static Texture texture = new Texture("energydrinks/redcow.png");
    static float lifeTime = 3f;
    static float speed = 2000;

    /**
     * An GameObject that always have a textre drawn at the body's position.
     *
     */
    public RedCowSpellEffect(Player player) {
        super(player,  lifeTime, texture);
        this.body.applyForce(new Vector2(player.getLookingDir()*speed* GameInfo.PPM,0),body.getPosition(),true);
    }
}





