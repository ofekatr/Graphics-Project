package Collidables;/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import Main.RadiusCollider;
import Main.Vec3;

public abstract class Collidable {
    protected static final CollisionResolver defColResolver = AABBCollisionResolver.getInstance();
    protected static final CollisionDetector defColDetector = AABBCollisionDetector.getInstance();

    private CollisionResolver cResolver;
    private CollisionDetector cDetector;
    private Vec3 minVals;
    private Vec3 maxVals;

    public Collidable(CollisionResolver cResolver, CollisionDetector cDetector, Vec3 minVals, Vec3 maxVals) {
        this.cResolver = cResolver;
        this.cDetector = cDetector;
        this.minVals = minVals;
        this.maxVals = maxVals;
    }

    public Collidable(Vec3 minVals, Vec3 maxVals) {
        this.minVals = minVals;
        this.maxVals = maxVals;
        this.cDetector = defColDetector;
        this.cResolver = defColResolver;
    }

    public Collidable(Vec3 playerPos, float radius, float height) {
        this.maxVals = new Vec3(playerPos.getX() + radius, playerPos.getY(), playerPos.getZ() + radius);
        this.minVals = new Vec3(playerPos.getX() - radius, playerPos.getY() - height, playerPos.getZ() - radius);
        this.cResolver = defColResolver;
        this.cDetector = defColDetector;
    }

    public boolean detectCollision(Collidable player) {
        return this.cDetector.detectCollision(player, this);
    }

    public void resolveCollision(RadiusCollider rc, boolean isProjectile) {
        this.cResolver.resolveCollision(rc, this);
    }

    public Vec3 getMinVals() {
        return minVals;
    }

    public Vec3 getMaxVals() {
        return maxVals;
    }

    protected void setMinVals(Vec3 minVals) {
        this.minVals = minVals;
    }

    protected void setMaxVals(Vec3 maxVals) {
        this.maxVals = maxVals;
    }

    public CollisionResolver getcResolver() {
        return cResolver;
    }

    public CollisionDetector getcDetector() {
        return cDetector;
    }
}
