package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import dk.amminiti.entity.AnimatedObject;
import dk.amminiti.entity.EnergyDrink;
import dk.amminiti.entity.Player;
import dk.amminiti.entity.TextureObject;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.DynamicBody;

public abstract class SpellEffect extends AnimatedObject {

    protected float lifeTime;
    protected Player owner;
    protected EnergyDrink.EnergyDrinkType type;
    protected int directionWhenCast;
    protected int level;

    public SpellEffect(FixtureDef fixtureDef, Texture texture, float offsetFromHead, int numberOfFrames, float animationSpeed, float lifeTime, Player owner, EnergyDrink.EnergyDrinkType type) {
        super(owner.getBody().getWorld(), getSpellPosRelativToHead(owner, offsetFromHead), createBodyDef(), fixtureDef, texture, numberOfFrames, animationSpeed);
        this.lifeTime = lifeTime;
        this.owner = owner;
        this.type = type;
        this.level = owner.getSpellLevel();
        this.directionWhenCast = owner.getLookingDir();
    }

    private static Vector2 getSpellPosRelativToHead(Player player, float offset){

        float x, y;

        if(player.getLookingDir() < 0){

            x = player.getHeadPos().x - offset;
            y = player.getHeadPos().y;

        }else{ //RIGHT
            x = player.getHeadPos().x + offset;
            y = player.getHeadPos().y;
        }

        //float x = player.getHeadPos().x + (texture.getWidth() / 100f) * player.getLookingDir();
        //float y = player.getHeadPos().y;

        return new Vector2(x, y);
    }

    public void render(SpriteBatch batch, float delta) {

        super.render(batch, delta);
        reduceLifetime(delta);
        if (lifeTime <= 0) {
            owner.getMap().addToDestroyQueue(this);
        }
    }

    protected void reduceLifetime(float dt) {
        lifeTime -= dt;
    }

    public EnergyDrink.EnergyDrinkType getType() {
        return type;
    }

    public Player getOwner() {
        return owner;
    }

    protected static BodyDef createBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = DynamicBody;
        bodyDef.gravityScale = 0;
        return bodyDef;
    }
}