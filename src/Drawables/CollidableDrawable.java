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

public class CollidableDrawable extends Collidable implements Drawable {

    public CollidableDrawable(CollisionResolver cResolver, CollisionDetector cDetector, Vec3 minVals, Vec3 maxVals) {
        super(cResolver, cDetector, minVals, maxVals);
    }

    public CollidableDrawable(Vec3 minVals, Vec3 maxVals) {
        super(minVals, maxVals);
    }

    @Override
    public void draw(GL2 gl) {
        // Nothing.
    }

    @Override
    public void timePassed() {
        // Nothing.
    }
}
