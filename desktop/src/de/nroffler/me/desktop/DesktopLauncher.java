package de.nroffler.me.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.nroffler.me.main.EndlessGame;
import de.nroffler.me.main.Statics;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Statics.WIDTH;
		config.height = Statics.HEIGHT;
		config.useGL30 = true;
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;
		new LwjglApplication(new EndlessGame(), config);
	}
}
