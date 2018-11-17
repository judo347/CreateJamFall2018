package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import dk.amminiti.PlayerWalkAnimation;
import dk.amminiti.entity.EnergyDrink;
import dk.amminiti.entity.Player;

public interface Spell {

    float getCooldownTotal();
    float getCooldownLeft();
    void reduceCooldown(float dt);
    boolean isOnCooldown();
    boolean canBeUsed();
    void use(Player player);
    Texture getIcon();
    PlayerWalkAnimation getBabyAnimation();
    EnergyDrink.EnergyDrinkType getType();
}
