/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Drawables;

import Main.Vec3;

import javax.media.opengl.GL2;

public class RotationDrawableDecorator extends DrawableDecorator {

    private Vec3 axis;
    private float alpha;

    public RotationDrawableDecorator(Drawable d, Vec3 axis, float alpha) {
        super(d);
        this.axis = axis;
        this.alpha = alpha;
    }

    @Override
    public void transform(Vec3... params) {
        if (params.length < 2)
            throw new RuntimeException("Incorrect number of arguments in RotationDrawableDecorator");
        Vec3 axis = params[1], alpha = params[0];

        if (this.axis.equals(axis)) {
            this.alpha += alpha.getX();
        }

    }

    @Override
    public void draw(GL2 gl) {
        gl.glRotatef(this.alpha, this.axis.getX(), this.axis.getY(), this.axis.getZ());
        super.draw(gl);
    }
}
