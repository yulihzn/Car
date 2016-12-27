package com.yuri.car.model;

import com.badlogic.gdx.math.Rectangle;
import com.yuri.car.Actor.VehicleImage;
import com.yuri.car.Logic.VehicleOperator;

/**
 * Created by BanditCat on 2016/12/27.
 */

public class Car extends Vehicle {


    public Car(VehicleImage image) {
        super(image);
    }

    @Override
    protected void initEngine() {
        setHealth(10);
        setAcceleration(0);
        setSpeed(0);
        setMaxAcceleration(4);
        setMaxSpeed(150);
        setRect(new Rectangle(0,0,100,30));
        setWeight(200);
        operator = new VehicleOperator(this);
    }

    @Override
    public void run() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void light() {

    }

    @Override
    public void whistle() {

    }

    @Override
    protected void initImage() {

    }

    public VehicleOperator getOperator() {
        return operator;
    }
}
