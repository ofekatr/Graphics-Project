/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Drawables;

import javax.media.opengl.GL2;

public class LoadedDrawable implements Drawable {

    private int id;

    public LoadedDrawable(int id) {
        this.id = id;
    }

    @Override
    public void draw(GL2 gl) {
        gl.glCallList(this.id);
    }

    @Override
    public void timePassed() {
        // Nothing.
    }

    public int getId() {
        return id;
    }
}
