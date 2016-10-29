package net.digaly.doodle.test.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.digaly.doodle.test.DoodleGDXTest;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.fullscreen = true;
		config.vSyncEnabled = true;

		config.width = 1024;
		config.height = 768;
        config.resizable = false;

		new LwjglApplication(new DoodleGDXTest(), config);
	}
}
