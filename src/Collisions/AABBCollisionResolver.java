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
    public void resolve(Player player, Collidable c) {
        Vec3 pos = player.getCamera().getPos(),
                minX = c.getMinX(),
                maxX = c.getMaxX(),
                minZ = c.getMinZ(),
                maxZ = c.getMaxZ();
        float dWest = Math.abs(pos.getX() - minX.getX()),
                dEast = Math.abs(pos.getX() - maxX.getX()),
                dNorth = Math.abs(pos.getZ() - minZ.getZ()),
                dSouth = Math.abs(pos.getZ() - maxZ.getZ());
        float dMin = Math.min(Math.min(dWest, dEast), Math.min(dNorth, dSouth));
        if (dMin == dWest)
            this.resolveWest(player, minX);
        if (dMin == dEast)
            this.resolveEast(player, maxX);
        if (dMin == dNorth)
            this.resolveNorth(player, minZ);
        if (dMin == dSouth)
            this.resolveSouth(player, maxZ);
        throw new RuntimeException("Reached an unexpected end of switch case in AABBCollisionResolver.");
    }

    private void resolveWest(Player player, Vec3 point) {
        float west = point.getX();
        Camera playerCam = player.getCamera();
        Vec3 pos = playerCam.getPos();
        playerCam.setPos(new Vec3(new float[]{west - Player.PLAYER_RADIUS, pos.getY(), pos.getZ()}));
    }

    private void resolveEast(Player player, Vec3 point) {
        float east = point.getX();
        Camera playerCam = player.getCamera();
        Vec3 pos = playerCam.getPos();
        playerCam.setPos(new Vec3(new float[]{east + Player.PLAYER_RADIUS, pos.getY(), pos.getZ()}));
    }

    private void resolveNorth(Player player, Vec3 point) {
        float north = point.getZ();
        Camera playerCam = player.getCamera();
        Vec3 pos = playerCam.getPos();
        playerCam.setPos(new Vec3(new float[]{pos.getX(), pos.getY(), north - Player.PLAYER_RADIUS}));
    }

    private void resolveSouth(Player player, Vec3 point) {
        float south = point.getZ();
        Camera playerCam = player.getCamera();
        Vec3 pos = playerCam.getPos();
        playerCam.setPos(new Vec3(new float[]{pos.getX(), pos.getY(), south + Player.PLAYER_RADIUS}));
    }
}
