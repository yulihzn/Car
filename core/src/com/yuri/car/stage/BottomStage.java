package com.yuri.car.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
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
 * Created by BanditCat on 2016/12/28.
 */

public class BottomStage extends Stage {
    private MainScreen mainScreen;
    private Touchpad touchpad;
    private Car player;


    public BottomStage(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        OrthographicCamera camera = new OrthographicCamera(CarGame.worldWidth, CarGame.worldHeight);
        camera.position.set(0f, 0f, 0);
        camera.zoom = 1f;
        camera.update();
        setViewport(new FitViewport(CarGame.worldWidth,CarGame.worldHeight));
        Touchpad.TouchpadStyle style = new Touchpad.TouchpadStyle();
        style.knob = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/knob.png"))));
        style.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/joystick.png"))));
        touchpad = new Touchpad(20,style);
        touchpad.setBounds(20,20,200,200);
        addActor(touchpad);

    }

    @Override
    public void act(float delta) {
        changeCarZOrder();
        super.act(delta);
        joyStickCheck();
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
                player.getOperator().sideShift(300);
                //up
            }else if(touchpad.getKnobPercentY()<-0.2){
                //down
                player.getOperator().sideShift(-300);
            }
//            float yMax = 480-player.getRect().height;
//            float yMin = 240;
//            if(player.getRect().y>yMax){
//                player.getRect().y = yMax;
//            }
//            if(player.getRect().y<yMin){
//                player.getRect().y = yMin;
//            }
        }
    }

    public Car getPlayer() {
        return player;
    }

    public void setPlayer(Car player) {
        this.player = player;
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
