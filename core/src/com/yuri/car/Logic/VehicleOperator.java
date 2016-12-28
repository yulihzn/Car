package com.yuri.car.Logic;

import com.badlogic.gdx.math.Vector2;
import com.yuri.car.model.Vehicle;

/**
 * 交通工具操作类
 * Created by BanditCat on 2016/12/27.
 */

public class VehicleOperator {
    private Vehicle  vehicle;
    private boolean isSideShifting = false;
    private boolean isForWarding = false;
    private boolean isBacking = false;
    private float time=0;
    private float timebox = 0;
    public VehicleOperator(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public void stop(){
        vehicle.stop();
    }
    public void start(){
        vehicle.run();
    }
    public void sideShift(float value){
        isSideShifting = true;
        vehicle.setSlidSpeed(value);
    }
    public void forward(float acceleration){
        isForWarding = true;
        vehicle.setAcceleration(acceleration);
    }
    public void back(float acceleration){
        isBacking =true;
        vehicle.setAcceleration(acceleration);
    }
    //每帧刷新放在act里面调用
    public void update(float delta){
        time+=delta;
        if(time>0.01){
            time=0;
            //侧移
            if(isSideShifting){
                isSideShifting =false;
                if(vehicle.getBody()!=null){
                    vehicle.getBody().setLinearVelocity(new Vector2(vehicle.getSpeed(),vehicle.getSlidSpeed()));
                }
            }
            //前进后退二选其一
            if(isForWarding){
                isForWarding =false;
                vehicle.setSpeed(vehicle.getSpeed()+vehicle.getAcceleration());
            }else if(isBacking){
                isBacking =false;
                vehicle.setSpeed(vehicle.getSpeed()-vehicle.getAcceleration());
            }
        }
        timebox+=delta;
        if(timebox>0.1){
            timebox = 0;
            vehicle.setSlidSpeed(0);
            if(vehicle.getBody()!=null){
                vehicle.getBody().setLinearVelocity(new Vector2(vehicle.getSpeed(),vehicle.getSlidSpeed()));
            }
        }

    }
}
