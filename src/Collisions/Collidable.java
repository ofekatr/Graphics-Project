package Collisions;/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import Main.Vec3;
import MathLib.MathUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class Collidable {
    private List<Vec3> points;
    private CollisionResolver cResolver;
    private CollisionDetector cDetector;

    public Collidable(List<Vec3> points, CollisionResolver cResolver, CollisionDetector cDetector) {
        this.points = points;
        this.cResolver = cResolver;
        this.cDetector = cDetector;
    }

    public Collidable(List<Vec3> points) {
        this.points = points;
        this.cDetector = new AABBDetector();
        this.cResolver = new AABBCollisionResolver();
    }

    public Collidable(Vec3 pos) {
        this.points = new ArrayList<>();
        this.points.add(pos);
        this.cResolver = new AABBCollisionResolver();
        this.cDetector = new AABBDetector();
    }

    public boolean detectCollision(Collidable player) {
        return this.cDetector.detectCollision(player, this);
    }

    public void resolveCollision(Vec3 playerPos) {
        this.cResolver.resolveCollision(playerPos, this);
    }

    public List<Vec3> getPoints() {
        return points;
    }

    public List<Float> getXVals() {
        List<Float> xVals = new ArrayList<>();
        for (Vec3 point : this.points) {
            xVals.add(point.getX());
        }
        return xVals;
    }

    public List<Float> getYVals() {
        List<Float> yVals = new ArrayList<>();
        for (Vec3 point : this.points) {
            yVals.add(point.getY());
        }
        return yVals;
    }

    public List<Float> getZVals() {
        List<Float> zVals = new ArrayList<>();
        for (Vec3 point : this.points) {
            zVals.add(point.getZ());
        }
        return zVals;
    }


    public Vec3 getMinX() {
        List<Float> xVals = this.getXVals();
        int minInd = MathUtils.argmin(xVals);
        return this.points.get(minInd);
    }

    public Vec3 getMaxX() {
        List<Float> xVals = this.getXVals();
        int maxInd = MathUtils.argmax(xVals);
        return this.points.get(maxInd);
    }

    public Vec3 getMinY() {
        List<Float> yVals = this.getYVals();
        int minInd = MathUtils.argmin(yVals);
        return this.points.get(minInd);
    }

    public Vec3 getMaxY() {
        List<Float> yVals = this.getYVals();
        int maxInd = MathUtils.argmax(yVals);
        return this.points.get(maxInd);
    }

    public Vec3 getMinZ() {
        List<Float> zVals = this.getZVals();
        int minInd = MathUtils.argmin(zVals);
        return this.points.get(minInd);
    }

    public Vec3 getMaxZ() {
        List<Float> zVals = this.getZVals();
        int maxInd = MathUtils.argmax(zVals);
        return this.points.get(maxInd);
    }

}
