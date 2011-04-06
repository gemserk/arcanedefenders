package com.gemserk.games.arcanedefenders.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.gemserk.games.arcanedefenders.ElementType;
import com.gemserk.games.arcanedefenders.World;
import com.gemserk.games.arcanedefenders.artemis.entities.EntityFactory;

public class Spawner {

	private Vector2 position;

	private World world;

	private Texture texture;

	private int timer;

	private Random random;

	private int maxTime;

	private int minTime;

	private final com.artemis.World artemisWorld;

	private final EntityFactory entityFactory;

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public void setRandom(Random random) {
		this.random = random;
	}

	public Spawner(com.artemis.World artemisWorld, EntityFactory entityFactory) {

		this.artemisWorld = artemisWorld;
		this.entityFactory = entityFactory;
		
		minTime = 3000;
		maxTime = 8000;

	}

	public void resetSpawnTimer() {
		timer = random.nextInt(maxTime - minTime) + minTime;
	}

	public void update(float delta) {

		timer -= (int) (delta * 1000);

		if (timer < 0) {

			Sprite sprite = new Sprite(texture);
			sprite.setOrigin(texture.getWidth() / 2, texture.getHeight() / 2);

			Vector2 position = new Vector2(this.position);
			Vector2 size = new Vector2(64, 64);
			
			ElementType type = randomElementType();
			
			entityFactory.fallingElementEntity(position, size, sprite, new Vector2(0f, -50f), type);
			entityFactory.typeEntity(position, type);

			resetSpawnTimer();

		}

	}
	
	public ElementType randomElementType() {
		ElementType[] values = ElementType.values();
		return values[random.nextInt(values.length)];
	}

}