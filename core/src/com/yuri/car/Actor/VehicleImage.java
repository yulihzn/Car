package com.yuri.car.Actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Scaling;

/**
 * Created by BanditCat on 2016/12/27.
 */

public class VehicleImage extends Image {
    public VehicleImage() {
    }

    public VehicleImage(Drawable drawable) {
        super(drawable);
    }

    public VehicleImage(Drawable drawable, Scaling scaling) {
        super(drawable, scaling);
    }

    public VehicleImage(Drawable drawable, Scaling scaling, int align) {
        super(drawable, scaling, align);
    }

    public VehicleImage(NinePatch patch) {
        super(patch);
    }

    public VehicleImage(TextureRegion region) {
        super(region);
    }

    public VehicleImage(Skin skin, String drawableName) {
        super(skin, drawableName);
    }

    public VehicleImage(Texture texture) {
        super(texture);
    }
}
