/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Main;

import Collidables.TranslatedCollidable;

public class Projectile extends RadiusCollider {
    private TranslatedCollidable translatedCollidable;
    private boolean isLeftClick;
    public static final float PROJECTILE_RADIUS = 0.3f;
    public static final float PROJECTILE_HEIGHT = 0.3f;

    public Projectile(Vec3 pos, TranslatedCollidable translatedCollidable, boolean isLeftClick) {
        super(pos, PROJECTILE_RADIUS, PROJECTILE_HEIGHT);
        this.translatedCollidable = translatedCollidable;
        this.isLeftClick = isLeftClick;
    }

    public void translate(Vec3 params) {
        this.translatedCollidable.translate(params);
        this.pos = Vec3.sum(this.pos, params);
    }

    public boolean getIsLeftClick() {
        return isLeftClick;
    }
}
