package com.gemserk.games.arcanedefenders.artemis.components;

import com.artemis.Component;
import com.gemserk.commons.values.IntValue;
import com.gemserk.componentsengine.properties.Property;
import com.gemserk.componentsengine.timers.Timer;
import com.gemserk.games.arcanedefenders.artemis.entities.EntityTemplate;

public class SpawnerComponent extends Component {
	
	private final Property<Timer> spawnTimer;
	
	private final Property<EntityTemplate> entityTemplate;

	private final Property<IntValue> minTime;

	private final Property<IntValue> maxTime;
	
	public int getMinTime() {
		return minTime.get().value;
	}

	public int getMaxTime() {
		return maxTime.get().value;
	}

	public Timer getSpawnTimer() {
		return spawnTimer.get();
	}
	
	public void setSpawnTimer(Timer timer) {
		spawnTimer.set(timer);
	}
	
	public EntityTemplate getEntityTemplate() {
		return entityTemplate.get();
	}

	public SpawnerComponent(Property<Timer> spawnTimer, Property<IntValue> minTime, Property<IntValue> maxTime, Property<EntityTemplate> entityTemplate) {
		this.spawnTimer = spawnTimer;
		this.minTime = minTime;
		this.maxTime = maxTime;
		this.entityTemplate = entityTemplate;
	}

}
