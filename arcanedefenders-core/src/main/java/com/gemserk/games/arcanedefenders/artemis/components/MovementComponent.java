package com.gemserk.games.arcanedefenders.artemis.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;
import com.gemserk.componentsengine.properties.Property;

public class MovementComponent extends Component {
	
	private final Property<Vector2> velocity;
	
	public Vector2 getVelocity() {
		return velocity.get();
	}

	public MovementComponent(Property<Vector2> velocity) {
		this.velocity = velocity;
	}

}
