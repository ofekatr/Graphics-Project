/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Collidables;

import Main.Player;
import Main.RadiusCollider;
import Main.Vec3;

import java.util.ArrayList;
import java.util.List;

public class CollisionManager {
    private static CollisionManager projectilesCollisionManager;
    private List<Collidable> collidables;

    public CollisionManager(List<Collidable> collidables) {
        this.collidables = collidables;
    }

    public CollisionManager() {
        this.collidables = new ArrayList<>();
    }

    public static CollisionManager getProjectilesCollisionManager(){
        if (projectilesCollisionManager == null){
            projectilesCollisionManager = new CollisionManager();
        }
        return projectilesCollisionManager;
    }

    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    public boolean handleCollisions(RadiusCollider rc, boolean isProjectile) {
        Collidable entityCollidable = new PlayerCollidable(rc.getPos(), rc.getRadius(), rc.getHeight());
        for (Collidable c : this.collidables) {
            if (c.detectCollision(entityCollidable)) {
                c.resolveCollision(rc, isProjectile);
                return true;
            }
        }
        return false;
    }

}
