package InputHandlers;/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import Collidables.CollisionManager;
import Main.Camera;
import Main.ObservablesManager;
import Main.Player;
import Main.Vec3;

import java.awt.event.*;

public class CameraInputHandler implements MouseListener, MouseMotionListener, KeyListener {
    private MouseInputHandler mouse;
    private KeyboardInputHandler keyboard;
    private Camera camera;
    private CollisionManager collisionManager;

    public CameraInputHandler(Camera camera) {
        this.camera = camera;
        this.initializeHandlers();
    }

    public void addKeyHandler(char ch, Runnable r) {
        this.keyboard.addKeyHandler(ch, r);
    }

    public void setCollisionManager(CollisionManager collisionManager){
        this.collisionManager = collisionManager;
        this.keyboard.setCollisionManager(this.collisionManager);
    }

    private void initializeHandlers() {
        this.mouse = new MouseInputHandler(this.camera);
        this.keyboard = new KeyboardInputHandler(this.camera);
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
