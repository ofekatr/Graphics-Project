package Drawables;

import Main.Vec3;

import javax.media.opengl.GL2;

public interface Drawable {
    public void draw(GL2 gl);

    public void timePassed();
}
