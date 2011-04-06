package com.gemserk.games.arcanedefenders.artemis.entities;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.gemserk.componentsengine.properties.AbstractProperty;
import com.gemserk.componentsengine.properties.SimpleProperty;
import com.gemserk.games.arcanedefenders.artemis.components.TextComponent;

public class EntityFactory {

	private final World world;
	
	public EntityFactory(World world) {
		this.world = world;
	}

	public Entity fpsEntity(BitmapFont font) {
		Entity entity = world.createEntity();
		entity.addComponent(new TextComponent( //
				new AbstractProperty<String>() {
					@Override
					public String get() {
						return "FPS: " + Gdx.graphics.getFramesPerSecond();
					}
				}, //
				new SimpleProperty<BitmapFont>(font), //
				new SimpleProperty<Vector2>(new Vector2(10, Gdx.graphics.getHeight() - 20)), //
				new SimpleProperty<Color>(new Color(1f, 1f, 1f, 1f)) //
		));

		entity.refresh();
		return entity;
	}

}
