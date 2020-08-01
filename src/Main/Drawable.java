package Main;/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import MathLib.MathUtils;

import javax.media.opengl.GL2;
import java.util.ArrayList;
import java.util.List;

public abstract class Drawable {
    private List<Vec3> points;

    public Drawable(List<Vec3> points) {
        this.points = points;
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


    public float getMinX() {
        List<Float> xVals = this.getXVals();
        int minInd = MathUtils.argmin(xVals);
        return this.points.get(minInd).getX();
    }

    public float getMaxX() {
        List<Float> xVals = this.getXVals();
        int maxInd = MathUtils.argmax(xVals);
        return this.points.get(maxInd).getX();
    }

    public float getMinY() {
        List<Float> yVals = this.getYVals();
        int minInd = MathUtils.argmin(yVals);
        return this.points.get(minInd).getY();
    }

    public float getMaxY() {
        List<Float> yVals = this.getYVals();
        int maxInd = MathUtils.argmax(yVals);
        return this.points.get(maxInd).getY();
    }

    public float getMinZ() {
        List<Float> zVals = this.getZVals();
        int minInd = MathUtils.argmin(zVals);
        return this.points.get(minInd).getZ();
    }

    public float getMaxZ() {
        List<Float> zVals = this.getZVals();
        int maxInd = MathUtils.argmax(zVals);
        return this.points.get(maxInd).getZ();
    }

    public abstract void draw(GL2 gl);
}
