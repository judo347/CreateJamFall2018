package dk.amminiti.spells;

import com.badlogic.gdx.graphics.Texture;
import dk.amminiti.PlayerWalkAnimation;
import dk.amminiti.entity.EnergyDrink;
import dk.amminiti.entity.Player;

public class CultSpell implements Spell {

    private EnergyDrink.EnergyDrinkType type = EnergyDrink.EnergyDrinkType.CULT;

    private PlayerWalkAnimation babyAnimation = new PlayerWalkAnimation(new Texture("baby_crawl_cult.png"));

    private final float cooldownTotal = 1.5f;
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
    public PlayerWalkAnimation getBabyAnimation() {
        return babyAnimation;
    }

    @Override
    public EnergyDrink.EnergyDrinkType getType() {
        return type;
    }
}
