package dk.amminiti.world;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Scaling;
import dk.amminiti.entity.GameObject;
import dk.amminiti.entity.TextureObject;
import dk.amminiti.helpers.GameInfo;
import dk.amminiti.screens.GameScreen;

import static sun.plugin.javascript.navig.JSType.Image;

public class GameMap {

    private TextureObject groundBox;
    private World world;

    public GameMap(GameScreen screen) {
        this.world = screen.getWorld();

        initializePlatforms();
    }

    private void initializePlatforms(){

        this.groundBox = getPlatform();
    }

    private void update(float delta){
    }

    public void render(SpriteBatch batch, float delta){

        update(delta);

        groundBox.render(batch, delta);

        //batch.draw();

    }

    private TextureObject getPlatform(){

        return new TextureObject(world, new Vector2(0,0), GameObject.DEFAULT_STATIC_BODYDEF, GameObject.DEFAULT_STATIC_FIXTUREDEF, new TextureRegion(new Texture(Gdx.files.internal("platformTemp.png"))));
    }

    private Body createPlatform(World world, Vector2 pos, int width, int height){

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.fixedRotation = true;
        bodyDef.position.set(((pos.x + width / 2) / GameInfo.PPM), (pos.y + height / 2) / GameInfo.PPM);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((width/2) / GameInfo.PPM, (height/2) / GameInfo.PPM);

        FixtureDef fixtureDef = getPlatformFixtureDef();

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData("platform"); //TODO maybe change this

        shape.dispose();

        return body;
    }

    private FixtureDef getPlatformFixtureDef(){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 0.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;
        fixtureDef.friction = 1;
        fixtureDef.restitution = 0;

        return fixtureDef;
    }
}
