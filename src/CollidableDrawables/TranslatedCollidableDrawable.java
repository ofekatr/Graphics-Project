/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package CollidableDrawables;

import Collidables.CollisionDetector;
import Collidables.CollisionResolver;
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
        return Vec3.sum(vals, params);
    }

    private void translate(Vec3 params){
        this.setMinVals(Vec3.sum(this.getMinVals(), params));
        this.setMaxVals(Vec3.sum(this.getMaxVals(), params));
    }
}
