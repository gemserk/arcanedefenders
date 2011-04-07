package com.gemserk.games.arcanedefenders.artemis.systems;

import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gemserk.games.arcanedefenders.artemis.components.SpriteComponent;

public class SpriteRendererSystem extends EntitySystem {

	private final SpriteBatch spriteBatch;

	@SuppressWarnings("unchecked")
	public SpriteRendererSystem(SpriteBatch spriteBatch) {
		super(SpriteComponent.class);
		this.spriteBatch = spriteBatch;
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			SpriteComponent spriteComponent = entity.getComponent(SpriteComponent.class);
			Sprite sprite = spriteComponent.getSprite();
			sprite.draw(spriteBatch);
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