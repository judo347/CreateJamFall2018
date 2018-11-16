package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import dk.amminiti.entity.Player;

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
