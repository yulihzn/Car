package com.yuri.car.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.yuri.car.CarGame;
import com.yuri.car.stage.TopStage;

/**
 * Created by BanditCat on 2016/12/26.
 */

public class MainScreen extends BaseScreen {
    private TopStage topStage;
    public MainScreen(CarGame carGame) {
        super(carGame);
        topStage = new TopStage(this);
        Gdx.input.setInputProcessor(topStage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render(delta);
        topStage.act(delta);
        topStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        topStage.getViewport().update(width,height,true);
    }
}
