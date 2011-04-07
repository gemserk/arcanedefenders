package com.gemserk.games.arcanedefenders.artemis.systems;

import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.gemserk.commons.artemis.components.MovementComponent;
import com.gemserk.commons.artemis.components.SpatialComponent;
import com.gemserk.games.arcanedefenders.ElementType;
import com.gemserk.games.arcanedefenders.artemis.components.ElementTypeComponent;
import com.gemserk.games.arcanedefenders.artemis.entities.Tags;

public class AllGameLogicSystem extends EntitySystem {
	
	@SuppressWarnings("unchecked")
	public AllGameLogicSystem() {
		super();
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		
		processInput();
		processRPS();
		
	}

	protected void processInput() {
		if (!Gdx.input.justTouched())
			return;
		
		ImmutableBag<Entity> entities = world.getGroupManager().getEntities(Tags.Defender);
		
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
			
			int nextIndex = getNextElementTypeIndex(elementTypeComponent.getElementType());
			elementTypeComponent.setElementType(ElementType.values()[nextIndex]);
			
		}
	}

	protected void processRPS() {
		
		ImmutableBag<Entity> defenders = world.getGroupManager().getEntities(Tags.Defender);
		ImmutableBag<Entity> fallingElements = world.getGroupManager().getEntities(Tags.FallingElement);
		
		if (fallingElements == null || fallingElements.size() <= 0)
			return;
		
		if (defenders == null || defenders.size() <= 0)
			return;
		
		for (int i = 0; i < defenders.size(); i++) {
			
			Entity defender = defenders.get(i);
			
			SpatialComponent spatialComponent = defender.getComponent(SpatialComponent.class);
			ElementTypeComponent elementTypeComponent = defender.getComponent(ElementTypeComponent.class);
			
			for (int j = 0; j < fallingElements.size(); j++) {
				
				Entity fallingElement = fallingElements.get(j);
				
				SpatialComponent fallingElementSpatialComponent = fallingElement.getComponent(SpatialComponent.class);
				ElementTypeComponent fallingElementelementTypeComponent = fallingElement.getComponent(ElementTypeComponent.class);
				
				if (fallingElementSpatialComponent.getPosition().dst(spatialComponent.getPosition()) > 32) 
					continue;

				// process RPS
				
				ElementType defenderElementType = elementTypeComponent.getElementType();
				ElementType fallingElementElementType = fallingElementelementTypeComponent.getElementType();

				int compare = compareElementTypes(defenderElementType, fallingElementElementType);
				
				if (compare == -1) {
					world.deleteEntity(defender);
				} else if (compare == 1) { 
					world.deleteEntity(fallingElement);
				} else {
					
					MovementComponent movementComponent = fallingElement.getComponent(MovementComponent.class);
					movementComponent.getVelocity().set(0,0);
					
				}
				
			}
			
			
		}
	}
	
	private int compareElementTypes(ElementType defenderElementType, ElementType fallingElementElementType) {
		
		if (defenderElementType.equals(fallingElementElementType))
			return 0;

		if (fallingElementElementType.equals(ElementType.values()[getNextElementTypeIndex(defenderElementType)]))
			return -1;

		return 1;
	}
	
	private int getNextElementTypeIndex(ElementType elementType) {
		int index = getElementTypeIndex(elementType);
		int nextIndex = index +1;
		if (nextIndex >= ElementType.values().length) 
			nextIndex = 0;
		return nextIndex;
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