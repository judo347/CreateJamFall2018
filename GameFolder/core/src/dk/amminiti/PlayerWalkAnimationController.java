package dk.amminiti;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerWalkAnimationController {

    private static final int FRAME_COUNT = 2;
    private static final float FRAME_DURATION = 0.3f;

    private PlayerWalkAnimation animation;

    private int lastDirNonNegative = 1;
    private int currentFrame = 0;
    private float timer = 0;

    public PlayerWalkAnimationController(PlayerWalkAnimation animation) {
        this.animation = animation;
    }

    public void setAnimation(PlayerWalkAnimation animation) {
        if (animation == null) return;
        this.animation = animation;
    }

    public TextureRegion getTexture(int dir, boolean midAir, float dt) {
        if (dir == 0) {
            currentFrame = midAir ? 1 : 0;
            timer = 0;
        } else {
            timer += dt;
            lastDirNonNegative = dir == 1 ? 0 : 1;
            if (timer > FRAME_DURATION) {
                timer -= FRAME_DURATION;
                currentFrame = (currentFrame + 1) % FRAME_COUNT;
            }
        }
        return animation.frames[lastDirNonNegative][currentFrame];
    }
}

