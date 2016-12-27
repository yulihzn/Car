package com.yuri.car.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.yuri.car.Actor.VehicleImage;
import com.yuri.car.CarGame;
import com.yuri.car.model.Car;
import com.yuri.car.screen.MainScreen;
import com.yuri.car.ui.GroupHorizontalInTurn;

/**
 * Created by BanditCat on 2016/12/26.
 */

public class TopStage extends Stage {
    private MainScreen mainScreen;
    private GroupHorizontalInTurn group_sky;
    private GroupHorizontalInTurn group_far;
    private GroupHorizontalInTurn group_near;
    private GroupHorizontalInTurn group_road;
    private Texture texture_bg,texture_bg1,texture_road,texture_sky;
    private TextureAtlas carAtlas;
    private TextureAtlas bgAtlas;
    private int[]carpos={720-240-120,720-240-200,720-240-180,720-240-100};
    private int[]carz = {100,101,102,103};

    private Touchpad touchpad;

    private Car player;
    private Array<Car> others = new Array<Car>();

    public TopStage(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        setViewport(new FitViewport(CarGame.worldWidth,CarGame.worldHeight));
        addBackground();
        addCar();
        Touchpad.TouchpadStyle style = new Touchpad.TouchpadStyle();
        style.knob = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/knob.png"))));
        style.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/joystick.png"))));
        touchpad = new Touchpad(20,style);
        touchpad.setBounds(20,20,200,200);
        addActor(touchpad);

    }


    private void addBackground(){
        bgAtlas = new TextureAtlas(Gdx.files.internal("images/bg.pack"));
        group_sky = new GroupHorizontalInTurn(50);
        group_far = new GroupHorizontalInTurn(4);
        group_near = new GroupHorizontalInTurn(2);
        group_road = new GroupHorizontalInTurn(2);
        for (int i = 0; i < 8; i++) {
            String index = i+1+"";
            if(i > 5){
                index = i-5+"";
            }
            group_sky.addActor(new Image(bgAtlas.findRegion("sky0"+index)));
            group_far.addActor(new Image(bgAtlas.findRegion("bg_far0"+index)));
            group_near.addActor(new Image(bgAtlas.findRegion("bg_near0"+index)));
            group_road.addActor(new Image(bgAtlas.findRegion("road0"+index)));
        }

        group_sky.setPosition(0, 720-80);
        group_far.setPosition(0, 720-240);
        group_near.setPosition(0, 720-240);
        group_road.setPosition(0, 720-240-240);
        group_sky.setLength(1280);
        group_sky.reset();
        group_far.reset();
        group_near.reset();
        group_road.reset();
        addActor(group_sky);
        addActor(group_far);
        addActor(group_near);
        addActor(group_road);
    }
    public void addCar(){
        carAtlas = new TextureAtlas(Gdx.files.internal("images/car.pack"));

        player = new Car(new VehicleImage(carAtlas.findRegion("car1")));
        player.setRect(new Rectangle(400,carpos[0],100,30));

        Car c2 = new Car(new VehicleImage(carAtlas.findRegion("car2")));
        c2.setRect(new Rectangle(-200,carpos[1],100,30));
        c2.setSpeed(145);
        Car c3 = new Car(new VehicleImage(carAtlas.findRegion("car3")));
        c3.setRect(new Rectangle(-200,carpos[2],100,30));
        c3.setSpeed(140);
        Car c4 = new Car(new VehicleImage(carAtlas.findRegion("car4")));
        c4.setRect(new Rectangle(-200,carpos[3],100,30));
        c4.setSpeed(135);
        others.add(c2);
        others.add(c3);
        others.add(c4);

        addActor(player.getImage());
        addActor(c2.getImage());
        addActor(c3.getImage());
        addActor(c4.getImage());
        player.getImage().setZIndex(100);
        c2.getImage().setZIndex(101);
        c3.getImage().setZIndex(102);
        c4.getImage().setZIndex(103);


    }

    @Override
    public void act(float delta) {
        changeCarZOrder();
        super.act(delta);
        joyStickCheck();
        changeSpeed(player.getSpeed());
        CarMove(others.get(0));
        CarMove(others.get(1));
        CarMove(others.get(2));
        player.update(delta);
        for(Car car:others){
            car.update(delta);
        }
    }

    private void changeSpeed(float speed) {
        group_sky.setLength(1280);
        group_far.setLength((int)speed*10);
        group_near.setLength((int)speed*10);
        group_road.setLength((int)speed*10);
        group_sky.setLength((int)speed*10);
        group_far.setLength((int)speed*10);
        group_near.setLength((int)speed*10);
        group_road.setLength((int)speed*10);
    }
    private void CarMove(Car car){
        car.getRect().x = car.getRect().getX()+car.getSpeed()-player.getSpeed();
        car.getRect().y = car.getRect().getY();
        if(car.getRect().x>1500||car.getRect().x<-500){
            car.getRect().x=1500;
        }
    }

    private void changeCarZOrder() {
    }

    private void joyStickCheck(){
        if(touchpad.isTouched()){
            if(touchpad.getKnobPercentX()>0.5){
                //right
                player.getOperator().forward(touchpad.getKnobPercentX());
            }else if(touchpad.getKnobPercentX()<-0.5){
                //left
                player.getOperator().back(-touchpad.getKnobPercentX());
            }
            if(touchpad.getKnobPercentY()>0.2){
                player.getOperator().sideShift(2);
                //up
            }else if(touchpad.getKnobPercentY()<-0.2){
                //down
                player.getOperator().sideShift(-2);
            }
            float yMax = 480-player.getRect().height;
            float yMin = 240;
            if(player.getRect().y>yMax){
                player.getRect().y = yMax;
            }
            if(player.getRect().y<yMin){
                player.getRect().y = yMin;
            }
            float xMax = CarGame.worldWidth-player.getRect().width;
            float xMin = 200;
            if(player.getRect().x>xMax){
                player.getRect().x = xMax;
            }
            if(player.getRect().x<xMin){
                player.getRect().x = xMin;
            }
        }
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
        carAtlas.dispose();
        bgAtlas.dispose();
    }
}
