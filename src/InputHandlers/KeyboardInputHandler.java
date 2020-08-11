package InputHandlers;/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import Collidables.CollisionManager;
import Main.Camera;
import Main.Player;
import Main.RadiusCollider;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

public class KeyboardInputHandler extends KeyAdapter {

    private final char UP = 'w';
    private final char DOWN = 's';
    private final char LEFT = 'a';
    private final char RIGHT = 'd';

    private Map<Character, Runnable> map = new HashMap<>();
    private Set<Character> pressed = new HashSet<>();
    private Camera camera;
    private CollisionManager collisionManager;

    public KeyboardInputHandler(Camera camera) {
        this.camera = camera;
        initializeMap();
    }

    private void initializeMap() {
        this.addKeyHandler(UP, () -> this.camera.translatef(0f, 0f, 1));
        this.addKeyHandler(DOWN, () -> this.camera.translatef(0f, 0f, -1f));
        this.addKeyHandler(LEFT, () -> this.camera.translatef(-1f, 0f, 0f));
        this.addKeyHandler(RIGHT, () -> this.camera.translatef(1f, 0f, 0f));
    }

    public void setCollisionManager(CollisionManager collisionManager) {
        this.collisionManager = collisionManager;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if (Character.isAlphabetic(keyChar)) {
            keyChar = Character.toLowerCase(keyChar);
            this.pressed.add(keyChar);
        }

        for (char ch : this.pressed) {
            Runnable r = this.map.get(ch);
            if (r != null)
                r.run();
        }
        this.collisionManager.handleCollisions(new RadiusCollider(this.camera.getPos(),
                Player.PLAYER_RADIUS, Player.PLAYER_HEIGHT));
    }

    protected void addKeyHandler(char ch, Runnable f) {
        this.map.put(ch, f);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if (Character.isAlphabetic(keyChar)) {
            keyChar = Character.toLowerCase(keyChar);
            this.pressed.remove(keyChar);
        }
    }
}