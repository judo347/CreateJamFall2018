package dk.amminiti;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import dk.amminiti.entity.EnergyDrink;
import dk.amminiti.entity.Platform;
import dk.amminiti.entity.Player;
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


            //EnergyDrink collision with platforms
            if ((fa.getBody().getUserData() instanceof EnergyDrink && fb.getBody().getUserData() instanceof Platform)){
                fa.getBody().setGravityScale(0);
                fa.getBody().setLinearVelocity(new Vector2(0,0));
            }
            //EnergyDrink collision with platforms
            if ((fb.getBody().getUserData() instanceof EnergyDrink && fa.getBody().getUserData() instanceof Platform)){
                fb.getBody().setGravityScale(0);
                fb.getBody().setLinearVelocity(new Vector2(0,0));
            }
            if ((fa.getBody().getUserData() == FEET)
                    || (fb.getBody().getUserData() == FEET)) {
                feetCollisions++;
            }

            //EnergyDrink collision with player
            if ((fa.getBody().getUserData() instanceof EnergyDrink && fb.getBody().getUserData() instanceof Player)){
                EnergyDrink energyDrink =(EnergyDrink) fa.getBody().getUserData();
                Player player =(Player) fb.getBody().getUserData();

                player.CollectEnergyDrink(energyDrink.getType());

            }
            //EnergyDrink collision with player
            if ((fb.getBody().getUserData() instanceof EnergyDrink && fa.getBody().getUserData() instanceof Player)){
                EnergyDrink energyDrink =(EnergyDrink) fb.getBody().getUserData();
                Player player =(Player) fa.getBody().getUserData();

                player.CollectEnergyDrink(energyDrink.getType());
            }




            //Check for player collison with energy drink

            if (fa.getBody().getUserData() instanceof Player || fb.getBody().getUserData() instanceof Player) {
                Player player;
                Object other;
                if (fa.getBody().getUserData() instanceof Player) {
                    player = (Player) fa.getBody().getUserData();
                    other = fb.getBody().getUserData();
                } else {
                    player = (Player) fb.getBody().getUserData();
                    other = fa.getBody().getUserData();
                }

            }





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
