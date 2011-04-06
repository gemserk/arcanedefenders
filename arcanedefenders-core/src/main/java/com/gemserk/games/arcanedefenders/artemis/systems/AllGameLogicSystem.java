package com.gemserk.games.arcanedefenders.artemis.systems;

import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.gemserk.games.arcanedefenders.ElementType;
import com.gemserk.games.arcanedefenders.artemis.components.ElementTypeComponent;
import com.gemserk.games.arcanedefenders.artemis.components.SpatialComponent;
import com.gemserk.games.arcanedefenders.artemis.entities.Tags;

public class AllGameLogicSystem extends EntitySystem {
	
	@SuppressWarnings("unchecked")
	public AllGameLogicSystem() {
		super();
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		
		if (!Gdx.input.justTouched())
			return;
		
		entities = world.getGroupManager().getEntities(Tags.Defender);
		
		int x = Gdx.input.getX();
		int y = Gdx.graphics.getHeight() - Gdx.input.getY();
		
		for (int i = 0; i < entities.size(); i++) {
			
			Entity entity = entities.get(i);
			
			SpatialComponent spatialComponent = entity.getComponent(SpatialComponent.class);
			ElementTypeComponent elementTypeComponent = entity.getComponent(ElementTypeComponent.class);
			
			Vector2 position = spatialComponent.getPosition();
			
			float xDistance = Math.abs(x - position.x);
			float yDistance = Math.abs(y - position.y);
			
			Vector2 size = spatialComponent.getSize();
			
			if (xDistance > size.x)
				continue;

			if (yDistance > size.y)
				continue;
			
			int index = getElementTypeIndex(elementTypeComponent.getElementType());
			int nextIndex = index +1;
			if (nextIndex >= ElementType.values().length) 
				nextIndex = 0;
			elementTypeComponent.setElementType(ElementType.values()[nextIndex]);
			
		}
	}
	
	private int getElementTypeIndex(ElementType elementType) {
		ElementType[] values = ElementType.values();
		for (int i = 0; i < values.length; i++) {
			if (values[i].equals(elementType))
				return i;
		}
		return 0;
	}

	@Override
	public void initialize() {

	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}
}