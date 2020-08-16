/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Main;

import CollidableDrawables.CollidableDrawable;

public class PortalAdder {
    private CollidableDrawable portalInstance;
    private CollidablesAndDrawablesManager manager;

    public PortalAdder(CollidablesAndDrawablesManager manager) {
        this.manager = manager;
    }

    public void addPortal(CollidableDrawable portal) {
        if (this.portalInstance != null) {
            this.removePortal(this.portalInstance);
        }
        this.portalInstance = portal;
        this.manager.addCollidableDrawable(portal);
    }

    public void removePortal(CollidableDrawable portal) {
        this.manager.removeCollidableDrawable(portal);
    }
}
