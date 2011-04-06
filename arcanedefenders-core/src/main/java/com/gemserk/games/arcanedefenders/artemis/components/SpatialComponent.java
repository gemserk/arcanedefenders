package com.gemserk.games.arcanedefenders.artemis.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;
import com.gemserk.componentsengine.properties.Property;

public class SpatialComponent extends Component {
	
	private final Property<Vector2> position;
	
	private final Property<Vector2> size;
	
	public Vector2 getPosition() {
		return position.get();
	}
	
	public Vector2 getSize() {
		return size.get();
	}
	
	public SpatialComponent(Property<Vector2> position, Property<Vector2> size) {
		this.position = position;
		this.size = size;
	}

}
