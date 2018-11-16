package dk.amminiti.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import dk.amminiti.InputController;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.DynamicBody;


public class Player extends TextureObject {

    public static final float WIDTH = 0.56f;
    public static final float HEIGHT = 1.35f;

    private static final float FEET_WIDTH = WIDTH - 0.05f;
    private static final float FEET_HEIGHT = 0.23f;
    private static final float FEET_Y_OFFSET = -.85f;
    private static final float MAX_X_VEL = 6f;
    private static final float JUMP_FORCE = 11.4f;
    private static final float JUMP_FORCE_IN_AIR = 9f;
    private static final float WALK_SPEED = 6f;
    private static final float AIR_WALK_FORCE = 0.3f;

    private static final float MAX_RANGE = 5f;
    private static final float MIN_RANGE = 1.6f;
    private static final float GUN_X_OFFSET = 0.53f;
    private static final float SPAWN_TIMER = 2f;
    private static final float DEATH_TIMER_RED = 0.24f;
    private static final float RESPAWN_TIMER = 2.2f;


    private static Texture playerTexture = new Texture("badlogic.jpg");


    private World world;
    private int lookingDir = 1;
    private boolean isMidAir = false;
    private boolean hasJumped = false;
//    private PlayerAnimationController animController;

    private boolean isDead = false;
    private float timePassed = 0;
    private int deathCounter;
    private Vector2 spawnPosition;
    private GameMap map;
    private boolean isFacingRight;
    public Body body;

    public Player(World world, Vector2 pos) {
        super(world,pos,createPlayerBodyDef(),createPlayerFixtureDef(),new TextureRegion(playerTexture));
        //animController = new PlayerAnimationController(this, playerTexture);

        spawnPosition = pos;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(FEET_WIDTH/2f, FEET_HEIGHT/2f);
        body = world.createBody(createPlayerBodyDef());
        body.setTransform(spawnPosition,0);
        this.world = world;
        body.setLinearDamping(0);
        body.setUserData(this);

        deathCounter=0;
    }

    public void render(SpriteBatch batch, float delta ) {
        super.render(batch, delta);
    }




    private void movement(GameMap map, InputController controller, SpriteBatch batch, float delta) {
        Vector2 vel = body.getLinearVelocity();

        // Jump
        if (!isMidAir) hasJumped = false;
        if (!hasJumped && (controller.w || controller.space)) {
            vel.y = isMidAir ? JUMP_FORCE_IN_AIR : JUMP_FORCE;
            isMidAir = true;
            hasJumped = true;
        }

        // Movement
        int dir = controller.d ? 1 : controller.a ? -1 : 0;
        lookingDir = dir == 0 ? lookingDir : dir;
        if (!isMidAir){
            //Sliding on ice
               body.applyForceToCenter(WALK_SPEED*dir,0,true);
        }else if (!isMidAir) {
            // Grounded

            vel.x = WALK_SPEED * dir;

        } else  {
            // Mid air
            vel.add(AIR_WALK_FORCE * dir, 0);
        }


        // Restrict vel x
        vel.x = Math.min(Math.max(-MAX_X_VEL, vel.x), MAX_X_VEL);

        // Apply new vel
        body.setLinearVelocity(vel);


//        texture = animController.getTexture(dir, isMidAir, delta);
    }


    public void resolveDeath(SpriteBatch batch, float delta) {
            body.setTransform(spawnPosition, 0);
            isDead = false;
            batch.setColor(1, 1, 1, 1);
    }

    public void kill() {
        if (!isDead) {
            deathCounter++;

            isDead = true;
        }
    }

    public Vector2 getBodyPos(){
         return body.getPosition();
    }


    /** The default fixturedef for players */
    private static FixtureDef createPlayerFixtureDef(){
        float cornerSize = 0.043f;
        float width = Player.WIDTH/2f;
        float widthShort = Player.WIDTH/2f - cornerSize;
        float height = Player.HEIGHT/2f;
        float heightShort = Player.HEIGHT/2f - cornerSize;
        PolygonShape shape = new PolygonShape();
        shape.set(new Vector2[] {
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



    public void bounce(Body other) {
            if (Math.round(other.getPosition().x) == Math.round(body.getPosition().x) && body.getLinearVelocity().y < 1)
                body.setLinearVelocity(body.getLinearVelocity().x, -body.getLinearVelocity().y);
    }

    public void playPickupSound() {
    }

    public int getDeathCounter() {
        return deathCounter;
    }

    public boolean isDead() {
        return isDead;
    }
}
