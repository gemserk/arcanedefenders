package com.gemserk.games.arcanedefenders;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.gemserk.games.arcanedefenders.ArcaneDefendersGame;

public class ArcaneDefendersAndroidApplication extends AndroidApplication  {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initialize(new ArcaneDefendersGame(), false);
	}

}