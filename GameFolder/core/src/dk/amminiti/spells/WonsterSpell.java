package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import dk.amminiti.entity.EnergyDrink;
import dk.amminiti.entity.Player;

public class WonsterSpell implements Spell{

    private EnergyDrink.EnergyDrinkType type = EnergyDrink.EnergyDrinkType.WONSTER;

    private Texture baby = new Texture("baby.png");

    private final float cooldownTotal = 5;
    private float cooldownLeft = 0;

    @Override
    public float getCooldownTotal() {
        return cooldownTotal;
    }

    @Override
    public float getCooldownLeft() {
        return cooldownLeft;
    }

    @Override
    public void reduceCooldown(float dt) {

    }

    @Override
    public boolean isOnCooldown() {
        return false;
    }

    @Override
    public boolean canBeUsed() {
        return false;
    }

    @Override
    public void use(Player player) {
        if (!isOnCooldown()) {
            player.getMap().addToWorldQueue(new WonsterSpellEffect(player));
            cooldownLeft = cooldownTotal;
        }
    }

    @Override
    public Texture getIcon() {
        return null;
    }

    @Override
    public Texture getBabyTexture() {
        return baby;
    }

    @Override
    public EnergyDrink.EnergyDrinkType getType() {
        return type;
    }
}
