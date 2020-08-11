/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Main;

import Collidables.TranslatedCollidable;

public class Projectile extends RadiusCollider {
    private TranslatedCollidable translatedCollidable;
    public static final float PROJECTILE_RADIUS = 0.001f;
    public static final float PROJECTILE_HEIGHT = 0.001f;

    public Projectile(Vec3 pos, TranslatedCollidable translatedCollidable) {
        super(pos, PROJECTILE_RADIUS, PROJECTILE_HEIGHT);
        this.translatedCollidable = translatedCollidable;
    }

    public void translate(Vec3 params) {
        this.translatedCollidable.translate(params);
    }
}
