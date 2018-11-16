package dk.amminiti;

import com.badlogic.gdx.graphics.Texture;

public interface Spell {

    double getCooldownTotal();
    double getCooldownLeft();
    void reduceCooldown(double dt);
    boolean isOnCooldown();
    boolean canBeUsed();
    void use(Player player);
    Texture getIcon();
    Texture getBabyTexture();
}
