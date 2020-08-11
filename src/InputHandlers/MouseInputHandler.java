package InputHandlers;/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import CollidableDrawables.TranslatedCollidableDrawable;
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

    public MouseInputHandler(Camera camera) {
        this.camera = camera;
        this.ignore = false;
    }

    private Point calcMidPoint() {
        return Game.calcMidPoint();
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
            int x = e.getX() + 8, y = e.getY() + 31;
//            int x = e.getX(), y = e.getY();
            float xDiff = Math.abs(midX - x), yDiff = Math.abs(midY - y);
            float angX = this.calcAngle(midX, x, Game.width);
            this.camera.rotatef(angX, Axes.Y);
            float angY = this.calcAngle(midY, y, Game.height);
            this.camera.rotatef(angY, Axes.X);
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
        System.out.println(SwingUtilities.isLeftMouseButton(e) ? "Left" : "Right");
        Vec3 dir = new Vec3(MathUtils.normalize(this.camera.getLookAt().getArray()));
        new Thread(() -> {
            Projectile proj = new Projectile(this.camera.getPos(),
                    new TranslatedCollidable(this.camera.getPos(), 0.001f, 0.001f,
                            new Vec3(0, 0, 0)));

            CollisionManager collisionManager = CollisionManager.getProjectilesCollisionManager();
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
                proj.translate(new Vec3(MathUtils.vectorScalarProduct(dir.getArray(), 0.1f)));
                if (collisionManager.handleCollisions(proj))
                    break;
            }
            System.out.println("No collisions occurred.");
        }).start();

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
