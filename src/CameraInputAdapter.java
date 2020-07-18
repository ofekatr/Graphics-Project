/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import java.awt.event.*;

public class CameraInputAdapter extends Player implements MouseListener, MouseMotionListener, KeyListener {
    private MouseInputHandler mouse;
    private KeyboardInputHandler keyboard;

    public CameraInputAdapter(Vec3 pos, Vec3 x, Vec3  y, Vec3 z) {
        super(pos, x, y, z);
        this.initializeHandlers();
    }

    public CameraInputAdapter() {
        this.initializeHandlers();
    }

    public void addKeyHandler(char ch, Runnable r) {
        this.keyboard.addKeyHandler(ch, r);
    }

    private void initializeHandlers() {
        this.mouse = new MouseInputHandler(this);
        this.keyboard = new KeyboardInputHandler(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        this.keyboard.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.keyboard.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keyboard.keyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.mouse.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.mouse.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.mouse.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.mouse.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.mouse.mouseExited(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.mouse.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.mouse.mouseMoved(e);
    }
}
