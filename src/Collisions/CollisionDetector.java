package Collisions;

import Main.Vec3;

public interface CollisionDetector {
    // Check if the drawables intersected
    public boolean detectCollision(Collidable player, Collidable c);
}
