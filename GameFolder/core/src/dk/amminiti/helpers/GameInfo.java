package dk.amminiti.helpers;

public class GameInfo {

    public static final int TILE_SIZE = 40;
    public static final float PPM = 1f/TILE_SIZE; //100px = 1m //PPM pixels per min //THIS ONE HAS TO BE A FLOAT!
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 800;
    public static final float ZOOM = PPM/0.1f;
}
