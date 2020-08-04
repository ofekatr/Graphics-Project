/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Collisions;

import Main.Camera;
import Main.Player;
import Main.Vec3;

import static java.lang.Float.max;

public class AABBCollisionResolver implements CollisionResolver {
    @Override
    public void resolveCollision(Vec3 playerPos, Collidable c) {

        Vec3 colMinVals = c.getMinVals(), colMaxVals = c.getMaxVals();

        float minX = colMinVals.getX(),
                maxX = colMaxVals.getX(),
                minZ = colMinVals.getZ(),
                maxZ = colMaxVals.getZ();
        float dWest = Math.abs(playerPos.getX() - minX),
                dEast = Math.abs(playerPos.getX() - maxX),
                dNorth = Math.abs(playerPos.getZ() - minZ),
                dSouth = Math.abs(playerPos.getZ() - maxZ);
        float dMin = Math.min(Math.min(dWest, dEast), Math.min(dNorth, dSouth));
        if (dMin == dWest)
            this.resolveWest(playerPos, minX);
        if (dMin == dEast)
            this.resolveEast(playerPos, maxX);
        if (dMin == dNorth)
            this.resolveNorth(playerPos, minZ);
        if (dMin == dSouth)
            this.resolveSouth(playerPos, maxZ);
        throw new RuntimeException("Reached an unexpected end of switch case in AABBCollisionResolver.");
    }

    private void resolveWest(Vec3 playerPos, float west) {
        playerPos.setX(west - Player.PLAYER_RADIUS);
    }

    private void resolveEast(Vec3 playerPos, float east) {
        playerPos.setX(east + Player.PLAYER_RADIUS);
    }

    private void resolveNorth(Vec3 playerPos, float north) {
        playerPos.setZ(north - Player.PLAYER_RADIUS);
    }

    private void resolveSouth(Vec3 playerPos, float south) {
        playerPos.setZ(south + Player.PLAYER_RADIUS);
    }
}
