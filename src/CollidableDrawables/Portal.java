/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package CollidableDrawables;

import Collidables.CollisionDetector;
import Collidables.CollisionResolver;
import Drawables.Drawable;
import Drawables.TexturedLoadedDrawable;
import Main.PortalAdder;
import Main.RadiusCollider;
import Main.Vec3;

public class Portal extends CollidableDrawable {
    private PortalAdder portalAdder;
    private static int portalId;
    private static String txtureName;
    private static Vec3 minVals;
    private static Vec3 maxVals;
    private static Portal instance;

    public Portal(CollisionResolver cResolver, CollisionDetector cDetector, Vec3 minVals, Vec3 maxVals, Drawable drawable, PortalAdder portalAdder) {
        super(cResolver, cDetector, minVals, maxVals, drawable);
        this.portalAdder = portalAdder;
    }

    public Portal(Vec3 minVals, Vec3 maxVals, Drawable drawable, PortalAdder portalAdder) {
        super(minVals, maxVals, drawable);
        this.portalAdder = portalAdder;
    }

    public static Portal getInstance(CollisionResolver cResolver, CollisionDetector cDetector, Vec3 minVals, Vec3 maxVals,
                                     Drawable drawable, PortalAdder portalAdder) {
        if (instance != null) {
            instance.portalAdder.removePortal(instance);
        }
        return new Portal(cResolver, cDetector, minVals, maxVals, drawable, portalAdder);
    }

    public static Portal getInstance(Vec3 minVals, Vec3 maxVals, Drawable drawable, PortalAdder portalAdder) {
        if (instance != null) {
            instance.portalAdder.removePortal(instance);
        }
        return new Portal(minVals, maxVals, drawable, portalAdder);
    }

    public static void setParams(String textureName1, int portalId1, Vec3 minVals1, Vec3 maxVals1) {
        txtureName = textureName1;
        portalId = portalId1;
        minVals = minVals1;
        maxVals = maxVals1;
    }

    @Override
    public void resolveCollision(RadiusCollider rc, boolean isProjectile) {
        super.resolveCollision(rc, isProjectile);
        if (isProjectile) {
//            this.createPortal();
        }
    }

    private void createPortal() {
        this.portalAdder.addPortal(new CollidableDrawable(minVals, maxVals,
                new TexturedLoadedDrawable(txtureName, portalId)));
    }
}
