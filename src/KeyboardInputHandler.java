/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

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
    private Player player;

    public KeyboardInputHandler(Player player) {
        this.player = player;
        initializeMap();
    }

    public KeyboardInputHandler() {
        this.player = new Player();
        initializeMap();
    }

//    public Vec3 getPos() {
//        return this.player.getCamera().getPos();
//    }
//
//    public Vec3 getUp() {
//        return this.player.getCamera().getUp();
//    }
//
//    public Vec3 getLookAt() {
//        return this.player.getCamera().getLookAt();
//    }

    private void initializeMap() {
        this.addKeyHandler(UP, () -> this.player.translatef(0f, 0f, 1));
        this.addKeyHandler(DOWN, () -> this.player.translatef(0f, 0f, -1f));
        this.addKeyHandler(LEFT, () -> this.player.translatef(-1f, 0f, 0f));
        this.addKeyHandler(RIGHT, () -> this.player.translatef(1f, 0f, 0f));
    }


    @Override
    public void keyPressed(KeyEvent e) {
        char keyChar = e.getKeyChar();
        this.pressed.add(keyChar);
        for (char ch : this.pressed) {
            Runnable r = this.map.get(ch);
            if (r != null)
                r.run();
        }
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
        this.pressed.remove(keyChar);
    }
}