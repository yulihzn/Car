package com.yuri.car.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Scaling;

/**
 * Created by BanditCat on 2017/1/3.
 */

public class Barricade extends Image{
    private Body body;
    private float speed;

    public Barricade() {
    }

    public Barricade(Drawable drawable) {
        super(drawable);
    }

    public Barricade(Drawable drawable, Scaling scaling) {
        super(drawable, scaling);
    }

    public Barricade(Drawable drawable, Scaling scaling, int align) {
        super(drawable, scaling, align);
    }

    public Barricade(NinePatch patch) {
        super(patch);
    }

    public Barricade(TextureRegion region) {
        super(region);
    }

    public Barricade(Skin skin, String drawableName) {
        super(skin, drawableName);
    }

    public Barricade(Texture texture) {
        super(texture);
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(body != null){
            setPosition(body.getPosition().x-getWidth()/2,body.getPosition().y-getHeight()/2);
            setOrigin(getWidth()/2,getHeight()/2);
            setRotation(MathUtils.radiansToDegrees*body.getAngle());
        }
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
