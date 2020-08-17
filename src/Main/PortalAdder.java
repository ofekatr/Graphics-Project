/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Main;

import CollidableDrawables.CollidableDrawable;

public class PortalAdder {
    private CollidableDrawable bluePortalInstance;
    private CollidableDrawable orangePortalInstance;
    private CollidablesAndDrawablesManager manager;

    public PortalAdder(CollidablesAndDrawablesManager manager) {
        this.manager = manager;
    }

    public void addPortal(CollidableDrawable portal, boolean isBlue) {
        if (isBlue){
            if (this.bluePortalInstance != null){
                this.removePortal(this.bluePortalInstance);
            }
            this.bluePortalInstance = portal;
        } else {
            if (this.orangePortalInstance != null){
                this.removePortal(this.orangePortalInstance);
            }
            this.orangePortalInstance = portal;
        }
        this.manager.addCollidableDrawable(portal);
    }

    public void removePortal(CollidableDrawable portal) {
        this.manager.removeCollidableDrawable(portal);
    }
}
