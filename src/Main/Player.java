package Main;/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import MathLib.MathUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Player {
    public static final float PLAYER_RADIUS = 10;
    public static final float PLAYER_HEIGHT = 2;
    private Camera camera;


    public Player(Vec3 pos, Vec3 up, Vec3 lookAt, Vec3 sideways) {
        this.camera = new Camera(pos, up, lookAt, sideways);
    }

    public Player() {
        float[] sideways = {1f, 0f, 0f};
        float[] up = {0f, 1f, 0f};
        float[] lookAt = {0f, 0f, -1f};
        float[] pos = {0f, PLAYER_HEIGHT, 0f};
        this.camera = new Camera(new Vec3(pos),
                new Vec3(up),
                new Vec3(lookAt),
                new Vec3(sideways));
//        this.initAxisStep();
//        this.initAxisRotationSteps();
    }

    public void translatef(float x, float y, float z) {
        this.camera.translatef(x, y, z);
    }

    public Vec3 getLookAt() {
        return new Vec3(MathUtils.sumVectors(this.camera.getPos().getArray(), this.camera.getLookAt().getArray()));
    }

    public Vec3 getUp() {
        return this.camera.getLookAt();
    }

    public void rotatef(float alpha, Axes axis) {
        this.camera.rotatef(alpha, axis);
    }

    public Camera getCamera() {
        return camera;
    }
}
