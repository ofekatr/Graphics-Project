/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Collidables;

import Main.Vec3;

public class ScaledCollidable extends Collidable {

    public ScaledCollidable(CollisionResolver cResolver, CollisionDetector cDetector,
                                Vec3 minVals, Vec3 maxVals, float alpha) {
        super(cResolver, cDetector, Vec3.multByScalar(minVals, alpha), Vec3.multByScalar(maxVals, alpha));
    }

    public ScaledCollidable(Vec3 minVals, Vec3 maxVals, float alpha) {
        super(Vec3.multByScalar(minVals, alpha), Vec3.multByScalar(maxVals, alpha));
    }

    public ScaledCollidable(Vec3 playerPos, float radius, float height, float alpha) {
        super(playerPos, radius * alpha, height * alpha);
    }

    public void sclaef(float alpha){
        this.setMinVals(Vec3.multByScalar(this.getMinVals(), alpha));
        this.setMaxVals(Vec3.multByScalar(this.getMaxVals(), alpha));
    }


}
