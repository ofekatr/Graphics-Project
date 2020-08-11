package Collidables;

import Main.RadiusCollider;
import Main.Vec3;

public interface CollisionResolver {
    public void resolveCollision(RadiusCollider rc, Collidable c);
}
