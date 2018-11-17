package dk.amminiti.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import dk.amminiti.helpers.GameInfo;

public class AnimatedObject extends RenderObject {

    private TextureRegion[] frames;
    private Texture texture;

    private final int NUMBER_OF_FRAMES;
    private final float TIME_PER_FRAME;
    private final int FRAME_WIDTH;
    private final int FRAME_HEIGHT;

    private int currentFrame;
    private float stateTime;

    public AnimatedObject(World world, Vector2 pos, BodyDef bodyDef, FixtureDef fixtureDef, Texture texture, int numberOfFrames) {
        this(world, pos, bodyDef, fixtureDef, texture, numberOfFrames, 0.2f);
    }

    public AnimatedObject(World world, Vector2 pos, BodyDef bodyDef, FixtureDef fixtureDef, Texture texture, int numberOfFrames, float timePerFrame) {
        super(world, pos, bodyDef, fixtureDef);

        this.NUMBER_OF_FRAMES = numberOfFrames;
        this.TIME_PER_FRAME = timePerFrame;

        this.currentFrame = 0;
        this.stateTime = 0f;
        this.texture = texture;

        this.FRAME_WIDTH = texture.getWidth() / NUMBER_OF_FRAMES;
        this.FRAME_HEIGHT = texture.getHeight();

        setUpAnimation();

    }

    /** Sets up the animation for the the object. */
    private void setUpAnimation() {

        frames = new TextureRegion[NUMBER_OF_FRAMES];

        for (int i = 0; i < NUMBER_OF_FRAMES; i++) {
            frames[i] = new TextureRegion(texture, i * FRAME_WIDTH, 0, FRAME_WIDTH, FRAME_HEIGHT);
        }
    }

    /** Render the AnimationObject */
    @Override
    public void render(SpriteBatch batch, float delta) {
        updateFrame(delta);

        Vector2 pos = body.getPosition();
        float width = getCurrentFrame().getRegionWidth() * GameInfo.PPM;
        float height = getCurrentFrame().getRegionHeight() * GameInfo.PPM;
        batch.draw(getCurrentFrame(), pos.x - width / 2, pos.y - height / 2, width / 2f, height / 2f, width, height, 1, 1, body.getAngle());

    }

    /** Updates the current frame number */
    private void updateFrame(float delta) {
        stateTime += delta;
        if (stateTime >= TIME_PER_FRAME) { //Has enough time passed to switch frame?

            if (currentFrame + 1 == NUMBER_OF_FRAMES) { //Have we reached the end of animation?
                // nothing
            } else
                currentFrame++;

            stateTime -= TIME_PER_FRAME;
        }
        System.out.println("Frame:" + currentFrame + ", time: " + stateTime);
    }

    public TextureRegion getCurrentFrame() {
        return frames[currentFrame];
    }
}
