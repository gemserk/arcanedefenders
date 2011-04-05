package com.gemserk.games.arcanedefenders;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.gemserk.animation4j.converters.Converters;
import com.gemserk.animation4j.gdx.converters.LibgdxConverters;

public class ArcaneDefendersGame extends Game {
	
	@Override
	public void create() {
		
		Converters.register(Vector2.class, LibgdxConverters.vector2());
		Converters.register(Color.class, LibgdxConverters.color());
		
		setScreen(new SplashScreen(this));
		
	}

}
