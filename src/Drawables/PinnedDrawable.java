/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Drawables;

import javax.media.opengl.GL2;

public class PinnedDrawable implements Drawable {

    private Drawable d;

    public PinnedDrawable(Drawable d) {
        this.d = d;
    }

    @Override
    public void draw(GL2 gl) {
        gl.glPushMatrix();
        gl.glLoadIdentity();
        this.transform(gl);
        d.draw(gl);
        gl.glPopMatrix();
    }

    protected void transform(GL2 gl) {
        // Nothing.
    }

    @Override
    public void timePassed() {

    }
}
