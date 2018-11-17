package dk.amminiti.world;

import com.badlogic.gdx.math.Vector2;
import dk.amminiti.entity.EnergyDrink;

import java.util.Random;

public class DrinkSpawner {

    private static final float SPAWN_Y = 25;
    private static final float SPAWN_X_RANGE = 25;
    private static final float INTERVAL = 4f;
    private static final float INITIAL_TIME = 7f;

    private Random rand = new Random();
    private GameMap map;
    private float timer = 0;

    public DrinkSpawner(GameMap map) {
        this.map = map;
        timer = INITIAL_TIME;
    }
     public void update(float dt) {
        timer -= dt;
        if (timer <= 0) {
            timer = INTERVAL;
            spawnDrink();
        }
     }

    private void spawnDrink() {
        float r = rand.nextInt(100) / 100f;
        float x = SPAWN_X_RANGE / -2f + SPAWN_X_RANGE * r;
        Vector2 pos = new Vector2(x, SPAWN_Y);
        int typeCount = EnergyDrink.EnergyDrinkType.values().length;
        // Type 0 is CULT, never spawn that one.
        EnergyDrink.EnergyDrinkType type = EnergyDrink.EnergyDrinkType.values()[rand.nextInt(typeCount - 1) + 1];
        EnergyDrink drink = new EnergyDrink(map, pos, type);
        map.addToWorldQueue(drink);
    }
}
