package dk.amminiti.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import dk.amminiti.helpers.GameInfo;

public class Platform extends Entity{

    public Platform(World world, Vector2 pos, EntityType entityType, Texture texture) {
        super(world, pos, entityType, texture);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch batch, float delta) {
        if (texture != null) {
            Vector2 pos = body.getPosition();
            float width = texture.getWidth() * GameInfo.PPM;
            float height = texture.getHeight() * GameInfo.PPM;
            //batch.draw(texture, pos.x - width / 2, pos.y - height / 2, width / 2f, height / 2f, width, height, 1, 1, body.getAngle());
            //batch.draw(texture, pos.x - width / 2, pos.y - height / 2);
            batch.draw(texture, pos.x, pos.y);
        }
    }

    @Override
    public void dispose() {

    }
}
