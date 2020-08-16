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

public class PortalSurface extends CollidableDrawable {
    private PortalAdder portalAdder;

    public PortalSurface(CollisionResolver cResolver, CollisionDetector cDetector, Vec3 minVals, Vec3 maxVals,
                         Drawable drawable, PortalAdder portalAdder) {
        super(cResolver, cDetector, minVals, maxVals, drawable);
        this.portalAdder = portalAdder;
    }

    public PortalSurface(Vec3 minVals, Vec3 maxVals, Drawable drawable, PortalAdder portalAdder) {
        super(minVals, maxVals, drawable);
        this.portalAdder = portalAdder;
    }

    @Override
    public void resolveCollision(RadiusCollider rc, boolean isProjectile) {
        super.resolveCollision(rc, isProjectile);
        if (isProjectile) {
            this.createPortal(rc.getPos());
        }
    }

    private void createPortal(Vec3 transParams) {
        this.portalAdder.addPortal(new Portal(transParams));
    }
}
