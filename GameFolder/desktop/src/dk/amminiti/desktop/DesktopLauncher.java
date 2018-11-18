package dk.amminiti.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import dk.amminiti.MainGame;
import dk.amminiti.helpers.GameInfo;
import dk.amminiti.world.GameMap;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = Toolkit.getDefaultToolkit().getScreenSize().height - 100;
		config.width = Toolkit.getDefaultToolkit().getScreenSize().width - 100;
		config.title ="Cult of Energy Babies!";
		new LwjglApplication(new MainGame(), config);
	}
}
