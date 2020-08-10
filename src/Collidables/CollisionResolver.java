package Collidables;

import Main.Vec3;

public interface CollisionResolver {
    public void resolveCollision(Vec3 playerPos, Collidable c);
}
