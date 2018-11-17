package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import dk.amminiti.entity.EnergyDrink;
import dk.amminiti.entity.Player;
import dk.amminiti.entity.TextureObject;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.DynamicBody;

public abstract class SpellEffect extends TextureObject {

    protected float lifeTime;
    protected Player owner;
    protected EnergyDrink.EnergyDrinkType type;
    protected float basePower = 100;
    protected float power;

    /**
     * An GameObject that always have a textre drawn at the body's position.
     * @param texture
     */
    public SpellEffect(Player player, float lifeTime, Texture texture, EnergyDrink.EnergyDrinkType type) {
        super(player.getBody().getWorld(), player.getHeadPos(), createBodyDef(), createSensorFixtureDef(texture), new TextureRegion(texture));
        this.lifeTime = lifeTime;
        this.type = type;
        this.owner = player;
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