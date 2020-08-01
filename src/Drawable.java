/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import javax.media.opengl.GL2;
import java.util.List;

public abstract class Drawable {
    private List<Vec3> points;


    public Drawable(List<Vec3> points) {
        this.points = points;
    }

    public List<Vec3> getPoints() {
        return points;
    }

    public abstract void draw(GL2 gl);
}
