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

    private Animation[] animation;
    private Texture texture;

    private final int NUMBER_OF_FRAMES;
    private final float ANIMATION_SPEED;

    private int currentFrame;
    private float stateTime;

    public AnimatedObject(World world, Vector2 pos, BodyDef bodyDef, FixtureDef fixtureDef, Texture texture, int numberOfFrames) {
        this( world, pos, bodyDef, fixtureDef, texture, numberOfFrames, 0.2f);
    }

    public AnimatedObject(World world, Vector2 pos, BodyDef bodyDef, FixtureDef fixtureDef, Texture texture, int numberOfFrames, float animationSpeed) {
        super(world, pos, bodyDef, fixtureDef);

        this.NUMBER_OF_FRAMES = numberOfFrames;
        this.ANIMATION_SPEED = animationSpeed;

        this.texture = texture;
        this.currentFrame = 0;
        this.stateTime = 0f;

        setUpAnimation();
    }

    /** Sets up the animation for the the object. */
    private void setUpAnimation(){

        this.animation = new Animation[NUMBER_OF_FRAMES];
        TextureRegion[][] resourceTextureSheet = TextureRegion.split(texture, texture.getWidth() / NUMBER_OF_FRAMES, texture.getHeight());

        System.out.println(texture.getWidth() / NUMBER_OF_FRAMES);

        for(int i = 0; i < NUMBER_OF_FRAMES; i++){
            animation[i] = new Animation(ANIMATION_SPEED, resourceTextureSheet[0][i]);
        }
    }

    /** Render the AnimationObject */
    @Override
    public void render(SpriteBatch batch, float delta){
        stateTime += delta;
        updateFrame();

        Vector2 pos = body.getPosition();
        float width = getCurrentFrame().getRegionWidth() * GameInfo.PPM;
        float height = getCurrentFrame().getRegionHeight() * GameInfo.PPM;
        //batch.begin();
        batch.draw(getCurrentFrame(), pos.x - width/2, pos.y - height/2, width / 2f, height / 2f, width, height, 1, 1, body.getAngle());;
        //batch.end();
        //batch.draw(getCurrentFrame(), body.getPosition().x - getCurrentFrame().getRegionWidth()/2f, body.getPosition().y - getCurrentFrame().getRegionHeight()/2f);
    }

    /** Updates the current frame number */
    private void updateFrame() {

        if(stateTime > ANIMATION_SPEED){ //Has enough time passed to switch frame?


            if(currentFrame+1 == NUMBER_OF_FRAMES){ //Have we reached the end of animation?
                currentFrame = 0;
            }else
                currentFrame++;

            stateTime -= ANIMATION_SPEED;
        }
    }

    public TextureRegion getCurrentFrame(){
        return (TextureRegion) animation[currentFrame].getKeyFrame(stateTime, true); //TODO THIS MIGHT BE A BUG
    }
}
