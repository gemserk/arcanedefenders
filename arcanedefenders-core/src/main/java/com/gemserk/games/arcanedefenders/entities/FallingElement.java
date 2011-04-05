package com.gemserk.games.arcanedefenders.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class FallingElement {
	
	Vector2 position;
	
	Vector2 size;
	
	Sprite sprite;
	
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
	
	public FallingElement() {

	}
	
}