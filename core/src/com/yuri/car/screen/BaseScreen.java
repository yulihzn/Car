package com.yuri.car.screen;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.yuri.car.CarGame;


public abstract class BaseScreen extends InputAdapter implements Screen {
    protected CarGame carGame;

    public BaseScreen(CarGame carGame) {
        this.carGame = carGame;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public CarGame getCarGame() {
        return carGame;
    }

    public void setCarGame(CarGame carGame) {
        this.carGame = carGame;
    }
}
