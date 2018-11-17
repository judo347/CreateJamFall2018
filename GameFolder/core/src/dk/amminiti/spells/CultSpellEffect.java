package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import dk.amminiti.entity.Player;
import dk.amminiti.entity.TextureObject;
import dk.amminiti.helpers.GameInfo;


public class CultSpellEffect extends SpellEffect {

    static Texture texture = new Texture("spellEffects/cult.png");
    static float lifeTime = 1f;
    static float speed = 0;

    public CultSpellEffect(Player player) {
        super(createFixtureDef(player), texture, 1, 2, lifeTime, player);
        this.body.applyForce(new Vector2(player.getLookingDir()*speed* GameInfo.PPM,0),body.getPosition(),true);
    }

    private static FixtureDef createFixtureDef(Player player){

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = (player.getLookingDir() > 0) ? getRightDirectionShape(): getLeftDirectionShape();
        fixtureDef.isSensor = true;

        return fixtureDef;
    }

    private static Shape getRightDirectionShape(){
        PolygonShape shape = new PolygonShape();
        shape.set(new Vector2[]{
                new Vector2(40 * GameInfo.PPM, 60 * GameInfo.PPM), //Up right
                new Vector2(40 * GameInfo.PPM, 0), //down right
                new Vector2(0, 10 * GameInfo.PPM), //down left
                new Vector2(0, 50 * GameInfo.PPM), //up left
        });

        return shape;
    }

    private static Shape getLeftDirectionShape(){
        PolygonShape shape = new PolygonShape();
        shape.set(new Vector2[]{
                new Vector2(-40 * GameInfo.PPM, -60 * GameInfo.PPM), //Up right
                new Vector2(-40 * GameInfo.PPM, 0), //down right
                new Vector2(0, -10 * GameInfo.PPM), //down left
                new Vector2(0, -50 * GameInfo.PPM), //up left
        });

        return shape;
    }
}
