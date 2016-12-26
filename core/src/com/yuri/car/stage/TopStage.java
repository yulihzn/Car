package com.yuri.car.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.yuri.car.CarGame;
import com.yuri.car.screen.MainScreen;
import com.yuri.car.ui.GroupHorizontalInTurn;

/**
 * Created by BanditCat on 2016/12/26.
 */

public class TopStage extends Stage {
    private MainScreen mainScreen;
    private GroupHorizontalInTurn group_sky;
    private GroupHorizontalInTurn group_bg;
    private GroupHorizontalInTurn group_bg1;
    private GroupHorizontalInTurn group_road;
    private GroupHorizontalInTurn group_car2;
    private GroupHorizontalInTurn group_car3;
    private GroupHorizontalInTurn group_car4;
    private Texture texture_bg,texture_bg1,texture_road,texture_sky;
    private TextureAtlas textureAtlas;
    private Image car1,car2,car3,car4;
    private int[]carpos={720-240-120,720-240-200,720-240-100,720-240-180};
    private int[]carz = {98,100,97,99};

    public TopStage(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        setViewport(new FitViewport(CarGame.worldWidth,CarGame.worldHeight));
        group_sky = new GroupHorizontalInTurn(50);
        group_bg1 = new GroupHorizontalInTurn(4);
        group_bg = new GroupHorizontalInTurn(2);
        group_road = new GroupHorizontalInTurn(2);
        group_car2 = new GroupHorizontalInTurn(20);
        group_car3 = new GroupHorizontalInTurn(25);
        group_car4 = new GroupHorizontalInTurn(30);
        texture_bg = new Texture(Gdx.files.internal("images/bg.png"));
        texture_bg1 = new Texture(Gdx.files.internal("images/bg1.png"));
        texture_road = new Texture(Gdx.files.internal("images/road.png"));
        texture_sky = new Texture(Gdx.files.internal("images/sky.png"));

        group_sky.addActor(new Image(texture_sky));
        group_bg.addActor(new Image(texture_bg));
        group_bg1.addActor(new Image(texture_bg1));
        group_road.addActor(new Image(texture_road));
        group_sky.addActor(new Image(texture_sky));
        group_bg.addActor(new Image(texture_bg));
        group_bg1.addActor(new Image(texture_bg1));
        group_road.addActor(new Image(texture_road));

        group_sky.setPosition(0, 720-80);
        group_bg.setPosition(0, 720-240);
        group_bg1.setPosition(0, 720-240);
        group_road.setPosition(0, 720-240-240);
        group_sky.reset();
        group_bg.reset();
        group_bg1.reset();
        group_road.reset();
        addActor(group_sky);
        addActor(group_bg1);
        addActor(group_bg);
        addActor(group_road);
        addCar();

    }
    public void addCar(){
        textureAtlas = new TextureAtlas(Gdx.files.internal("images/car.pack"));
        car1 = new Image(textureAtlas.findRegion("car1"));
        car2 = new Image(textureAtlas.findRegion("car2"));
        car3 = new Image(textureAtlas.findRegion("car3"));
        car4 = new Image(textureAtlas.findRegion("car4"));
        car1.setZIndex(103);
        car2.setZIndex(105);
        car3.setZIndex(102);
        car4.setZIndex(104);
        car1.setPosition(400,carpos[0]);
        group_car2.addActor(car2);
        group_car2.addActor(getEmptyActor());
        group_car2.setPosition(0,720-240-200);
        group_car2.reset();
        group_car2.setZIndex(100);
        group_car3.addActor(car3);
        group_car3.addActor(getEmptyActor());
        group_car3.setPosition(0,720-240-100);
        group_car3.reset();
        group_car3.setZIndex(97);
        group_car4.addActor(car4);
        group_car4.addActor(getEmptyActor());
        group_car4.setPosition(0,720-240-180);
        group_car4.reset();
        group_car4.setZIndex(99);
        addActor(group_car3);
        addActor(car1);
        addActor(group_car4);
        addActor(group_car2);

    }
    private Actor getEmptyActor(){
        Actor emptyActor = new Actor();
        emptyActor.setBounds(0,0,1280,0);
        return emptyActor;
    }
    private GroupHorizontalInTurn car;
    @Override
    public void act() {
        super.act();
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        texture_bg.dispose();
        texture_bg1.dispose();
        texture_road.dispose();
        texture_sky.dispose();
    }
}
