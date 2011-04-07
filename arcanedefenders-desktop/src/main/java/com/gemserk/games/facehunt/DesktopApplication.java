package com.gemserk.games.facehunt;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.gemserk.games.arcanedefenders.LibgdxGame;

public class DesktopApplication {
	public static void main (String[] argv) {
		new LwjglApplication(new LibgdxGame(), "Arcane Defenders", 480, 800, false);
	}
}
