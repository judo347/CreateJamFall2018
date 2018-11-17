package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import dk.amminiti.entity.EnergyDrink;
import dk.amminiti.entity.Player;

public class CultSpell implements Spell {

    private EnergyDrink.EnergyDrinkType type = EnergyDrink.EnergyDrinkType.CULT;

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
        if (!isOnCooldown()) {
            player.getMap().addToWorldQueue(new CultSpellEffect(player));
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
