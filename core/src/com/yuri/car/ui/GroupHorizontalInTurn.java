package com.yuri.car.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;

/**
 * Created by BanditCat on 2016/12/26.
 */

public class GroupHorizontalInTurn extends Group {
    private float time;
    private int lastIndex;
    private int length = 0;

    public GroupHorizontalInTurn(float time) {
        // 一张背景移动通过config.width的时间
        this.time = time;
    }

    @Override
    public void addActor(Actor actor) {
        super.addActor(actor);
    }

    @Override
    public void act(float delta) {
        SnapshotArray<Actor> children = super.getChildren();
        float pastTime = (long) (delta * 1000000000f)
                % ((long) (time * 1000000000f)) / 1000000000f;
        float moveDistance = length * pastTime / time;

        Actor[] actors = children.begin();
        for (int i = 0, n = children.size; i < n; i++) {
            // 所有背景移动距离
            float newX = actors[i].getX() - moveDistance;
            actors[i].setX(newX);
        }

        // lastIndex：排在最后面的actor下标
        // +1开始扫描也就是从头开始扫描
        int i = (lastIndex + 1) % children.size;
        while (true) {
            if (actors[i].getX() < -actors[i].getWidth()) {
                // 超出范围，放到后面
                actors[i].setX(actors[lastIndex].getRight());
                lastIndex = i;
            } else {
                break;
            }
            // 一帧之内可能有多个actor超出屏幕范围
            i = (i + 1) % children.size;
        }
        children.end();

        super.act(delta);
    }

    // 调用后按照actor加入顺序从左到右排列
    public void reset() {
        SnapshotArray<Actor> children = super.getChildren();
        Actor[] actors = children.begin();
        for (int i = 1, n = children.size; i < n; i++) {
            actors[i].setX(actors[i - 1].getX() + actors[i - 1].getWidth());
        }
        children.end();

        lastIndex = super.getChildren().size - 1;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
