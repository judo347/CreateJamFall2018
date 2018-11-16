package dk.amminiti.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import dk.amminiti.ContactManager;
import dk.amminiti.InputController;
import dk.amminiti.PlayerInputProcessor;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.DynamicBody;


public class Player extends TextureObject {

    public static final float WIDTH = 0.56f;
    public static final float HEIGHT = 1.35f;

    private static final float FEET_WIDTH = WIDTH - 0.05f;
    private static final float FEET_HEIGHT = 0.23f;
    private static final Vector2 FEET_Y_OFFSET = new Vector2(0, -.85f);
    private static final float MAX_X_VEL = 6f;
    private static final float JUMP_FORCE = 11.4f;
    private static final float JUMP_FORCE_IN_AIR = 9f;
    private static final float WALK_SPEED = 6f;
    private static final float AIR_WALK_FORCE = 2f;
    private static final float AIR_DRAG = 2f;

    private static Texture playerTexture = new Texture("baby.png");

    private PlayerInputProcessor inputs;
    private Body feet;
    private int lookingDir = 1;
    private boolean isMidAir = false;
    private boolean hasJumped = false;

    private boolean isFacingRight;

    public Player(World world, Vector2 pos, PlayerInputProcessor inputs) {
        super(world, pos, createPlayerBodyDef(), createPlayerFixtureDef(), new TextureRegion(playerTexture));
        this.inputs = inputs;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(FEET_WIDTH / 2f, FEET_HEIGHT / 2f);
        createFeet();
        body.setLinearDamping(0);
        body.setUserData(this);
    }

    private void createFeet() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(FEET_WIDTH / 2f, FEET_HEIGHT / 2f);
        FixtureDef feetDef = new FixtureDef();
        feetDef.shape = shape;
        feetDef.density = 0;
        feetDef.friction = 1;
        feetDef.restitution = 0;
        feetDef.isSensor = false;
        feet = new GameObject(world, new Vector2(body.getPosition().add(FEET_Y_OFFSET)), createPlayerBodyDef(), feetDef).getBody();
        feet.setUserData(ContactManager.FEET);
        feet.setGravityScale(0);
    }

    public void render(SpriteBatch batch, float delta) {
        movement();
        super.render(batch, delta);
    }

    void movement() {
        Vector2 vel = body.getLinearVelocity();

        isMidAir = !(ContactManager.feetCollisions > 0 && Math.abs(vel.y) <= 1e-2);

        // Jump
        if (!isMidAir) hasJumped = false;
        if (!hasJumped && (inputs.isUpPressed())) {
            vel.y = isMidAir ? JUMP_FORCE_IN_AIR : JUMP_FORCE;
            isMidAir = true;
            hasJumped = true;
        }

        // Movement
        int dir = inputs.isRightPressed() ? 1 : inputs.isLeftPressed() ? -1 : 0;
        lookingDir = dir == 0 ? lookingDir : dir;
        if (!isMidAir) {
            // Grounded
            vel.x = WALK_SPEED * dir;

        } else {
            // Mid air
            if (dir != 0) {
                vel.add(AIR_WALK_FORCE * dir, 0);
            } else if (vel.x != 0) {
                // Air drag
                int sgn = vel.x > 0 ? 1 : -1;
                vel.x = Math.max(Math.abs(vel.x) - AIR_DRAG, 0);
            }
        }

        // Restrict vel x
        vel.x = Math.min(Math.max(-MAX_X_VEL, vel.x), MAX_X_VEL);

        // Apply new vel
        body.setLinearVelocity(vel);

        // Move feet
        feet.setTransform(new Vector2(body.getPosition()).add(FEET_Y_OFFSET), 0);
        feet.setLinearVelocity(vel);
    }

    public Vector2 getBodyPos() {
        return body.getPosition();
    }

    /** The default fixturedef for players */
    private static FixtureDef createPlayerFixtureDef() {
        float cornerSize = 0.043f;
        float width = Player.WIDTH / 2f;
        float widthShort = Player.WIDTH / 2f - cornerSize;
        float height = Player.HEIGHT / 2f;
        float heightShort = Player.HEIGHT / 2f - cornerSize;
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
        fixtureDef.density = 1f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0;

        return fixtureDef;

    }

    /** The BodyDef used for something like players */
    private static BodyDef createPlayerBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = DynamicBody;
        return bodyDef;
    }
}
