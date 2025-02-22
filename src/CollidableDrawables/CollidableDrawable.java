/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package CollidableDrawables;

import Collidables.Collidable;
import Collidables.CollisionDetector;
import Collidables.CollisionResolver;
import Drawables.Drawable;
import Main.Vec3;

import javax.media.opengl.GL2;

public class CollidableDrawable extends Collidable implements Drawable {

    private Drawable drawable;

    public CollidableDrawable(CollisionResolver cResolver, CollisionDetector cDetector, Vec3 minVals, Vec3 maxVals,
                              Drawable drawable) {
        super(cResolver, cDetector, minVals, maxVals);
        this.drawable = drawable;
    }

    public CollidableDrawable(Vec3 minVals, Vec3 maxVals, Drawable drawable) {
        super(minVals, maxVals);
        this.drawable = drawable;
    }

    @Override
    public void draw(GL2 gl) {
        this.drawable.draw(gl);
    }

    @Override
    public void timePassed() {
        this.drawable.timePassed();
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable1) {
        this.drawable = drawable1;
    }
}
