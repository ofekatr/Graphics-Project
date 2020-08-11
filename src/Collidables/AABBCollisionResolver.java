/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Collidables;

import Main.Player;
import Main.RadiusCollider;
import Main.Vec3;

public class AABBCollisionResolver implements CollisionResolver {

    private static AABBCollisionResolver instance;

    private AABBCollisionResolver() {
    }

    public static AABBCollisionResolver getInstance() {
        if (instance == null) {
            instance = new AABBCollisionResolver();
        }
        return instance;
    }

    @Override
    public void resolveCollision(RadiusCollider rc, Collidable c) {

        Vec3 pos = rc.getPos();
        Vec3 colMinVals = c.getMinVals(), colMaxVals = c.getMaxVals();

        float minX = colMinVals.getX(),
                maxX = colMaxVals.getX(),
                minZ = colMinVals.getZ(),
                maxZ = colMaxVals.getZ();
        float dWest = Math.abs(pos.getX() - (minX - rc.getRadius())),
                dEast = Math.abs(pos.getX() - (maxX + rc.getRadius())),
                dNorth = Math.abs(pos.getZ() - (minZ - rc.getRadius())),
                dSouth = Math.abs(pos.getZ() - (maxZ + rc.getRadius()));

        float dMin = Math.min(Math.min(dWest, dEast), Math.min(dNorth, dSouth));

        if (dMin == dWest) {
            this.resolveWest(rc, minX);
            return;
        }
        if (dMin == dEast) {
            this.resolveEast(rc, maxX);
            return;
        }
        if (dMin == dNorth) {
            this.resolveNorth(rc, minZ);
            return;
        }
        if (dMin == dSouth) {
            this.resolveSouth(rc, maxZ);
            return;
        }
        throw new RuntimeException("Reached an unexpected end of switch case in AABBCollisionResolver.");
    }

    private void resolveWest(RadiusCollider rc, float west) {
        rc.getPos().setX(west - rc.getRadius());
    }

    private void resolveEast(RadiusCollider rc, float east) {
        rc.getPos().setX(east + rc.getRadius());
    }

    private void resolveNorth(RadiusCollider rc, float north) {
        rc.getPos().setZ(north - rc.getRadius());
    }

    private void resolveSouth(RadiusCollider rc, float south) {
        rc.getPos().setZ(south + rc.getRadius());
    }
}
