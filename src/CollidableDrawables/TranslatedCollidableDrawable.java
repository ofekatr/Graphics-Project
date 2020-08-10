/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package CollidableDrawables;

import Collisions.CollisionDetector;
import Collisions.CollisionResolver;
import Drawables.Drawable;
import Drawables.TranslationDrawableDecorator;
import Main.Vec3;

public class TranslatedCollidableDrawable extends CollidableDrawable {

    public TranslatedCollidableDrawable(CollisionResolver cResolver, CollisionDetector cDetector, Vec3 minVals,
                                        Vec3 maxVals, Vec3 transParams, Drawable d) {
        super(cResolver, cDetector, translateVals(minVals, transParams), translateVals(maxVals, transParams),
                new TranslationDrawableDecorator(d, transParams));
    }

    public TranslatedCollidableDrawable(Vec3 minVals, Vec3 maxVals, Drawable d, Vec3 transParams) {
        super(translateVals(minVals, transParams), translateVals(maxVals, transParams),
                new TranslationDrawableDecorator(d, transParams));
    }

    private static Vec3 translateVals(Vec3 vals, Vec3 params) {
        return new Vec3(vals.getX() + params.getX(),
                vals.getY() + params.getY(),
                vals.getZ() + params.getZ());
    }
}
