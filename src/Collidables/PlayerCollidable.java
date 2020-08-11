/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Collidables;

import Main.Vec3;

public class PlayerCollidable extends Collidable {

    public PlayerCollidable(Vec3 playerPos, float playerRadius, float playerHeight) {
        super(defColResolver, defColDetector,
                new Vec3(playerPos.getX() - playerRadius, playerPos.getY() - playerHeight,
                        playerPos.getZ() - playerRadius),
                new Vec3(playerPos.getX() + playerRadius, playerPos.getY(), playerPos.getZ() + playerRadius));
    }

}
