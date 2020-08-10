/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Collidables;

import Main.Player;
import Main.Vec3;

import java.util.ArrayList;
import java.util.List;

public class CollisionManager {
    private List<Collidable> collidables;
    private final Player player;
    private final Vec3 playerPos;

    public CollisionManager(List<Collidable> collidables, Player player) {
        this.collidables = collidables;
        this.player = player;
        this.playerPos = player.getCamera().getPos();
    }

    public CollisionManager(Player player) {
        this.player = player;
        this.playerPos = player.getCamera().getPos();
        this.collidables = new ArrayList<>();
    }

    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    public void handleCollisions() {
        Collidable playerC = new PlayerCollidable(this.playerPos, Player.PLAYER_RADIUS, Player.PLAYER_HEIGHT);
        for (Collidable c : this.collidables) {
            if (c.detectCollision(playerC)) {
                c.resolveCollision(this.playerPos);
            }
        }
    }

}
