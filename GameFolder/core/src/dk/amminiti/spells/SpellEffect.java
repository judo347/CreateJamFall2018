package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import dk.amminiti.entity.AnimatedObject;
import dk.amminiti.entity.Player;
import dk.amminiti.entity.TextureObject;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.DynamicBody;

public abstract class SpellEffect extends AnimatedObject {

    protected float lifeTime;
    protected Player owner;

    public SpellEffect(FixtureDef fixtureDef, Texture texture, int numberOfFrames, float animationSpeed, float lifeTime, Player owner) {
        super(owner.getBody().getWorld(), owner.getHeadPos(), createBodyDef(), fixtureDef, texture, numberOfFrames, animationSpeed);
        this.lifeTime = lifeTime;
        this.owner = owner;
    }


    public void render(SpriteBatch batch, float delta){
        super.render(batch, delta);
        reduceLifetime(delta);
        if (lifeTime<=0){
            owner.getMap().addToDestroyQueue(this);
        }
    }

    protected void reduceLifetime(float dt) {
        lifeTime -= dt;
    }


    protected static BodyDef createBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = DynamicBody;
        bodyDef.gravityScale=0;
        return bodyDef;
    }



}
