package Collisions;

import Main.Vec3;

public interface Collidable {
    public boolean intersects(Collidable player);

    public Vec3 resolveCollision(Vec3 oldPos);

}
