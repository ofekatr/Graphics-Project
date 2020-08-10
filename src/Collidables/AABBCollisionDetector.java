/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Collidables;

import Main.Vec3;

public class AABBCollisionDetector implements CollisionDetector {

    private static AABBCollisionDetector instance;

    private AABBCollisionDetector() {
    }

    public static AABBCollisionDetector getInstance() {
        if (instance == null) {
            return instance = new AABBCollisionDetector();
        }
        return instance;
    }

    public boolean detectCollision(Collidable player, Collidable c) {
//        return this.singleTest(a, b) || this.singleTest(b, a);
        return this.singleTest(player, c);
    }

    // Check if b entered a's boundaries.
    private boolean singleTest(Collidable player, Collidable c) {
        Vec3 playerMinVals = player.getMinVals(), playerMaxVals = player.getMaxVals();
        Vec3 colMinVals = c.getMinVals(), colMaxVals = c.getMaxVals();
        float aXMax = playerMaxVals.getX(), aXMin = playerMinVals.getX(),
                aYMax = playerMaxVals.getY(), aYMin = playerMinVals.getY(),
                aZMax = playerMaxVals.getZ(), aZMin = playerMinVals.getZ();
        float bXMax = colMaxVals.getX(), bXMin = colMinVals.getX(),
                bYMax = colMaxVals.getY(), bYMin = colMinVals.getY(),
                bZMax = colMaxVals.getZ(), bZMin = colMinVals.getZ();
        return (aXMin <= bXMax && aXMax >= bXMin &&
                aYMin <= bYMax && aYMax >= bYMin &&
                aZMin <= bZMax && aZMax >= bZMin);
    }
}
