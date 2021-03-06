package com.gemserk.games.arcanedefenders;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.math.Vector2;
import com.gemserk.animation4j.converters.Converters;
import com.gemserk.animation4j.gdx.converters.LibgdxConverters;
import com.gemserk.commons.gdx.SplashScreen;

public class LibgdxGame extends Game {
	
	@Override
	public void create() {
		
		Converters.register(Vector2.class, LibgdxConverters.vector2());
		Converters.register(Color.class, LibgdxConverters.color());
		
		final Game game = this;
		
		final Texture gemserkLogo = new Texture(Gdx.files.internal("data/logo-gemserk-512x128-white.png"));
		gemserkLogo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		setScreen(new SplashScreen(gemserkLogo) {
			
			@Override
			protected void onSplashScreenFinished() {
				game.setScreen(new GameScreen(game));				
			}
			
			@Override
			public void dispose() {
				gemserkLogo.dispose();
			}
			
		});
		
	}

}
