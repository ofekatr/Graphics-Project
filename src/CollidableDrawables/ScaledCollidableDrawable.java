/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package CollidableDrawables;

import Collidables.CollisionDetector;
import Collidables.CollisionResolver;
import Drawables.Drawable;
import Drawables.ScaleDrawableDecorator;
import Drawables.TranslationDrawableDecorator;
import Main.Vec3;

public class ScaledCollidableDrawable extends CollidableDrawable {

    public ScaledCollidableDrawable(CollisionResolver cResolver, CollisionDetector cDetector, Vec3 minVals,
                                        Vec3 maxVals, float alpha, Drawable d) {
        super(cResolver, cDetector, scaleVals(minVals, alpha), scaleVals(maxVals, alpha),
                new ScaleDrawableDecorator(d, alpha));
    }

    public ScaledCollidableDrawable(Vec3 minVals, Vec3 maxVals, Drawable d, float alpha) {
        super(scaleVals(minVals, alpha), scaleVals(maxVals, alpha),
                new ScaleDrawableDecorator(d, alpha));
    }

    private static Vec3 scaleVals(Vec3 vals, float alpha) {
        return Vec3.multByScalar(vals, alpha);
    }

    private void scale(Vec3 params){
        this.setMinVals(Vec3.sum(this.getMinVals(), params));
        this.setMaxVals(Vec3.sum(this.getMaxVals(), params));
    }
}
