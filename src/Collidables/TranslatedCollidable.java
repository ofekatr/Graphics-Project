/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Collidables;

import Main.Vec3;

public class TranslatedCollidable extends Collidable {

    public TranslatedCollidable(CollisionResolver cResolver, CollisionDetector cDetector,
                                Vec3 minVals, Vec3 maxVals, Vec3 params) {
        super(cResolver, cDetector, Vec3.sum(minVals, params), Vec3.sum(maxVals, params));
    }

    public TranslatedCollidable(Vec3 minVals, Vec3 maxVals, Vec3 params) {
        super(Vec3.sum(minVals, params), Vec3.sum(maxVals, params));
    }

    public TranslatedCollidable(Vec3 playerPos, float radius, float height, Vec3 params) {
        super(Vec3.sum(playerPos, params), radius, height);
    }

    public void translate(Vec3 params){
        this.setMinVals(Vec3.sum(this.getMinVals(), params));
        this.setMaxVals(Vec3.sum(this.getMaxVals(), params));
    }


}
