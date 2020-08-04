package InputHandlers;/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import Main.Axes;
import Main.Game;
import Main.Player;

import java.awt.*;
import java.awt.event.*;

public class MouseInputHandler implements MouseMotionListener, MouseListener {
    private Player camera;
    private Point prev;
    private boolean ignore;

    public MouseInputHandler(Player camera) {
        this.camera = camera;
        this.ignore = false;
        this.prev = MouseInfo.getPointerInfo().getLocation();
    }

    private Point calcMidPoint() {
        return new Point(Game.width / 2, Game.height / 2);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point midPoint = this.calcMidPoint();
        int midX = midPoint.x, midY = midPoint.y;
        if (!this.ignore) {
//            10 38
//            8 32
            int x = e.getX() + 10, y = e.getY() + 38;
//            int x = e.getX(), y = e.getY();
            float xDiff = Math.abs(midX - x), yDiff = Math.abs(midY - y);
//            System.out.println(xDiff + " " + yDiff);
            float angX = this.calcAngle(midX, x, Game.width);
            this.camera.rotatef(angX, Axes.Y);
            float angY = this.calcAngle(midY, y, Game.height);
            this.camera.rotatef(angY, Axes.X);
            this.prev = e.getPoint();
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
        try {
            // These coordinates are screen coordinates
            Point midPoint = this.calcMidPoint();
            int xCoord = midPoint.x, yCoord = midPoint.y;

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
