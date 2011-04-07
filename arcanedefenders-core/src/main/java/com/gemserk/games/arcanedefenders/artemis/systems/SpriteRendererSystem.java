package com.gemserk.games.arcanedefenders.artemis.systems;

import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.gemserk.games.arcanedefenders.artemis.components.SpatialComponent;
import com.gemserk.games.arcanedefenders.artemis.components.SpriteComponent;

public class SpriteRendererSystem extends EntitySystem {

	private final SpriteBatch spriteBatch;

	@SuppressWarnings("unchecked")
	public SpriteRendererSystem(SpriteBatch spriteBatch) {
		super(SpatialComponent.class, SpriteComponent.class);
		this.spriteBatch = spriteBatch;
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		for (int i = 0; i < entities.size(); i++) {

			Entity entity = entities.get(i);

			SpatialComponent spatialComponent = entity.getComponent(SpatialComponent.class);
			SpriteComponent spriteComponent = entity.getComponent(SpriteComponent.class);

			Vector2 position = spatialComponent.getPosition();
			Vector2 size = spatialComponent.getSize();

			Sprite sprite = spriteComponent.getSprite();

			sprite.setRotation(spatialComponent.getAngle());
			sprite.setOrigin(size.x / 2, size.y / 2);
			sprite.setSize(size.x, size.y);
			sprite.setPosition(position.x - size.x / 2, position.y - size.y / 2);
			
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