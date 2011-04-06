package com.gemserk.games.arcanedefenders.artemis.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gemserk.componentsengine.properties.Property;

public class SpriteComponent extends Component {
	
	private final Property<Sprite> sprite;
	
	public Sprite getSprite() {
		return sprite.get();
	}

	public SpriteComponent(Property<Sprite> sprite) {
		this.sprite = sprite;
	}

}
