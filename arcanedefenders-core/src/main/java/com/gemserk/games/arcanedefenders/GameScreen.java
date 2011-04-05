package com.gemserk.games.arcanedefenders;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.gemserk.games.arcanedefenders.entities.Defender;

public class GameScreen extends ScreenAdapter {

	private final Game game;

	ArrayList<Defender> defenders;

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

		defenders = new ArrayList<Defender>();

		int xStart = part - midPart;

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

			defenders.add(defender);

			xStart += part;

		}

	}

	@Override
	public void render(float delta) {

		int centerX = Gdx.graphics.getWidth() / 2;
		int centerY = Gdx.graphics.getHeight() / 2;

		Gdx.graphics.getGL10().glClear(GL10.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();

		for (int i = 0; i < defenders.size(); i++) {

			Defender defender = defenders.get(i);

			Vector2 position = defender.getPosition();
			Vector2 size = defender.getSize();
			Sprite sprite = defender.getSprite();

			sprite.setSize(size.x, size.y);
			sprite.setPosition(position.x - size.x / 2, position.y - size.y / 2);
			sprite.draw(spriteBatch);

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