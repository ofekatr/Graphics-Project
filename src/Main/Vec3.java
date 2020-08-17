package Main;/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import MathLib.MathUtils;

import java.util.HashMap;
import java.util.Map;

public class Vec3 {
    private final Map<Axes, Float> vals = new HashMap<>();

    public Vec3() {
        this.fillMap(0, 0, 0);
    }

    public Vec3(float x, float y, float z) {
        this.fillMap(x, y, z);
    }

    public Vec3(float[] arr) {
        this.fillMap(arr[0], arr[1], arr[2]);
    }

    public Vec3 clone() {
        return new Vec3(this.getArray());
    }

    public void fillMap(float x, float y, float z) {
        this.vals.put(Axes.X, x);
        this.vals.put(Axes.Y, y);
        this.vals.put(Axes.Z, z);
    }

    public float[] getArray() {
        float[] arr = {this.vals.get(Axes.X),
                this.vals.get(Axes.Y),
                this.vals.get(Axes.Z)};
        return arr;
    }

    public void setX(float x) {
        this.vals.put(Axes.X, x);
    }

    public void setY(float y) {
        this.vals.put(Axes.Y, y);
    }

    public void setZ(float z) {
        this.vals.put(Axes.Z, z);
    }

    public float getX() {
        return vals.get(Axes.X);
    }

    public float getY() {
        return vals.get(Axes.Y);
    }

    public static Vec3 sum(Vec3 v1, Vec3 v2) {
        return new Vec3(v1.getX() + v2.getX(),
                v1.getY() + v2.getY(),
                v1.getZ() + v2.getZ());
    }

    public float getZ() {
        return vals.get(Axes.Z);
    }

    public String toString() {
        return "{ " + this.getX() + ", " + this.getY() + ", " + this.getZ() + "}";
    }

    public boolean equals(Vec3 other) {
        Vec3 norm1 = new Vec3(MathUtils.normalize(this.getArray())),
                norm2 = new Vec3(MathUtils.normalize(other.getArray()));
        return norm1.getX() == norm2.getX() &&
                norm1.getY() == norm2.getY() &&
                norm1.getZ() == norm2.getZ();
    }
}
