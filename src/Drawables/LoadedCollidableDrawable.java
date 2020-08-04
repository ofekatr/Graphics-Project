/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Drawables;

import Collisions.Collidable;
import Collisions.CollisionDetector;
import Collisions.CollisionResolver;
import Main.Vec3;

import javax.media.opengl.GL2;

public class LoadedCollidableDrawable extends CollidableDrawable {

    protected int id;

    public LoadedCollidableDrawable(CollisionResolver cResolver, CollisionDetector cDetector, Vec3 minVals, Vec3 maxVals, int id) {
        super(cResolver, cDetector, minVals, maxVals);
        this.id = id;
    }

    public LoadedCollidableDrawable(Vec3 minVals, Vec3 maxVals, int id) {
        super(minVals, maxVals);
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
}
