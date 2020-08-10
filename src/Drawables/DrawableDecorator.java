/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Drawables;

import Main.Vec3;

import javax.media.opengl.GL2;

public abstract class DrawableDecorator implements Drawable {

    private Drawable d;

    public DrawableDecorator(Drawable d) {
        this.d = d;
    }

    public abstract void transform(Vec3 params);

    @Override
    public void draw(GL2 gl) {
        this.d.draw(gl);
    }

    @Override
    public void timePassed() {
        this.d.timePassed();
    }
}
