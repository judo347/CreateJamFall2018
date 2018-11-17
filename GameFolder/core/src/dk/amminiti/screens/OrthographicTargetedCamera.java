package dk.amminiti.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import dk.amminiti.entity.Player;
import dk.amminiti.helpers.GameInfo;

import java.util.ArrayList;

public class OrthographicTargetedCamera extends OrthographicCamera {

    private static final float SPEED = 0.15f; // Opposite of smooth movement. 1 = instant, 0 = no movement
    private static final float ZOOM = GameInfo.PPM/1f;

    public final ArrayList<Player> targets = new ArrayList<Player>();
    private static final Vector2 ARENA_CENTER = new Vector2(0, 12);

    public OrthographicTargetedCamera(float viewportWidth, float viewportHeight) {
        super(viewportWidth, viewportHeight);
        zoom = ZOOM;
    }

    @Override
    public void update() {
        Vector2 targetPos = new Vector2(ARENA_CENTER);
        if (targets != null && targets.size() != 0) {
            for (Player target : targets) {
                targetPos.add(target.getBodyPos());
            }
            targetPos.scl(1f / (targets.size() + 1));
        }

        Vector2 diff = new Vector2(targetPos.x - position.x, targetPos.y - position.y);
        position.x += diff.x * SPEED;
        position.y += diff.y * SPEED;

        super.update();
    }
}
