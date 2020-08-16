/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Drawables;

import Main.Vec3;

import javax.media.opengl.GL2;

public class RotationDrawableDecorator extends DrawableDecorator {

    private static final float step = 1;
    private Vec3 axis;
    private float alpha;
    private int id;

    public RotationDrawableDecorator(int id, Drawable d, Vec3 axis, float alpha) {
        super(d);
        this.id = id;
        this.axis = axis;
        this.alpha = alpha;
    }

    public RotationDrawableDecorator(int id, Drawable d, Vec3 axis) {
        super(d);
        this.id = id;
        this.axis = axis;
        this.alpha = 0;
    }

    @Override
    public void transform(Vec3... params) {
        if (params.length < 2)
            throw new RuntimeException("Incorrect number of arguments in RotationDrawableDecorator");
        int id = (int) params[0].getY();
        if (id == this.id) {
            this.alpha = params[0].getX();
            this.axis = params[1].clone();
        }

    }

    @Override
    public void draw(GL2 gl) {
        gl.glRotatef(step * alpha, this.axis.getX(), this.axis.getY(), this.axis.getZ());
        super.draw(gl);
    }
}
