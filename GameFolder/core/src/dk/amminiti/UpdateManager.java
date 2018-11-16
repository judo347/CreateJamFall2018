package dk.amminiti;

import java.util.LinkedList;
import java.util.List;

public class UpdateManager {

    private static LinkedList<Updateable> updateables = new LinkedList<Updateable>();

    public static List<Updateable> getAll() {
        return updateables;
    }

    public static void updateAll(float dt) {
        for (Updateable updateable : updateables) {
            updateable.update(dt);
        }
    }
}
