package com.gemserk.games.arcanedefenders;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.gemserk.games.arcanedefenders.artemis.entities.EntityFactory;
import com.gemserk.games.arcanedefenders.artemis.systems.MovementSystem;
import com.gemserk.games.arcanedefenders.artemis.systems.SpriteRendererSystem;
import com.gemserk.games.arcanedefenders.artemis.systems.TextRendererSystem;
import com.gemserk.games.arcanedefenders.entities.Defender;
import com.gemserk.games.arcanedefenders.entities.Spawner;

public class GameScreen extends ScreenAdapter {

	private final Game game;

	World world = new World();

	private SpriteBatch spriteBatch;

	private BitmapFont font;

	private float angle = 0f;

	private com.artemis.World artemisWorld;

	private TextRendererSystem textRendererSystem;

	private SpriteRendererSystem spriteRendererSystem;

	private MovementSystem movementSystem;

	public GameScreen(Game game) {
		this.game = game;

		spriteBatch = new SpriteBatch();

		Texture texture = new Texture(Gdx.files.internal("data/mage-512x512.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture fontTexture = new Texture(Gdx.files.internal("data/font.png"));
		fontTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Sprite fontSprite = new Sprite(fontTexture);
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"), fontSprite, false);

		textRendererSystem = new TextRendererSystem(spriteBatch);
		spriteRendererSystem = new SpriteRendererSystem(spriteBatch);
		movementSystem = new MovementSystem();

		artemisWorld = new com.artemis.World();
		artemisWorld.getSystemManager().setSystem(textRendererSystem);
		artemisWorld.getSystemManager().setSystem(spriteRendererSystem);
		artemisWorld.getSystemManager().setSystem(movementSystem);
		artemisWorld.getSystemManager().initializeAll();

		EntityFactory entityFactory = new EntityFactory(artemisWorld, font);
		entityFactory.fpsEntity();

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

			defender.setType(ElementType.Paper);

			world.defenders.add(defender);

			Spawner spawner = new Spawner(artemisWorld, entityFactory);
			spawner.setPosition(new Vector2(x, height - 50));
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

		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();

		int centerX = width / 2;
		int centerY = height / 2;

		Gdx.graphics.getGL10().glClear(GL10.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();

		for (int i = 0; i < world.defenders.size(); i++) {

			Defender defender = world.defenders.get(i);

			Vector2 position = defender.getPosition();
			Vector2 size = defender.getSize();

			Sprite sprite = defender.getSprite();

			sprite.setOrigin(size.x / 2, size.y / 2);

			sprite.setRotation(angle);
			sprite.setSize(size.x, size.y);
			sprite.setPosition(position.x - size.x / 2, position.y - size.y / 2);
			sprite.draw(spriteBatch);

			String renderType = getStringForElementType(defender.getType());

			font.setScale(0.5f, 0.5f);
			font.setColor(1f, 1f, 1f, 1f);
			font.draw(spriteBatch, renderType, position.x - font.getSpaceWidth() * 0.25f, position.y + font.getCapHeight() * 0.25f);

			angle += 0.1f;

		}

		for (int i = 0; i < world.spawners.size(); i++) {

			Spawner spawner = world.spawners.get(i);
			spawner.update(delta);

		}

		artemisWorld.loopStart();
		artemisWorld.setDelta((int)(delta * 1000));

		movementSystem.process();
		spriteRendererSystem.process();
		textRendererSystem.process();

		spriteBatch.end();
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

	@Override
	public void show() {

	}

	@Override
	public void dispose() {

	}

}