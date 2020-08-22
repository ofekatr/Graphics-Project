/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Drawables;

import Main.Vec3;

import javax.media.opengl.GL2;

public class ScaleDrawableDecorator extends DrawableDecorator {

    private float alpha;

    public ScaleDrawableDecorator(Drawable d, float alpha) {
        super(d);
        this.alpha = alpha;
    }

    public ScaleDrawableDecorator(Drawable d) {
        super(d);
        this.alpha = 1;
    }

    @Override
    public void draw(GL2 gl) {
        gl.glPushMatrix();
        gl.glScalef(this.alpha, this.alpha, this.alpha);
        super.draw(gl);
        gl.glPopMatrix();
    }

    @Override
    public void transform(Vec3... params) {
        Vec3 transVals = params[0];
        this.alpha *= transVals.getX();
    }
}
