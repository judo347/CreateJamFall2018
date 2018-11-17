package dk.amminiti.helpers;

public class EntityDimensions {

    public static final Dimensions PLAYER_DIMENSIONS = new Dimensions(32, 64);
    public static final Dimensions PLATFORM_DIMENSIONS = new Dimensions(400, 50);

    public static class Dimensions{

        private final int width;
        private final int height;

        public Dimensions(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }
}
