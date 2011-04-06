package com.gemserk.games.arcanedefenders;

import java.util.Random;

import com.artemis.Entity;
import com.artemis.World;
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
import com.gemserk.games.arcanedefenders.artemis.entities.EntityTemplate;
import com.gemserk.games.arcanedefenders.artemis.systems.MovementSystem;
import com.gemserk.games.arcanedefenders.artemis.systems.SpawnerSystem;
import com.gemserk.games.arcanedefenders.artemis.systems.SpriteRendererSystem;
import com.gemserk.games.arcanedefenders.artemis.systems.TextRendererSystem;

public class GameScreen extends ScreenAdapter {

	private final Game game;

	private SpriteBatch spriteBatch;

	private BitmapFont font;

	private com.artemis.World world;

	private TextRendererSystem textRendererSystem;

	private SpriteRendererSystem spriteRendererSystem;

	private MovementSystem movementSystem;

	private SpawnerSystem spawnerSystem;

	public GameScreen(Game game) {
		this.game = game;

		spriteBatch = new SpriteBatch();

		final Texture texture = new Texture(Gdx.files.internal("data/mage-512x512.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Texture fontTexture = new Texture(Gdx.files.internal("data/font.png"));
		fontTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Sprite fontSprite = new Sprite(fontTexture);
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"), fontSprite, false);

		textRendererSystem = new TextRendererSystem(spriteBatch);
		spriteRendererSystem = new SpriteRendererSystem(spriteBatch);
		movementSystem = new MovementSystem();
		spawnerSystem = new SpawnerSystem();

		world = new World();
		world.getSystemManager().setSystem(textRendererSystem);
		world.getSystemManager().setSystem(spriteRendererSystem);
		world.getSystemManager().setSystem(movementSystem);
		world.getSystemManager().setSystem(spawnerSystem);
		world.getSystemManager().initializeAll();

		final EntityFactory entityFactory = new EntityFactory(world, font);
		entityFactory.fpsEntity();

		int width = Gdx.graphics.getWidth();
		final int height = Gdx.graphics.getHeight();
		int n = 3;

		int part = width / n;
		int midPart = part / 2;

		System.out.println(part);
		System.out.println(midPart);

		int xStart = part - midPart;

		final Random random = new Random();

		for (int i = 0; i < n; i++) {

			final int x = xStart;
			final int y = 74;
			
			// builds the defenders
			{
				Sprite sprite = new Sprite(texture);
				sprite.setOrigin(texture.getWidth() / 2, texture.getHeight() / 2);

				final Vector2 position = new Vector2(x, y);
				Vector2 size = new Vector2(128, 128);
				ElementType type = ElementType.Paper;

				entityFactory.defender(position, size, sprite, type);
				entityFactory.typeEntity(position, type);
			}

			// build the falling elements spawners
			{
				entityFactory.spawner(new EntityTemplate() {
					@Override
					public Entity build() {

						Sprite sprite = new Sprite(texture);
						sprite.setOrigin(texture.getWidth() / 2, texture.getHeight() / 2);

						Vector2 size = new Vector2(64, 64);

						ElementType type = randomElementType();

						Vector2 position = new Vector2(x, height - y);

						entityFactory.fallingElementEntity(position, size, sprite, new Vector2(0f, -50f), type);
						entityFactory.typeEntity(position, type);

						return super.build();
					}

					public ElementType randomElementType() {
						ElementType[] values = ElementType.values();
						return values[random.nextInt(values.length)];
					}

				});
			}

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

		world.loopStart();
		world.setDelta((int) (delta * 1000));

		movementSystem.process();
		spriteRendererSystem.process();
		textRendererSystem.process();
		spawnerSystem.process();

		spriteBatch.end();
	}

	@Override
	public void show() {

	}

	@Override
	public void dispose() {

	}

}