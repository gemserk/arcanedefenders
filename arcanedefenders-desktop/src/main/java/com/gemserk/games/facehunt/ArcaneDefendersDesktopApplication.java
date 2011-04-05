package com.gemserk.games.facehunt;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.gemserk.games.arcanedefenders.ArcaneDefendersGame;

public class ArcaneDefendersDesktopApplication {
	public static void main (String[] argv) {
		new LwjglApplication(new ArcaneDefendersGame(), "Arcane Defenders", 800, 480, false);
	}
}
