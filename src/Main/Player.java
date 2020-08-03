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
    private Camera camera;
    private final Map<Axes, Float> axisMovementSteps = new HashMap<>();
    private final Map<Axes, Float> axisRotationSteps = new HashMap<>();

    public Player(Vec3 pos, Vec3 up, Vec3 lookAt, Vec3 sideways) {
        this.camera = new Camera(pos, up, lookAt, sideways);
//        this.initAxisStep();
//        this.initAxisRotationSteps();
    }

    public Player() {
        float[] sideways = {1f, 0f, 0f};
        float[] up = {0f, 1f, 0f};
        float[] lookAt = {0f, 0f, -1f};
        float[] pos = {0f, 0f, 0f};
        this.camera = new Camera(new Vec3(pos),
                new Vec3(up),
                new Vec3(lookAt),
                new Vec3(sideways));
//        this.initAxisStep();
//        this.initAxisRotationSteps();
    }

//    private void initAxisStep() {
//        this.axisMovementSteps.put(Axes.X, 0.3f);
//        this.axisMovementSteps.put(Axes.Y, 0.3f);
//        this.axisMovementSteps.put(Axes.Z, 0.3f);
//    }

//    private void initAxisRotationSteps() {
//        this.axisRotationSteps.put(Axes.X, 2f);
//        this.axisRotationSteps.put(Axes.Y, 2f);
//        this.axisRotationSteps.put(Axes.Z, 2f);
//    }

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
