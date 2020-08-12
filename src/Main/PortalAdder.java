/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Main;

import CollidableDrawables.CollidableDrawable;
import Collidables.Collidable;
import Drawables.Drawable;

import java.util.List;

public class PortalAdder {
    private CollidablesAndDrawablesManager manager;

    public PortalAdder(CollidablesAndDrawablesManager manager) {
        this.manager = manager;
    }

    public void addPortal(CollidableDrawable portal) {
        this.manager.addCollidableDrawable(portal);
    }

    public void removePortal(CollidableDrawable portal){
        this.manager.removeCollidableDrawable(portal);
    }
}
