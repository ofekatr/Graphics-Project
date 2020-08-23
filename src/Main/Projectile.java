/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Main;

import Collidables.CollisionManager;
import Collidables.TranslatedCollidable;
import MathLib.MathUtils;

import javax.swing.*;

public class Projectile extends RadiusCollider {
    private TranslatedCollidable translatedCollidable;
    private boolean isLeftClick;
    private Vec3 dir;
    public static final float PROJECTILE_RADIUS = 0.3f;
    public static final float PROJECTILE_HEIGHT = 0.3f;

    public Projectile(Vec3 pos, Vec3 dir, boolean isLeftClick) {
        super(pos, PROJECTILE_RADIUS, PROJECTILE_HEIGHT);
        this.translatedCollidable = new TranslatedCollidable(pos, Projectile.PROJECTILE_RADIUS,
                Projectile.PROJECTILE_HEIGHT,
                new Vec3(0, 0, 0));
        this.isLeftClick = isLeftClick;
        this.dir = dir;
        this.initAndStartThread();

    }

    private void initAndStartThread() {
        new Thread(() -> {
            CollisionManager collisionManager = CollisionManager.getProjectilesCollisionManager();
            for (int i = 0; i < 100000; i++) {
                this.translate(new Vec3(MathUtils.vectorScalarProduct(this.dir.getArray(), 0.1f)));
                if (collisionManager.handleCollisions(this, true)) {
                    break;
                }
            }
        }).start();
    }

    public void translate(Vec3 params) {
        this.translatedCollidable.translate(params);
        this.pos = Vec3.sum(this.pos, params);
    }

    public boolean getIsLeftClick() {
        return isLeftClick;
    }
}
