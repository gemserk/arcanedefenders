package com.gemserk.games.arcanedefenders.artemis.components;

import com.artemis.Component;
import com.gemserk.commons.values.IntValue;
import com.gemserk.componentsengine.properties.Property;
import com.gemserk.games.arcanedefenders.artemis.entities.EntityTemplate;

public class SpawnerComponent extends Component {
	
	private final Property<IntValue> spawnTime;
	
	private final Property<EntityTemplate> entityTemplate;
	
	public int getSpawnTime() {
		return spawnTime.get().value;
	}
	
	public void setSpawnTime(int spawnTime) {
		this.spawnTime.get().value = spawnTime;
	}
	
	public EntityTemplate getEntityTemplate() {
		return entityTemplate.get();
	}

	public SpawnerComponent(Property<IntValue> spawnTime, Property<EntityTemplate> entityTemplate) {
		this.spawnTime = spawnTime;
		this.entityTemplate = entityTemplate;
	}

}
