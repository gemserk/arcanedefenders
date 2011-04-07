package com.gemserk.games.arcanedefenders.artemis.entities;

import java.util.Random;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.gemserk.commons.artemis.components.ChildComponent;
import com.gemserk.commons.artemis.components.MovementComponent;
import com.gemserk.commons.artemis.components.SpatialComponent;
import com.gemserk.commons.artemis.components.SpriteComponent;
import com.gemserk.commons.artemis.components.TextComponent;
import com.gemserk.commons.values.FloatValue;
import com.gemserk.commons.values.IntValue;
import com.gemserk.componentsengine.properties.AbstractProperty;
import com.gemserk.componentsengine.properties.Property;
import com.gemserk.componentsengine.properties.SimpleProperty;
import com.gemserk.componentsengine.timers.CountDownTimer;
import com.gemserk.componentsengine.timers.Timer;
import com.gemserk.games.arcanedefenders.ElementType;
import com.gemserk.games.arcanedefenders.artemis.components.ElementTypeComponent;
import com.gemserk.games.arcanedefenders.artemis.components.SpawnerComponent;

public class EntityFactory {

	private final World world;
	
	private final Random random = new Random();
	
	public EntityFactory(World world) {
		this.world = world;
	}
	
	public Entity defender(Vector2 position, Vector2 size, Sprite sprite, Property<ElementType> elementType) {
		Entity entity = world.createEntity();

		entity.addComponent(new SpriteComponent(new SimpleProperty<Sprite>(sprite), new SimpleProperty<IntValue>(new IntValue(0))));
		entity.addComponent(new SpatialComponent(new SimpleProperty<Vector2>(position), new SimpleProperty<Vector2>(size), new SimpleProperty<FloatValue>(new FloatValue(0f))));
		entity.addComponent(new ElementTypeComponent(elementType));

		entity.setGroup(Tags.Defender);
		
		entity.refresh();
		
		return entity;
	}

	public Entity fallingElementEntity(Vector2 position, Vector2 size, Sprite sprite, Vector2 velocity, Property<ElementType> elementType) {
		Entity entity = world.createEntity();

		float angularVelocityF = random.nextFloat() * 20f + 20f;
		
		if (random.nextBoolean())
			angularVelocityF = -angularVelocityF;
		
		FloatValue angularVelocity = new FloatValue(angularVelocityF);
		
		entity.addComponent(new SpriteComponent(new SimpleProperty<Sprite>(sprite),new SimpleProperty<IntValue>(new IntValue(1))));
		entity.addComponent(new SpatialComponent(
				new SimpleProperty<Vector2>(position), 
				new SimpleProperty<Vector2>(size), 
				new SimpleProperty<FloatValue>(new FloatValue(random.nextFloat()*360f))));
		entity.addComponent(new MovementComponent(new SimpleProperty<Vector2>(velocity), new SimpleProperty<FloatValue>(angularVelocity)));
		entity.addComponent(new ElementTypeComponent(elementType));
		
		entity.setGroup(Tags.FallingElement);
		
		entity.refresh();
		
		return entity;
	}
	
	public Entity spawner(EntityTemplate entityTemplate) {
		Entity entity = world.createEntity();
		
		entity.addComponent(new SpawnerComponent(new SimpleProperty<Timer>(new CountDownTimer(0, false)),
				new SimpleProperty<IntValue>(new IntValue(3000)), 
				new SimpleProperty<IntValue>(new IntValue(7000)),
				new SimpleProperty<EntityTemplate>(entityTemplate)));
		entity.refresh();
		
		return entity;
	}
	
	public Entity typeEntity(Vector2 position, final Property<ElementType> elementType, Property<Entity> owner, Property<BitmapFont> font) {
		Entity entity = world.createEntity();

		Vector2 size = new Vector2(0.5f, 0.5f);
		
		entity.addComponent(new SpatialComponent(new SimpleProperty<Vector2>(position), new SimpleProperty<Vector2>(size), new SimpleProperty<FloatValue>(new FloatValue(0f))));
		entity.addComponent(new TextComponent( //
				new AbstractProperty<String>() {
					@Override
					public String get() {
						return getStringForElementType(elementType.get());
					}
				}, //
				font, //
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
