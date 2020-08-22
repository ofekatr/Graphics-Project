package InputHandlers;/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import Collidables.CollisionManager;
import Collidables.TranslatedCollidable;
import Main.*;
import MathLib.MathUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseInputHandler implements MouseMotionListener, MouseListener {
    private Camera camera;
    private boolean ignore;
    private Robot robot;
    private boolean init;
    private int xOffset;
    private int yOffset;

    public MouseInputHandler(Camera camera) {
        this.camera = camera;
        this.ignore = false;
        this.init = true;
        try {
            this.robot = new Robot();
        } catch (AWTException ex) {
            throw new RuntimeException("Failed creating robot for MouseInputHandler.");
        }
    }

    private Point calcMidPoint() {
        return Game.calcMidPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (init) {
            this.centerMouse();
            return;
        }
        Point midPoint = this.calcMidPoint();
        int midX = midPoint.x, midY = midPoint.y;
        if (!this.ignore) {
//            10 38
//            8 32
            int x = e.getX(), y = e.getY();
//            int x = e.getX(), y = e.getY();
            float angX = this.calcAngle(midX, x, Game.width);
            angX = Math.abs(angX) > 1 ? angX / Math.abs(angX) : angX;
            this.camera.rotatef(angX, Axes.Y, true);
            float angY = this.calcAngle(midY, y, Game.height);
            angY = Math.abs(angY) > 1 ? angY/ Math.abs(angY) : angY;
            this.camera.rotatef(angY, Axes.X, true);
            this.centerMouse();
        }
        this.ignore = !this.ignore;
    }


    private float calcAngle(int prev, int curr, int range) {
//        float angle = (float) ((curr - prev) / range) * 180;
        float angle = curr - prev;
        return -angle * 0.04f;
    }

    private void centerMouse() {
        // These coordinates are screen coordinates
        Point midPoint = this.calcMidPoint();
        int xCoord = midPoint.x + this.xOffset, yCoord = midPoint.y + this.yOffset;

        // Move the cursor
        this.robot.mouseMove(xCoord, yCoord);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e) || this.camera.isFrozen()){
            return;
        }
        Vec3 dir = new Vec3(MathUtils.normalize(this.camera.getLookAt().getArray()));
        new Projectile(this.camera.getPos(), dir, SwingUtilities.isLeftMouseButton(e));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (this.init && !this.camera.isFrozen()) {
            this.xOffset = this.calcMidPoint().x - e.getX();
            this.yOffset = this.calcMidPoint().y - e.getY();
            this.init = false;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        centerMouse();
    }
}
