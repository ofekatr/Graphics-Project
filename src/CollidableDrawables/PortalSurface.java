/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package CollidableDrawables;

import Collidables.CollisionDetector;
import Collidables.CollisionResolver;
import Drawables.Drawable;
import Main.PortalAdder;
import Main.Projectile;
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
            Projectile p = (Projectile) rc;
            this.createPortal(rc, p.getIsLeftClick());
        }
    }

    private void createPortal(RadiusCollider rc, boolean isBlue) {
        this.portalAdder.addPortal(new Portal(rc, this, isBlue), isBlue);
    }
}
