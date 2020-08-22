/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Drawables;

import Main.Vec3;

import javax.media.opengl.GL2;

public class TranslationDrawableDecorator extends DrawableDecorator {

    private Vec3 measures;

    public TranslationDrawableDecorator(Drawable d, Vec3 measures) {
        super(d);
        this.measures = measures;
    }

    public TranslationDrawableDecorator(Drawable d) {
        super(d);
        this.measures = new Vec3(0, 0, 0);
    }

    @Override
    public void draw(GL2 gl) {
        gl.glPushMatrix();
        gl.glTranslatef(this.measures.getX(), this.measures.getY(), this.measures.getZ());
        super.draw(gl);
        gl.glPopMatrix();
    }

    @Override
    public void transform(Vec3... params) {
        Vec3 transVals = params[0];
        this.measures = new Vec3(this.measures.getX() + transVals.getX(),
                this.measures.getY() + transVals.getY(),
                this.measures.getZ() + transVals.getZ());
    }
}
