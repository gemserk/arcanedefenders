package com.gemserk.games.arcanedefenders.artemis.entities;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.gemserk.commons.values.IntValue;
import com.gemserk.componentsengine.properties.AbstractProperty;
import com.gemserk.componentsengine.properties.Property;
import com.gemserk.componentsengine.properties.SimpleProperty;
import com.gemserk.componentsengine.timers.CountDownTimer;
import com.gemserk.componentsengine.timers.Timer;
import com.gemserk.games.arcanedefenders.ElementType;
import com.gemserk.games.arcanedefenders.artemis.components.ElementTypeComponent;
import com.gemserk.games.arcanedefenders.artemis.components.MovementComponent;
import com.gemserk.games.arcanedefenders.artemis.components.ChildComponent;
import com.gemserk.games.arcanedefenders.artemis.components.SpatialComponent;
import com.gemserk.games.arcanedefenders.artemis.components.SpawnerComponent;
import com.gemserk.games.arcanedefenders.artemis.components.SpriteComponent;
import com.gemserk.games.arcanedefenders.artemis.components.TextComponent;

public class EntityFactory {

	private final World world;
	
	private final BitmapFont defaultFont;
	
	public EntityFactory(World world, BitmapFont defaultFont) {
		this.world = world;
		this.defaultFont = defaultFont;
	}

	public Entity fpsEntity() {
		Entity entity = world.createEntity();
		
		Vector2 position = new Vector2(10, Gdx.graphics.getHeight() - 20);
		Vector2 value = new Vector2(0.5f, 0.5f);
		
		entity.addComponent(new TextComponent( //
				new AbstractProperty<String>() {
					@Override
					public String get() {
						return "FPS: " + Gdx.graphics.getFramesPerSecond();
					}
				}, //
				new SimpleProperty<BitmapFont>(defaultFont), //
				new SimpleProperty<Color>(new Color(1f, 1f, 1f, 1f)) //
		));
		
		entity.addComponent(new SpatialComponent(new SimpleProperty<Vector2>(position), new SimpleProperty<Vector2>(value)));
		
		entity.refresh();
		return entity;
	}
	
	public Entity defender(Vector2 position, Vector2 size, Sprite sprite, Property<ElementType> elementType) {
		Entity entity = world.createEntity();

		entity.addComponent(new SpriteComponent(new SimpleProperty<Sprite>(sprite)));
		entity.addComponent(new SpatialComponent(new SimpleProperty<Vector2>(position), new SimpleProperty<Vector2>(size)));
		entity.addComponent(new ElementTypeComponent(elementType));

		entity.setGroup(Tags.Defender);
		
		entity.refresh();
		
		return entity;
	}

	public Entity fallingElementEntity(Vector2 position, Vector2 size, Sprite sprite, Vector2 velocity, Property<ElementType> elementType) {
		Entity entity = world.createEntity();

		entity.addComponent(new SpriteComponent(new SimpleProperty<Sprite>(sprite)));
		entity.addComponent(new SpatialComponent(new SimpleProperty<Vector2>(position), new SimpleProperty<Vector2>(size)));
		entity.addComponent(new MovementComponent(new SimpleProperty<Vector2>(velocity)));
		entity.addComponent(new ElementTypeComponent(elementType));
		
		entity.setGroup(Tags.FallingElement);
		
		entity.refresh();
		
		return entity;
	}
	
	public Entity spawner(EntityTemplate entityTemplate) {
		Entity entity = world.createEntity();
		
		entity.addComponent(new SpawnerComponent(new SimpleProperty<Timer>(new CountDownTimer(0, false)),
				new SimpleProperty<IntValue>(new IntValue(3000)), 
				new SimpleProperty<IntValue>(new IntValue(8000)),
				new SimpleProperty<EntityTemplate>(entityTemplate)));
		entity.refresh();
		
		return entity;
	}
	
	public Entity typeEntity(Vector2 position, final Property<ElementType> elementType, Property<Entity> owner) {
		Entity entity = world.createEntity();

		Vector2 size = new Vector2(0.5f, 0.5f);
		
		entity.addComponent(new SpatialComponent(new SimpleProperty<Vector2>(position), new SimpleProperty<Vector2>(size)));
		entity.addComponent(new TextComponent( //
				new AbstractProperty<String>() {
					@Override
					public String get() {
						return getStringForElementType(elementType.get());
					}
				}, //
				new SimpleProperty<BitmapFont>(defaultFont), //
				new SimpleProperty<Color>(new Color(1f, 1f, 1f, 1f)) //
		));
		entity.addComponent(new ChildComponent(owner));
		
		entity.setGroup(Tags.ElementTypeDebugLabel);
		
		entity.refresh();
		return entity;
	}
	
	protected String getStringForElementType(ElementType type) {
		switch (type) {
		case Rock:
			return "R";
		case Paper:
			return "P";
		default:
			return "S";
		}
	}

}
