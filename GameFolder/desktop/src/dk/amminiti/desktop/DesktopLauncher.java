package dk.amminiti.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import dk.amminiti.MainGame;
import dk.amminiti.helpers.GameInfo;
import dk.amminiti.world.GameMap;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = GameInfo.SCREEN_HEIGHT;
		config.width = GameInfo.SCREEN_WIDTH;
		new LwjglApplication(new MainGame(), config);
	}
}
