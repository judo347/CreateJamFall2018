package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import dk.amminiti.entity.Player;
import dk.amminiti.helpers.GameInfo;


public class CultSpellEffect extends SpellEffect {

    static Texture texture = new Texture("cult effect.png");
    static float lifeTime = 3f;
    static float speed = 2000;


    /**
     * An GameObject that always have a textre drawn at the body's position.
     *
     */
    public CultSpellEffect(Player player) {
        super(player,  lifeTime, texture);
        System.out.println(getBody().getPosition() + " Positionen for spell effect");
        System.out.println(player.getBodyPos() + " Positionen for player");
        this.body.applyForce(new Vector2(player.getLookingDir()*speed* GameInfo.PPM,0),body.getPosition(),true);

    }

}
