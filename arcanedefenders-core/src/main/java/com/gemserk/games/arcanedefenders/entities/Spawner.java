package com.gemserk.games.arcanedefenders.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.gemserk.games.arcanedefenders.World;

public class Spawner {

	private Vector2 position;

	private World world;

	private Texture texture;

	private int timer;

	private Random random;

	private int maxTime;

	private int minTime;

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

	public Spawner() {

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

			FallingElement fallingElement = new FallingElement();
			fallingElement.setPosition(new Vector2(this.position));
			fallingElement.setSize(new Vector2(64, 64));
			fallingElement.setSprite(sprite);

			world.fallingElements.add(fallingElement);

			resetSpawnTimer();

		}

	}

}