package com.yuri.car.model;

import com.badlogic.gdx.math.Rectangle;
import com.yuri.car.Actor.VehicleImage;
import com.yuri.car.Logic.VehicleOperator;

/**
 * Created by BanditCat on 2016/12/27.
 */

public abstract class Vehicle {
    protected int health=0;//生命
    protected float weight = 0;//重力
    protected float speed = 0;//速度
    protected float acceleration = 0;//加速度
    protected float maxSpeed = 0;//最大速度
    protected float maxAcceleration = 0;//最大加速度
    protected Rectangle rect = new Rectangle();//碰撞区域

    protected VehicleImage image;
    protected VehicleOperator operator;

    protected abstract void initEngine();//初始化引擎
    public abstract void run();//行驶
    public abstract void stop();//停止
    public abstract void light();//车灯
    public abstract void whistle();//鸣笛
    protected abstract void initImage();//图像
    public Vehicle(VehicleImage image) {
        this.image = image;
        operator = new VehicleOperator(this);
        initEngine();
        initImage();
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
        if(acceleration > maxAcceleration){
            this.acceleration = maxAcceleration;
        }
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
        if(speed > maxSpeed){
            this.speed = maxSpeed;
        }
        if(speed<0){
            this.speed = 0;
        }
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getMaxAcceleration() {
        return maxAcceleration;
    }

    public void setMaxAcceleration(float maxAcceleration) {
        this.maxAcceleration = maxAcceleration;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public VehicleImage getImage() {
        return image;
    }

    public void setImage(VehicleImage image) {
        this.image = image;
        initImage();
    }
    public void update(float delta){
        if(image != null){
            image.setPosition(rect.x,rect.y);
        }
        operator.update(delta);
    }
}
