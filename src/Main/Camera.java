package Main;/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import InputHandlers.CameraInputHandler;
import MathLib.MathUtils;

import java.util.HashMap;
import java.util.Map;

public class Camera {
    private static Camera instance;

    private Vec3 pos;
    private Vec3 up;
    private Vec3 lookAt;
    private Vec3 sideways;

    private boolean isFrozen = false;

    private final Map<Axes, Float> axisMovementSteps = new HashMap<>();
    private final Map<Axes, Float> axisRotationSteps = new HashMap<>();

    private CameraInputHandlerObservablesManager inputHandler;

    private Camera(Vec3 pos, Vec3 up, Vec3 lookAt) {
        this.pos = pos;
        this.up = up;
        this.lookAt = lookAt;
        this.calcSideways();

        this.inputHandler = new CameraInputHandlerObservablesManager(this);

        this.initAxisStep();
        this.initAxisRotationSteps();
    }

    private Camera(Vec3 pos, Vec3 up, Vec3 lookAt, Vec3 sideways) {
        this.pos = pos;
        this.up = up;
        this.lookAt = lookAt;
        this.sideways = sideways;

        this.inputHandler = new CameraInputHandlerObservablesManager(this);

        this.initAxisStep();
        this.initAxisRotationSteps();
    }

    public static Camera getInstance() {
        return instance;
    }

    public static Camera getInstance(Vec3 pos, Vec3 up, Vec3 lookAt, Vec3 sideways){
        instance = new Camera(pos, up, lookAt, sideways);
        return instance;
    }

    public static Camera getInstance(Vec3 pos, Vec3 up, Vec3 lookAt){
        instance = new Camera(pos, up, lookAt);
        return instance;
    }

    private void initAxisStep() {
        this.axisMovementSteps.put(Axes.X, 0.3f);
        this.axisMovementSteps.put(Axes.Y, 0.3f);
        this.axisMovementSteps.put(Axes.Z, 0.3f);
    }

    private void initAxisRotationSteps() {
        this.axisRotationSteps.put(Axes.X, 4f);
        this.axisRotationSteps.put(Axes.Y, 4f);
        this.axisRotationSteps.put(Axes.Z, 4f);
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

        if (Math.abs(lookAt.getY()) >= 0.01) {
            this.up = up.clone();
        }
    }

    public void setLookAt(Vec3 lookAt) {
        if (Math.abs(Math.abs(lookAt.getY()) - 1) >= 0.01) {
            this.lookAt = lookAt.clone();
        }
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

    public CameraInputHandlerObservablesManager getInputHandler() {
        return this.inputHandler;
    }

    public Vec3 getSideways() {
        return sideways;
    }

    public void rotatef(float alpha, Axes axis, boolean isSteps) {
        if (this.isFrozen){
            return;
        }
        float[] x = this.getSideways().getArray(),
                y = this.getUp().getArray(),
                y0 = {0, 1, 0},
                z = this.getLookAt().getArray(),
                resX = x.clone(), resY = y.clone(), resZ = z.clone();
        float[][] transMat;
        alpha = (float) Math.toRadians(alpha);

        switch (axis) {
            case X:
                alpha *= !isSteps ? 1 : this.axisRotationSteps.get(Axes.X);
                transMat = MathUtils.generateVectorRotationMatrix(x, alpha);
                this.setUp(new Vec3(MathUtils.product(transMat, y0)));
                this.setLookAt(new Vec3(MathUtils.product(transMat, z)));
                this.inputHandler.notifyRot(new Vec3(alpha, 0, 0), this.sideways.clone());
                return;
            case Y:
                alpha *= !isSteps ? 1 : this.axisRotationSteps.get(Axes.Y);
                transMat = MathUtils.generateVectorRotationMatrix(y0, alpha);
                this.setSideways(new Vec3(MathUtils.product(transMat, x)));
                this.setLookAt(new Vec3(MathUtils.product(transMat, z)));
                this.inputHandler.notifyRot(new Vec3(alpha, 1, 0), this.up.clone());
                return;
            case Z:
                alpha *= !isSteps ? 1 : this.axisRotationSteps.get(Axes.Z);
                transMat = MathUtils.generateVectorRotationMatrix(z, alpha);
                this.setSideways(new Vec3(MathUtils.product(transMat, x)));
                this.setUp(new Vec3(MathUtils.product(transMat, y0)));
                this.inputHandler.notifyRot(new Vec3(alpha, 2, 0), this.lookAt.clone());
                return;
        }
        this.setSideways(new Vec3(MathUtils.normalize(resX)));
        this.setUp(new Vec3(MathUtils.normalize(resY)));
        this.setLookAt(new Vec3(MathUtils.normalize(resZ)));
    }

    public void translatef(float x, float y, float z, boolean isSteps) {
        if (this.isFrozen){
            return;
        }
        Vec3[] coords = {this.getSideways(),
                this.getUp(),
                new Vec3(new float[]{this.getLookAt().getX(), 0, this.getLookAt().getZ()})};
        float[] axisSteps = {this.axisMovementSteps.get(Axes.X),
                this.axisMovementSteps.get(Axes.Y),
                this.axisMovementSteps.get(Axes.Z)};
        float[] args = {x, y, z};
        float[] res = {0f, 0f, 0f};
        for (int i = 0; i < res.length; i++)
            res = !isSteps ? args : MathUtils.sumVectors(res, MathUtils.vectorScalarProduct(coords[i].getArray(), args[i] * axisSteps[i]));
        this.setPos(new Vec3(MathUtils.sumVectors(this.getPos().getArray(), res)));

        this.inputHandler.notifyTrans(new Vec3(res.clone()));
    }

    public void changeFreezeStatus(){
        this.isFrozen = !this.isFrozen;
    }

    public boolean isFrozen() {
        return isFrozen;
    }
}
