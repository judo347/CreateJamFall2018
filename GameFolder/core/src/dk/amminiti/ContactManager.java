package dk.amminiti;

import com.badlogic.gdx.physics.box2d.*;
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

            if (fa.getBody().getUserData() == FEET || fb.getBody().getUserData() == FEET) {
                feetCollisions++;
            }

            //Check for player collison
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
