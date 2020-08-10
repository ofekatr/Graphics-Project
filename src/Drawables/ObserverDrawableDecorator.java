/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Drawables;

import Main.Vec3;

public class ObserverDrawableDecorator {

    private DrawableDecorator d;

    public ObserverDrawableDecorator(DrawableDecorator d) {
        this.d = d;
    }

    public void update(Vec3 params) {
        this.d.transform(params);
    }
}
