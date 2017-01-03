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
import com.yuri.car.model.Barricade;
import com.yuri.car.model.Car;
import com.yuri.car.screen.MainScreen;
import com.yuri.car.ui.GroupHorizontalInTurn;

import java.util.Iterator;

/**
 * Created by BanditCat on 2016/12/26.
 */

public class TopStage extends Stage {
    private MainScreen mainScreen;
    private GroupHorizontalInTurn group_sky;
    private GroupHorizontalInTurn group_far;
    private GroupHorizontalInTurn group_near;
    private GroupHorizontalInTurn group_road;
    private TextureAtlas carAtlas;
    private TextureAtlas bgAtlas;
    private int[]carpos={720-240-100,720-240-120,720-240-180,720-240-200};
    private int[]carz = {100,101,102,103};

    private Car player;
    private Array<Car> others = new Array<Car>();

    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;

    private Body ground;

    private Array<Barricade> barricades = new Array<Barricade>();
    public TopStage(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        camera = new OrthographicCamera(CarGame.worldWidth, CarGame.worldHeight);
        camera.position.set(0f, 0f, 0);
        camera.zoom = 1f;
        camera.update();
        setViewport(new FitViewport(CarGame.worldWidth,CarGame.worldHeight,camera));
        addBackground();
        addCar();
        Touchpad.TouchpadStyle style = new Touchpad.TouchpadStyle();
        style.knob = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/knob.png"))));
        style.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("images/joystick.png"))));
        world = new World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();
        addBox2d();
        barricades.clear();
        for (int i = 0; i < 30; i++) {
            addBarricade();
        }

    }


    private void addBackground(){
        bgAtlas = new TextureAtlas(Gdx.files.internal("images/bg.pack"));
        group_sky = new GroupHorizontalInTurn(50,camera);
        group_far = new GroupHorizontalInTurn(4,camera);
        group_near = new GroupHorizontalInTurn(2,camera);
        group_road = new GroupHorizontalInTurn(2,camera);
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
        player.getRect().setPosition(400,carpos[0]);

        Car c2 = new Car(new VehicleImage(carAtlas.findRegion("car2")));
        c2.getRect().setPosition(-200,carpos[1]);
        c2.setSpeed(145);
        Car c3 = new Car(new VehicleImage(carAtlas.findRegion("car3")));
        c3.getRect().setPosition(-200,carpos[2]);
        c3.setSpeed(140);
        Car c4 = new Car(new VehicleImage(carAtlas.findRegion("car4")));
        c4.getRect().setPosition(-200,carpos[3]);
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

    private void addBox2dBackground(){
        BodyDef bd = new BodyDef();
        bd.position.set(0, 0);
        bd.type = BodyDef.BodyType.KinematicBody;
        ground = world.createBody(bd);
        EdgeShape shape = new EdgeShape();
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape=shape;
        fixtureDef.restitution=0;
        fixtureDef.density=0;
        float offset = 200;
//        shape.set(new Vector2(0-offset, 240), new Vector2(0-offset, 480));
//        ground.createFixture(fixtureDef);
        shape.set(new Vector2(0-offset, 240), new Vector2(CarGame.worldWidth+offset, 240));
        ground.createFixture(fixtureDef);
        shape.set(new Vector2(CarGame.worldWidth+offset, 480), new Vector2(0-offset, 480));
        ground.createFixture(fixtureDef);
//        shape.set(new Vector2(CarGame.worldWidth+offset, 480), new Vector2(CarGame.worldWidth+offset, 240));
//        ground.createFixture(fixtureDef);
        shape.dispose();
    }
    private Body getCarBox(Car car){
        if(car.getBody()!=null){
            world.destroyBody(car.getBody());
        }
        BodyDef ballBodyDef = new BodyDef();
        ballBodyDef.type = BodyDef.BodyType.DynamicBody;
        ballBodyDef.position.set(car.getRect().x, car.getRect().y);
        Body body = world.createBody(ballBodyDef);
        body.setUserData(car);
        body.setFixedRotation(true);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(car.getRect().width,car.getRect().height,new Vector2(0,0),0);
        shape.setRadius(-0.4f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1/1000f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0.3f;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }
    private  void addBarricade(){
        Barricade barricade = new Barricade(carAtlas.findRegion("barricade"));
        addActor(barricade);
        barricade.setZIndex(1000);
        BodyDef bd = new BodyDef();
        bd.type= BodyDef.BodyType.DynamicBody;
        bd.position.set(300,300);
        Body body = world.createBody(bd);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(barricade.getWidth()/2,barricade.getHeight()/2,new Vector2(0,0),0);
        shape.setRadius(-0.4f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1/10000f;
        fixtureDef.friction = 0.1f;
        fixtureDef.restitution = 0.5f;
        body.createFixture(fixtureDef);
        shape.dispose();
        barricade.setBody(body);
        barricades.add(barricade);
    }
    private void addBox2d(){
        addBox2dBackground();
        player.setBody(getCarBox(player));
        for(Car car:others){
            car.setBody(getCarBox(car));
        }


    }

    public Car getPlayer() {
        return player;
    }

    private void updateBox(){
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body b : bodies) {
            Car e = (Car) b.getUserData();
            if (e != null && b.isAwake()) {
                if(!b.isAwake()){
                    b.getPosition().set(e.getRect().x,e.getRect().y);
                }else{
                    e.getRect().setPosition(b.getPosition().x, b.getPosition().y);
                    e.getImage().setRotation(MathUtils.radiansToDegrees * b.getAngle());
                }
            }
        }
    }

    @Override
    public void act(float delta) {
        changeCarZOrder();
        super.act(delta);
        changeSpeed(player.getSpeed());
        player.update(delta);
        for(Barricade b : barricades){
            b.setSpeed(-player.getSpeed());
        }
        for(Car car:others){
            CarMove(car);
            car.update(delta);
        }
        getCamera().position.set(player.getRect().x+300,getCamera().position.y,0);
        world.step(1/60f,6,2);
    }

    private void changeSpeed(float speed) {
        group_sky.setLength(1280);
        group_far.setLength((int)speed*10);
        group_near.setLength((int)speed*10);
        group_road.setLength((int)speed*10);
        ground.setLinearVelocity(new Vector2(speed,0));
    }
    private void CarMove(Car car){
        if(car.getBody() != null){
            car.getBody().setLinearVelocity(new Vector2(car.getSpeed(),car.getSlidSpeed()));
            if(car.getBody().getPosition().x>1500||car.getBody().getPosition().x<-500){
                car.getBody().getPosition().x=MathUtils.random(1)>0?1500:-500;
            }
        }
    }

    private void changeCarZOrder() {
    }

    @Override
    public void draw() {
        super.draw();
        debugRenderer.render(world, getCamera().combined);
    }

    @Override
    public void dispose() {
        super.dispose();
        carAtlas.dispose();
        bgAtlas.dispose();
    }
}
