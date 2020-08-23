package InputHandlers;/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import Collidables.CollisionManager;
import Drawables.Drawable;
import Drawables.ImageDrawable;
import Main.*;
import com.sun.xml.internal.stream.events.EntityReferenceEvent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.*;

enum Button {
    UP(KeyEvent.VK_W),
    DOWN(KeyEvent.VK_S),
    LEFT(KeyEvent.VK_A),
    RIGHT(KeyEvent.VK_D),
    HELP(KeyEvent.VK_F1),
    NEXT_LEVEL(KeyEvent.VK_F2);

    private int id;

    private Button(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}

public class KeyboardInputHandler extends KeyAdapter {

    private Map<Integer, Runnable> map = new HashMap<>();
    private Set<Integer> pressed = new HashSet<>();
    private Camera camera;
    private CollisionManager collisionManager;

    public KeyboardInputHandler(Camera camera) {
        this.camera = camera;
        initializeMap();
    }

    private void initializeMap() {
        this.addKeyHandler(Button.UP.getId(), () -> this.camera.translatef(0f, 0f, 1, true));
        this.addKeyHandler(Button.DOWN.getId(), () -> this.camera.translatef(0f, 0f, -1f, true));
        this.addKeyHandler(Button.LEFT.getId(), () -> this.camera.translatef(-1f, 0f, 0f, true));
        this.addKeyHandler(Button.RIGHT.getId(), () -> this.camera.translatef(1f, 0f, 0f, true));
        this.addKeyHandler(Button.HELP.getId(), () -> {
            EntitiesCreator.changeHelpVisibilitStatus();
            this.camera.changeFreezeStatus();
        });
        this.addKeyHandler(Button.NEXT_LEVEL.getId(), () -> {
            CollisionManager.getProjectilesCollisionManager().emptyCollidables();
            Game.notifyToLoadLevel2();
        });
    }

    public void setCollisionManager(CollisionManager collisionManager) {
        this.collisionManager = collisionManager;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int id = e.getKeyCode();
        char keyChar = e.getKeyChar();
        this.pressed.add(id);

        for (Integer keyCode : this.pressed) {
            Runnable r = this.map.get(keyCode);
            if (r != null)
                r.run();
        }
        this.collisionManager.handleCollisions(new RadiusCollider(this.camera.getPos(),
                Player.PLAYER_RADIUS, Player.PLAYER_HEIGHT), false);
    }

    protected void addKeyHandler(int id, Runnable f) {
        this.map.put(id, f);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        this.pressed.remove(keyCode);

    }
}