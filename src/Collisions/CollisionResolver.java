package Collisions;

import Main.Player;
import Main.Vec3;

public interface CollisionResolver {
    public void resolve(Player player, Collidable c);
}
