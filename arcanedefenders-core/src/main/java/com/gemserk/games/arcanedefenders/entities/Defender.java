package com.gemserk.games.arcanedefenders.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.gemserk.games.arcanedefenders.ElementType;

public class Defender {

	Vector2 position;

	Vector2 size;

	Sprite sprite;

	ElementType type;

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

	public Vector2 getSize() {
		return size;
	}

	public ElementType getType() {
		return type;
	}

	public void setType(ElementType type) {
		this.type = type;
	}

	public Defender() {

	}

}