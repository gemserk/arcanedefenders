package com.gemserk.games.arcanedefenders.artemis.systems;

import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;
import com.gemserk.games.arcanedefenders.artemis.components.SpawnerComponent;
import com.gemserk.games.arcanedefenders.artemis.entities.EntityTemplate;

public class SpawnerSystem extends EntitySystem {

	@SuppressWarnings("unchecked")
	public SpawnerSystem() {
		super(SpawnerComponent.class);
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		for (int i = 0; i < entities.size(); i++) {

			Entity entity = entities.get(i);

			SpawnerComponent spawnerComponent = entity.getComponent(SpawnerComponent.class);

			int spawnTime = spawnerComponent.getSpawnTime();
			EntityTemplate entityTemplate = spawnerComponent.getEntityTemplate();
			spawnTime -= world.getDelta();
			
			if (spawnTime <= 0) {
				
				System.out.println("SPAWN!!");
				
				entityTemplate.build();
				
				spawnTime = 5000; 

			}

			spawnerComponent.setSpawnTime(spawnTime);
			
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