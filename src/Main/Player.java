package Main;/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import Collidables.Collidable;
import Collidables.CollisionManager;
import MathLib.MathUtils;

public class Player extends RadiusCollider {
    public static final float PLAYER_RADIUS = 1.5f;
    public static final float PLAYER_HEIGHT = 3;
    private static final float[] defUp = {0f, 1f, 0f};
    private static final float[] defSideways = {1f, 0f, 0f};
    private static final float[] defLookAt = {0f, 0f, -1f};
    private static final float[] defPos = {0f, PLAYER_HEIGHT, 0f};


    private Camera camera;
    private CollisionManager collisionManager;


    public Player(Vec3 pos, Vec3 up, Vec3 lookAt, Vec3 sideways) {
        super(pos, PLAYER_RADIUS, PLAYER_HEIGHT);
//        super(pos, PLAYER_RADIUS, PLAYER_HEIGHT);
        this.camera = new Camera(pos, up, lookAt, sideways);
        this.collisionManager = new CollisionManager();
    }

    public Player() {
        super(new Vec3(defPos.clone()), PLAYER_RADIUS, PLAYER_HEIGHT);
//        super(new Vec3(defPos.clone()), PLAYER_RADIUS, PLAYER_HEIGHT);
        this.camera = new Camera(new Vec3(defPos.clone()),
                new Vec3(defUp.clone()),
                new Vec3(defLookAt.clone()),
                new Vec3(defSideways.clone()));
        this.collisionManager = new CollisionManager();
        this.camera.getInputHandler().setCollisionManager(this.collisionManager);
    }

    @Override
    public Vec3 getPos() {
        return this.camera.getPos();
    }

    public void addCollidable(Collidable c) {
        this.collisionManager.addCollidable(c);
    }

//    public void translatef(float x, float y, float z) {
//        this.camera.translatef(x, y, z);
//    }

    public Vec3 getLookAt() {
        return new Vec3(MathUtils.sumVectors(this.camera.getPos().getArray(), this.camera.getLookAt().getArray()));
//        return this.camera.getLookAt();
    }

    public Vec3 getUp() {
        return this.camera.getUp();
    }

    public Vec3 getSideways() {
        return this.camera.getSideways();
    }

    public Camera getCamera() {
        return camera;
    }
}