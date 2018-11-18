package dk.amminiti;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import dk.amminiti.entity.*;
import dk.amminiti.spells.*;
import dk.amminiti.world.GameMap;


public class ContactManager implements ContactListener {

        public static final String FEET = "Feet";

        private World world;
        private GameMap gameMap;

        public static int feetCollisions = 0;

        public ContactManager(World world, GameMap gameMap){
            this.world = world;
            this.gameMap = gameMap;
        }

        @Override
        public void beginContact(Contact contact) {
            Fixture fa = contact.getFixtureA();
            Fixture fb = contact.getFixtureB();



            //Kill walls detection
            if(fa.getBody().getUserData() instanceof PlatformWithoutTexture || fb.getBody().getUserData() instanceof PlatformWithoutTexture){
                if(fa.getBody().getUserData() instanceof Player)
                    gameMap.killPlayer((Player)fa.getBody().getUserData());

                if(fb.getBody().getUserData() instanceof Player)
                    gameMap.killPlayer((Player)fb.getBody().getUserData());

                if (fa.getBody().getUserData() instanceof EnergyDrink){
                    gameMap.addToDestroyQueue((EnergyDrink)fa.getBody().getUserData());
                }
                if (fb.getBody().getUserData() instanceof EnergyDrink){
                    gameMap.addToDestroyQueue((EnergyDrink)fb.getBody().getUserData());
                }

            }


            if (fa.getBody().getUserData() instanceof Player && (fb.getBody().getUserData() instanceof Player || fb.getBody().getUserData() instanceof Platform))
            {
                ((Player)fa.getBody().getUserData()).feetCollision();
            }
            if (fb.getBody().getUserData() instanceof Player && (fa.getBody().getUserData() instanceof Player || fa.getBody().getUserData() instanceof Platform))
            {
                ((Player)fb.getBody().getUserData()).feetCollision();
            }

            //EnergyDrink collision with player
            if ((fa.getBody().getUserData() instanceof EnergyDrink && fb.getBody().getUserData() instanceof Player)){
                resolveEnergyDrinkPlayerCollision((Player) fb.getBody().getUserData(),(EnergyDrink) fa.getBody().getUserData());
            }
            //EnergyDrink collision with player
            if ((fb.getBody().getUserData() instanceof EnergyDrink && fa.getBody().getUserData() instanceof Player)){
                resolveEnergyDrinkPlayerCollision((Player) fa.getBody().getUserData(),(EnergyDrink) fb.getBody().getUserData());
            }

            //Spell collision with player
            if ((fa.getBody().getUserData() instanceof SpellEffect && fb.getBody().getUserData() instanceof Player)){
                spellPlayerCollision((Player) fb.getBody().getUserData(),(SpellEffect) fa.getBody().getUserData());
            }
            //Spell collision with player
            if ((fb.getBody().getUserData() instanceof SpellEffect && fa.getBody().getUserData() instanceof Player)){
                spellPlayerCollision((Player) fa.getBody().getUserData(),(SpellEffect) fb.getBody().getUserData());
            }

            if ((fa.getBody().getUserData() instanceof WonsterSpellEffect && fb.getBody().getUserData() instanceof EnergyDrink))
            {
                fa.getBody().applyForceToCenter(new Vector2(2,0),true);
            }
            if ((fb.getBody().getUserData() instanceof WonsterSpellEffect && fa.getBody().getUserData() instanceof EnergyDrink))
            {
                fb.getBody().applyForceToCenter(new Vector2(2,0),true);
            }


            //FireSpell collision with platform
            if ((fa.getBody().getUserData() instanceof FireSpellEffect && fb.getBody().getUserData() instanceof Platform)){
                gameMap.addToDestroyQueue((FireSpellEffect)fa.getBody().getUserData());
            }
            //FireSpell collision with platform
            if ((fb.getBody().getUserData() instanceof FireSpellEffect && fa.getBody().getUserData() instanceof Platform)){
                gameMap.addToDestroyQueue((FireSpellEffect)fb.getBody().getUserData());
            }
        }

    private void spellPlayerCollision(Player player, SpellEffect effect) {

        if (player.equals(effect.getOwner())) return;

        if (effect instanceof RedCowSpellEffect){
            effect.applyForce(player);
        }
        else if (!(effect instanceof FoosterMovementEffect)){
            effect.applyForce(player);
            player.getMap().addToDestroyQueue(effect);
        }
    }

    private void resolveEnergyDrinkPlayerCollision(Player player, EnergyDrink energyDrink) {
        player.CollectEnergyDrink(energyDrink.getType());
        energyDrink.removeFromWorld();

    }

    @Override
    public void endContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if (fa.getBody().getUserData() == FEET || fb.getBody().getUserData() == FEET) {
            feetCollisions--;
        }
    }

    @Override
    public void preSolve (Contact contact, Manifold oldManifold){

    }

    @Override
    public void postSolve (Contact contact, ContactImpulse impulse){


    }

}
