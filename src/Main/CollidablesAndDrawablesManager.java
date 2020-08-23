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
import java.util.Collections;
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

    public boolean containsDrawable(Drawable d) {
        return this.drawables.contains(d);
    }

    public boolean containsCollidable(Collidable c) {
        return this.collidables.contains(c);
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

    public void emptyEntities() {
        for (Collidable c : this.collidables)
            this.collidables.remove(c);
        this.drawables = new CopyOnWriteArrayList<>();
    }

    @Override
    public void draw(GL2 gl) {
        try {
            for (Drawable d : this.drawables) {
                d.draw(gl);
            }
        } catch (NullPointerException e) {

        }
    }

    @Override
    public void timePassed() {
    }
}
