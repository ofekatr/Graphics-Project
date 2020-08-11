/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Drawables;

import Main.Vec3;

import javax.media.opengl.GL2;

public class TranslatedPinnedDrawable extends PinnedDrawable {
    private Vec3 transVals;

    public TranslatedPinnedDrawable(Drawable d, Vec3 transVals) {
        super(d);
        this.transVals = transVals;
    }

    @Override
    protected void transform(GL2 gl) {
        gl.glTranslatef(this.transVals.getX(), this.transVals.getY(), this.transVals.getZ());
    }
}
