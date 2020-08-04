package Main;/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import Collisions.Collidable;
import Collisions.CollisionManager;
import MathLib.MathUtils;

public class Player extends Collidable {
    public static final float PLAYER_RADIUS = 10;
    public static final float PLAYER_HEIGHT = 2;
    public static final float[] defUp = {0f, 1f, 0f};
    public static final float[] defSideways = {1f, 0f, 0f};
    public static final float[] defLookAt = {0f, 0f, -1f};
    public static final float[] defPos = {0f, PLAYER_HEIGHT, 0f};

    private Camera camera;
    private CollisionManager collisionManager;


    public Player(Vec3 pos, Vec3 up, Vec3 lookAt, Vec3 sideways) {
        super(pos, PLAYER_RADIUS, PLAYER_HEIGHT);
        this.camera = new Camera(pos, up, lookAt, sideways);
        this.collisionManager = new CollisionManager(this);
    }

    public Player() {
        super(new Vec3(defPos.clone()), PLAYER_RADIUS, PLAYER_HEIGHT);
        this.camera = new Camera(new Vec3(defPos.clone()),
                new Vec3(defUp.clone()),
                new Vec3(defLookAt.clone()),
                new Vec3(defSideways.clone()));
        this.collisionManager = new CollisionManager(this);
    }

    public void addCollidable(Collidable c) {
        this.collisionManager.addCollidable(c);
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
