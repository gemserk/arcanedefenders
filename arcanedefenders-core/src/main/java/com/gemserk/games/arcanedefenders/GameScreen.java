package com.gemserk.games.arcanedefenders;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.gemserk.games.arcanedefenders.entities.Defender;
import com.gemserk.games.arcanedefenders.entities.FallingElement;
import com.gemserk.games.arcanedefenders.entities.Spawner;

public class GameScreen extends ScreenAdapter {

	private final Game game;

	World world = new World();

	private SpriteBatch spriteBatch;

	public GameScreen(Game game) {
		this.game = game;

		spriteBatch = new SpriteBatch();

		Texture texture = new Texture(Gdx.files.internal("data/mage-512x512.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		int n = 3;

		int part = width / n;
		int midPart = part / 2;

		System.out.println(part);
		System.out.println(midPart);

		int xStart = part - midPart;

		Random random = new Random();

		for (int i = 0; i < n; i++) {

			int x = xStart;
			int y = 74;

			System.out.println(x);

			Defender defender = new Defender();

			Sprite sprite = new Sprite(texture);
			sprite.setOrigin(texture.getWidth() / 2, texture.getHeight() / 2);

			defender.setPosition(new Vector2(x, y));
			defender.setSize(new Vector2(128, 128));
			defender.setSprite(sprite);

			world.defenders.add(defender);

			Spawner spawner = new Spawner();
			spawner.setPosition(new Vector2(x, height + 10));
			spawner.setWorld(world);
			spawner.setTexture(texture);
			spawner.setRandom(random);
			spawner.resetSpawnTimer();

			world.spawners.add(spawner);

			xStart += part;

		}

	}

	@Override
	public void render(float delta) {

		int centerX = Gdx.graphics.getWidth() / 2;
		int centerY = Gdx.graphics.getHeight() / 2;

		Gdx.graphics.getGL10().glClear(GL10.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();

		for (int i = 0; i < world.defenders.size(); i++) {

			Defender defender = world.defenders.get(i);

			Vector2 position = defender.getPosition();
			Vector2 size = defender.getSize();

			Sprite sprite = defender.getSprite();

			sprite.setSize(size.x, size.y);
			sprite.setPosition(position.x - size.x / 2, position.y - size.y / 2);
			sprite.draw(spriteBatch);

		}

		for (int i = 0; i < world.spawners.size(); i++) {

			Spawner spawner = world.spawners.get(i);
			spawner.update(delta);

		}

		for (int i = 0; i < world.fallingElements.size(); i++) {

			FallingElement fallingElement = world.fallingElements.get(i);

			Vector2 position = fallingElement.getPosition();
			Vector2 size = fallingElement.getSize();

			Sprite sprite = fallingElement.getSprite();

			sprite.setSize(size.x, -size.y);
			sprite.setPosition(position.x - size.x / 2, position.y - size.y / 2);
			sprite.draw(spriteBatch);

			position.y -= 50 * delta;

		}

		spriteBatch.end();
	}

	@Override
	public void show() {

	}

	@Override
	public void dispose() {

	}

}