package Main;/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import MathLib.MathUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Player {
    private Camera camera;
    private final Map<Axes, Float> axisMovementSteps = new HashMap<>();
    private final Map<Axes, Float> axisRotationSteps = new HashMap<>();

    public Player(Vec3 pos, Vec3 up, Vec3 lookAt, Vec3 sideways) {
        this.camera = new Camera(pos, up, lookAt, sideways);
        this.initAxisStep();
        this.initAxisRotationSteps();
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
        this.initAxisStep();
        this.initAxisRotationSteps();
    }

    private void initAxisStep() {
        this.axisMovementSteps.put(Axes.X, 0.3f);
        this.axisMovementSteps.put(Axes.Y, 0.3f);
        this.axisMovementSteps.put(Axes.Z, 0.3f);
    }

    private void initAxisRotationSteps() {
        this.axisRotationSteps.put(Axes.X, 2f);
        this.axisRotationSteps.put(Axes.Y, 2f);
        this.axisRotationSteps.put(Axes.Z, 2f);
    }

    public void translatef(float x, float y, float z) {
        Vec3[] coords = {this.camera.getSideways(),
                this.camera.getUp(),
//                this.camera.getLookAt()};
                new Vec3(new float[]{0, 0, -1})};
        float[] axisSteps = {this.axisMovementSteps.get(Axes.X),
                this.axisMovementSteps.get(Axes.Y),
                this.axisMovementSteps.get(Axes.Z)};
        float[] args = {x, y, z};
        float[] res = {0f, 0f, 0f};
        for (int i = 0; i < res.length; i++)
            res = MathUtils.sumVectors(res, MathUtils.vectorScalarProduct(coords[i].getArray(), args[i] * axisSteps[i]));
        this.camera.setPos(new Vec3(MathUtils.sumVectors(this.camera.getPos().getArray(), res)));
    }

    public Vec3 getLookAt() {
        return new Vec3(MathUtils.sumVectors(this.camera.getPos().getArray(), this.camera.getLookAt().getArray()));
    }

    public Vec3 getUp() {
        return this.camera.getLookAt();
    }

    public void rotatef(float alpha, Axes axis) {
        float[] x = this.camera.getSideways().getArray(),
                y = this.camera.getUp().getArray(),
                y0 = {0, 1, 0},
                z = this.camera.getLookAt().getArray(),
                resX = x.clone(), resY = y.clone(), resZ = z.clone();
        float[][] transMat;
        alpha = (float) Math.toRadians(alpha);

        switch (axis) {
            case X:
                alpha *= this.axisRotationSteps.get(Axes.X);
                transMat = MathUtils.generateVectorRotationMatrix(x, alpha);
                this.camera.setUp(new Vec3(MathUtils.product(transMat, y0)));
                this.camera.setLookAt(new Vec3(MathUtils.product(transMat, z)));
//                resY = MathUtils.sumVectors(MathUtils.vectorScalarProduct(y, (float) Math.cos(alpha)),
//                        MathUtils.vectorScalarProduct(z, (float) -Math.sin(alpha)));
//                resZ = MathUtils.sumVectors(MathUtils.vectorScalarProduct(z, (float) Math.cos(alpha)),
//                        MathUtils.vectorScalarProduct(y, (float) Math.sin(alpha)));
                return;
            case Y:
                alpha *= this.axisRotationSteps.get(Axes.Y);
                transMat = MathUtils.generateVectorRotationMatrix(y0, alpha);
                this.camera.setSideways(new Vec3(MathUtils.product(transMat, x)));
                this.camera.setLookAt(new Vec3(MathUtils.product(transMat, z)));
                return;

//                resX = MathUtils.sumVectors(MathUtils.vectorScalarProduct(x, (float) Math.cos(alpha)),
//                        MathUtils.vectorScalarProduct(z, (float) -Math.sin(alpha)));
//                resZ = MathUtils.sumVectors(MathUtils.vectorScalarProduct(x, (float) Math.sin(alpha)),
//                        MathUtils.vectorScalarProduct(z, (float) Math.cos(alpha)));
            case Z:
                alpha *= this.axisRotationSteps.get(Axes.Z);
                transMat = MathUtils.generateVectorRotationMatrix(z, alpha);
                this.camera.setSideways(new Vec3(MathUtils.product(transMat, x)));
                this.camera.setUp(new Vec3(MathUtils.product(transMat, y0)));
//                System.out.println(Arrays.toString(z));
                return;

//                resX = MathUtils.sumVectors(MathUtils.vectorScalarProduct(x, (float) Math.cos(alpha)),
//                        MathUtils.vectorScalarProduct(y, (float) -Math.sin(alpha)));
//                resY = MathUtils.sumVectors(MathUtils.vectorScalarProduct(x, (float) Math.sin(alpha)),
//                        MathUtils.vectorScalarProduct(y, (float) Math.cos(alpha)));
//                break;
        }
        this.camera.setSideways(new Vec3(MathUtils.normalize(resX)));
        this.camera.setUp(new Vec3(MathUtils.normalize(resY)));
        this.camera.setLookAt(new Vec3(MathUtils.normalize(resZ)));
//        System.out.println(Arrays.toString(this.camera.get(Main.Axes.X)) + " " + Arrays.toString(this.camera.get(Main.Axes.Y)) + " " + Arrays.toString(this.camera.get(Main.Axes.Z)));
    }

//    public void fixCoordinates() {
//        float[] xy, yz, xz;
//        float[] x = this.camera.getSideways().getArray(),
//                y = this.camera.getUp().getArray(),
//                z = this.camera.getLookAt().getArray();
//        xy = MathUtils.normalize(MathUtils.crossProduct3d(x, y));
//        if (!Arrays.equals(xy, z) && !MathUtils.sameVector(xy, z)) {
//            this.camera.setLookAt(new Vec3(MathUtils.vectorScalarProduct(xy, -1)));
//        }
//        yz = MathUtils.normalize(MathUtils.crossProduct3d(y, z));
//        if (!Arrays.equals(yz, x) && !MathUtils.sameVector(yz, x)) {
//            this.camera.setSideways(new Vec3(yz));
//        }
//        xz = MathUtils.normalize(MathUtils.crossProduct3d(x, z));
//        if (!Arrays.equals(xz, y) && !MathUtils.sameVector(xz, y)) {
//            this.camera.setUp(new Vec3(xz));
//        }
//    }

    public Camera getCamera() {
        return camera;
    }
}
