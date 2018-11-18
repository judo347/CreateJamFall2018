package dk.amminiti.spells;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import dk.amminiti.PlayerWalkAnimation;
import dk.amminiti.entity.EnergyDrink;
import dk.amminiti.entity.Player;

public class FoosterSpell implements Spell {

    private EnergyDrink.EnergyDrinkType type = EnergyDrink.EnergyDrinkType.FOOSTER;

    private PlayerWalkAnimation babyAnimation = new PlayerWalkAnimation(new Texture("baby_crawl_fooster.png"));

    private Sound castingSound;
    private Sound hitSound;
    private Sound travelSound;

    private final float cooldownTotal = 3;
    private float cooldownLeft = 0;
    private float manacost = 25;


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
            player.useMana(manacost);
            player.applyHitForce(new Vector2(15 * player.getLookingDir(), 3));
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
