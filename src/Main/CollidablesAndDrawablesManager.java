/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Main;

import CollidableDrawables.CollidableDrawable;
import CollidableDrawables.PortalSurface;
import Collidables.Collidable;
import Collidables.CollisionManager;
import Drawables.Drawable;

import javax.media.opengl.GL2;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollidablesAndDrawablesManager implements Drawable {
    private List<Collidable> collidables;
    private List<Drawable> drawables;
    private PortalAdder portalAdder;

    public CollidablesAndDrawablesManager(List<Collidable> collidables) {
        this.collidables = collidables;
        this.drawables = new CopyOnWriteArrayList<>();
        this.portalAdder = new PortalAdder(this);
    }

    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    public void addDrawable(Drawable d) {
        this.drawables.add(d);
    }

    public void removeDrawable(Drawable d) {
        this.drawables.remove(d);
    }

    public void addProjectileCollidable(Collidable c) {
        CollisionManager.getProjectilesCollisionManager().addCollidable(c);
    }

    public void addCollidableDrawable(CollidableDrawable cd) {
        this.addCollidable(cd);
        this.addDrawable(cd);
    }

    public void removeCollidableDrawable(CollidableDrawable cd) {
        this.removeCollidable(cd);
        this.removeDrawable(cd);
    }

    public void createAndAddPortalSurface(Vec3 minVals, Vec3 maxVals, Drawable drawable) {
        CollidableDrawable surface = new PortalSurface(minVals, maxVals, drawable, this.portalAdder);
        this.addCollidableDrawable(surface);
        this.addProjectileCollidable(surface);
    }

    @Override
    public void draw(GL2 gl) {
        for (Drawable d : this.drawables) {
            d.draw(gl);
        }
    }

    @Override
    public void timePassed() {
    }
}
