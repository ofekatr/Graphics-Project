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
        gl.glTranslatef(this.measures.getX(), this.measures.getY(), this.measures.getZ());
        super.draw(gl);
    }

    @Override
    public void transform(Vec3 params) {
        this.measures = new Vec3(this.measures.getX() + params.getX(),
                this.measures.getY() + params.getY(),
                this.measures.getZ() + params.getZ());
    }
}
