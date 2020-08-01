package Main;/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import MathLib.MathUtils;

public class Camera {
    private Vec3 pos;
    private Vec3 up;
    private Vec3 lookAt;
    private Vec3 sideways;

    public Camera(Vec3 pos, Vec3 up, Vec3 lookAt) {
        this.pos = pos;
        this.up = up;
        this.lookAt = lookAt;
        this.calcSideways();
    }

    public Camera(Vec3 pos, Vec3 up, Vec3 lookAt, Vec3 sideways) {
        this.pos = pos;
        this.up = up;
        this.lookAt = lookAt;
        this.sideways = sideways;
    }

    public Camera clone() {
        return new Camera(this.pos.clone(),
                this.up.clone(),
                this.lookAt.clone(),
                this.sideways.clone());
    }

    public void setPos(Vec3 pos) {
        this.pos = pos.clone();
    }

    public void setUp(Vec3 up) {
        this.up = up.clone();
    }

    public void setLookAt(Vec3 lookAt) {
        this.lookAt = lookAt.clone();
    }

    public void setSideways(Vec3 sideways) {
        this.sideways = sideways.clone();
    }

    public void calcSideways() {
        this.sideways = new Vec3(MathUtils.crossProduct3d(this.up.getArray(),
                this.lookAt.getArray()));
    }

    public Vec3 getPos() {
        return pos;
    }

    public Vec3 getUp() {
        return up;
    }

    public Vec3 getLookAt() {
//        return new Main.Vec3(MathUtils.sumVectors(this.pos.getArray(), this.lookAt.getArray()));
        return this.lookAt;
    }

    public Vec3 getSideways() {
        return sideways;
    }
}
