package Collisions;/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import Main.Vec3;
import MathLib.MathUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class Collidable {
    private CollisionResolver cResolver;
    private CollisionDetector cDetector;
    protected Vec3 minVals;
    protected Vec3 maxVals;

    public Collidable(CollisionResolver cResolver, CollisionDetector cDetector, Vec3 minVals, Vec3 maxVals) {
        this.cResolver = cResolver;
        this.cDetector = cDetector;
        this.minVals = minVals;
        this.maxVals = maxVals;
    }

    public Collidable(Vec3 minVals, Vec3 maxVals) {
        this.minVals = minVals;
        this.maxVals = maxVals;
        this.cDetector = new AABBDetector();
        this.cResolver = new AABBCollisionResolver();
    }

    public Collidable(Vec3 playerPos, float radius, float height) {
        this.maxVals = new Vec3(playerPos.getX() + radius, playerPos.getY(), playerPos.getZ() + radius);
        this.minVals = new Vec3(playerPos.getX() - radius, playerPos.getY() - height, playerPos.getZ() - radius);
        this.cResolver = new AABBCollisionResolver();
        this.cDetector = new AABBDetector();
    }

    public boolean detectCollision(Collidable player) {
        return this.cDetector.detectCollision(player, this);
    }

    public void resolveCollision(Vec3 playerPos) {
        this.cResolver.resolveCollision(playerPos, this);
    }

    public Vec3 getMinVals() {
        return minVals;
    }

    public Vec3 getMaxVals() {
        return maxVals;
    }
}
