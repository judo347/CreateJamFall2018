package dk.amminiti;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerWalkAnimation {

    private final int FRAME_WIDTH = 80;
    private final int FRAME_HEIGHT = 48;
    private final float FRAME_DURATION = 0.12f;

    // first index is facing direction, next is frame
    public TextureRegion[][] frames;

    public PlayerWalkAnimation(Texture spriteSheet) {
        frames = new TextureRegion[2][2];
        frames[0][0] = new TextureRegion(spriteSheet, 0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        frames[0][1] = new TextureRegion(spriteSheet, FRAME_WIDTH, 0, FRAME_WIDTH, FRAME_HEIGHT);
        frames[1][0] = new TextureRegion(spriteSheet, 0, FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT);
        frames[1][1] = new TextureRegion(spriteSheet, FRAME_WIDTH, FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT);
    }
}
