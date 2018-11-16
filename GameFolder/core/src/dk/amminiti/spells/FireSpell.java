package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import dk.amminiti.entity.Player;

public class FireSpell implements Spell {

    private Texture baby = new Texture("baby.png");

    private final double cooldownTotal = 5;
    private double cooldownLeft = 0;

    @Override
    public double getCooldownTotal() {
        return 4;
    }

    @Override
    public double getCooldownLeft() {
        return 0;
    }

    @Override
    public void reduceCooldown(double dt) {
        cooldownLeft -= dt;
    }

    @Override
    public boolean isOnCooldown() {
        return cooldownLeft > 0;
    }

    @Override
    public boolean canBeUsed() {
        return cooldownLeft <= 0;
    }

    @Override
    public void use(Player player) {

        cooldownLeft = cooldownTotal;
    }

    @Override
    public Texture getIcon() {
        return null;
    }

    @Override
    public Texture getBabyTexture() {
        return baby;
    }
}
