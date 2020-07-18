/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import java.awt.*;
import java.awt.event.*;

public class MouseInputHandler implements MouseMotionListener, MouseListener {
    private Player camera;
    private Point prev;
    private int midX;
    private int midY;
    private boolean ignore;

    public MouseInputHandler(Player camera) {
        this.camera = camera;
        this.midX = ex2.width / 2;
        this.midY = ex2.height / 2;
        this.ignore = false;
        this.prev = MouseInfo.getPointerInfo().getLocation();
    }

    public MouseInputHandler(Player camera, int midX, int midY) {
        this.camera = camera;
        this.midX = midX;
        this.midY = midY;
        this.ignore = false;
        this.prev = MouseInfo.getPointerInfo().getLocation();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!this.ignore) {
//            10 38
            int x = e.getX() + 8, y = e.getY() + 32;
            float xDiff = Math.abs(this.midX - x), yDiff = Math.abs(this.midY - y);
            System.out.println(xDiff + " " + yDiff);
            float angX = this.calcAngle(this.midX, x, ex2.width);
            this.camera.rotatef(angX, Axes.Y);
            float angY = this.calcAngle(this.midY, y, ex2.height);
            this.camera.rotatef(angY, Axes.X);
            this.prev = e.getPoint();
            this.centerMouse();
        }
        this.ignore = !this.ignore;
    }


    private float calcAngle(int prev, int curr, int range) {
//        float angle = (float) ((curr - prev) / range) * 180;
        float angle = curr - prev;
        return - angle * 0.04f;
    }

    private void centerMouse() {
        try {
            // These coordinates are screen coordinates
            int xCoord = this.midX;
            int yCoord = this.midY;

            // Move the cursor
            Robot robot = new Robot();
            robot.mouseMove(xCoord, yCoord);
        } catch (AWTException ex) {
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        centerMouse();
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        centerMouse();
    }
}
