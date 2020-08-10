package Collidables;

public interface CollisionDetector {
    // Check if the drawables intersected
    public boolean detectCollision(Collidable player, Collidable c);
}
