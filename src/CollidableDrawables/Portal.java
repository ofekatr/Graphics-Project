/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package CollidableDrawables;

import Collidables.*;
import Drawables.Drawable;
import Main.*;

import javax.media.opengl.GL2;

enum Direction {
    West(90),
    East(270),
    North(0),
    South(180);

    private float angle;

    private Direction(float angle) {
        this.angle = angle;
    }

    public float getAngle() {
        return angle;
    }
}

public class Portal extends CollidableDrawable {
    //    public static int portalId;
    private static PortalParams bluePortalData = new PortalParams();
    private static PortalParams orangePortalData = new PortalParams();
    private static Vec3 blueColor = new Vec3(0, 0.633f, 0.957f);
    private static Vec3 orangeColor = new Vec3(1f, 0.6445f, 0);
    private static float portalWidth = Player.PLAYER_HEIGHT / 2;

    private boolean isBlue;
    private static class PortalParams {
        private Vec3 minVals;
        private Vec3 maxVals;
        private Direction dir;
        private Vec3 newPos;
        private boolean northOrSouth;
        private boolean init;

        private PortalParams() {
            this.maxVals = new Vec3();
            this.minVals = new Vec3();
            this.newPos = new Vec3();
            this.northOrSouth = false;
            this.dir = Direction.West;
            this.init = false;
        }

        private PortalParams(Vec3 minVals, Vec3 maxVals, boolean northOrSouth, Direction dir) {
            this.minVals = minVals;
            this.maxVals = maxVals;
            this.northOrSouth = northOrSouth;
            this.dir = dir;
            this.init = false;
        }

        private Vec3 getMinVals() {
            return minVals;
        }

        private Vec3 getMaxVals() {
            return maxVals;
        }

        private boolean isNorthOrSouth() {
            return northOrSouth;
        }

        private void setMinVals(Vec3 minVals) {
            this.minVals = minVals;
        }

        private void setMaxVals(Vec3 maxVals) {
            this.maxVals = maxVals;
        }

        private void setNorthOrSouth(boolean northOrSouth) {
            this.northOrSouth = northOrSouth;
        }

    }

    public Portal(CollisionResolver cResolver, CollisionDetector cDetector, RadiusCollider rc, Collidable c,
                  boolean isBlue) {
        super(cResolver, cDetector, isBlue ? bluePortalData.getMinVals() : orangePortalData.getMinVals(),
                initPortal(rc, c, isBlue), new Drawable() {
                    @Override
                    public void draw(GL2 gl) {
                        PortalParams params = isBlue ? bluePortalData : orangePortalData;
                        Vec3 pos = rc.getPos();
                        gl.glDisable(GL2.GL_LIGHTING);
                        gl.glDisable(GL2.GL_TEXTURE_2D);
                        if (isBlue) {
                            gl.glColor3f(blueColor.getX(), blueColor.getY(), blueColor.getZ());
                        } else {
                            gl.glColor3f(orangeColor.getX(), orangeColor.getY(), orangeColor.getZ());
                        }
                        gl.glBegin(GL2.GL_POLYGON);
                        {
                            if (params.isNorthOrSouth()) {
                                for (int i = 0; i < 360; i++) {
                                    float rad = i * 3.14159f / 180.0f;
                                    gl.glVertex3f(pos.getX() + (float) Math.cos(rad) * portalWidth / 2,
                                            pos.getY() + (float) Math.sin(rad) * Player.PLAYER_HEIGHT / 2,
                                            pos.getZ());
                                }
                            } else {
                                for (int i = 0; i < 360; i++) {
                                    float rad = i * 3.14159f / 180.0f;
                                    gl.glVertex3f(pos.getX(),
                                            pos.getY() + (float) Math.sin(rad) * Player.PLAYER_HEIGHT / 2,
                                            pos.getZ() + (float) Math.cos(rad) * portalWidth / 2);
                                }
                            }
                        }
                        gl.glEnd();
                        gl.glEnable(GL2.GL_LIGHTING);
                        gl.glColor3f(1, 1, 1);
                        gl.glEnable(GL2.GL_TEXTURE_2D);
                    }

                    @Override
                    public void timePassed() {
                        // Nothing.
                    }
                });
        this.isBlue = isBlue;
    }

    public Portal(RadiusCollider rc, Collidable c, boolean isBlue) {
        this(AABBCollisionResolver.getInstance(), AABBCollisionDetector.getInstance(), rc, c, isBlue);
    }

    private static void translate(boolean toBlue) {
        Camera cam = Camera.getInstance();
        Vec3 camPos = cam.getPos(),
                newPos = toBlue ? bluePortalData.newPos : orangePortalData.newPos;
        Vec3 diffs = new Vec3(newPos.getX() - camPos.getX(),
                0,
                newPos.getZ() - camPos.getZ());
        cam.translatef(diffs.getX(), diffs.getY(), diffs.getZ(), false);
    }

    private static void rotate(boolean toBlue) {
        Camera cam = Camera.getInstance();
        float source, dest, angDiff;
        if (toBlue) {
            source = orangePortalData.dir.getAngle();
            dest = bluePortalData.dir.getAngle();
        } else {
            source = bluePortalData.dir.getAngle();
            dest = orangePortalData.dir.getAngle();
        }
        source -= 180;
        angDiff = dest - source;
        angDiff = angDiff % 360;
        cam.rotatef(angDiff, Axes.Y, false);
    }

    private static void teleport(boolean toBlue) {
        PortalParams params = toBlue ? bluePortalData : orangePortalData;
        if (params.init){
            translate(toBlue);
            rotate(toBlue);
        }
    }

    @Override
    public void resolveCollision(RadiusCollider rc, boolean isProjectile) {
        teleport(!this.isBlue);
    }

    private static Vec3 initPortal(RadiusCollider rc, Collidable c, boolean isBlue) {
        PortalParams params = isBlue ? bluePortalData : orangePortalData;
        params.init = true;
        Vec3 minVals = params.getMinVals(),
                maxVals = params.getMaxVals();
        float minX = c.getMinVals().getX(), maxX = c.getMaxVals().getX(),
                minZ = c.getMinVals().getZ(), maxZ = c.getMaxVals().getZ(),
                posX = rc.getPos().getX(), posY = rc.getPos().getY(), posZ = rc.getPos().getZ();
        boolean north = posZ < minZ,
                south = posZ > maxZ,
                east = posX > maxX,
                west = posX < minX;
        float halfPortalWidth = portalWidth / 2;
        float EPSILON = 0.01f;

        maxVals.setY(Player.PLAYER_HEIGHT);
        minVals.setY(0);
        params.newPos = rc.getPos().clone();
        params.newPos.setY(posY + rc.getHeight() / 2);
        // North/South case.
        if (!(west || east)) {
            float v1 = Math.min(posZ, (minZ + maxZ) / 2),
                    v2 = v1 == posZ ? (minZ + maxZ) / 2 : posZ;
            boolean isNorth = v1 == posZ;
            params.setNorthOrSouth(true);
            params.dir = isNorth ? Direction.North : Direction.South;
            params.newPos.setZ(posZ + (isNorth ? -1 : 1) * Player.PLAYER_RADIUS + EPSILON);
            minVals.setZ(v1);
            maxVals.setZ(v2);
            minVals.setX(posX - halfPortalWidth);
            maxVals.setX(posX + halfPortalWidth);

        } else if (!(north || south)) { // West/East case.
            float v1 = Math.min(posX, (minX + maxX) / 2),
                    v2 = v1 == posX ? (minX + maxX) / 2 : posX;
            boolean isWest = v1 == posX;
            params.setNorthOrSouth(false);
            params.dir = v1 == posX ? Direction.West : Direction.East;
            params.newPos.setX(posX + (isWest ? -1 : 1) * Player.PLAYER_RADIUS + EPSILON);
            minVals.setX(v1);
            maxVals.setX(v2);
            minVals.setZ(posZ - halfPortalWidth);
            maxVals.setZ(posZ + halfPortalWidth);
        }
        return maxVals;
    }
}
