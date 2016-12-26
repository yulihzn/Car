package com.yuri.car;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.yuri.car.screen.MainScreen;

public class CarGame extends Game {
	public static final int worldWidth = 1280;
	public static final int worldHeight = 720;
	private MainScreen mainScreen;
	
	@Override
	public void create () {
		mainScreen = new MainScreen(this);
		setScreen(mainScreen);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		mainScreen.dispose();
	}
}
