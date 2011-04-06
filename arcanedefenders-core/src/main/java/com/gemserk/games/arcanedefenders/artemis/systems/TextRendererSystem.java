package com.gemserk.games.arcanedefenders.artemis.systems;

import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.gemserk.games.arcanedefenders.artemis.components.TextComponent;

public class TextRendererSystem extends EntitySystem {

	private final SpriteBatch spriteBatch;

	@SuppressWarnings("unchecked")
	public TextRendererSystem(SpriteBatch spriteBatch) {
		super(TextComponent.class);
		this.spriteBatch = spriteBatch;
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		for (int i = 0; i < entities.size(); i++) {

			Entity entity = entities.get(i);
			TextComponent textComponent = entity.getComponent(TextComponent.class);

			if (textComponent == null)
				continue;

			BitmapFont font = textComponent.getFont();
			String text = textComponent.getText();
			Vector2 position = textComponent.getPosition();

			// TextBounds bounds = font.getBounds(text);

			font.setScale(0.5f, 0.5f);
			font.setColor(textComponent.getColor());
			font.draw(spriteBatch, text, position.x, position.y);

		}
	}

	@Override
	public void initialize() {

	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}
}