/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package CollidableDrawables;

import Collidables.*;
import Drawables.Drawable;
import Main.Player;
import Main.RadiusCollider;
import Main.Vec3;

import javax.media.opengl.GL2;

public class Portal extends CollidableDrawable {
    //    public static int portalId;
    private static PortalParams bluePortalData = new PortalParams();
    private static PortalParams orangePortalData = new PortalParams();

    private static Vec3 blueColor = new Vec3(0, 0.633f, 0.957f);
    private static Vec3 orangeColor = new Vec3(1f, 0.6445f, 0);
    private static float portalWidth = Player.PLAYER_HEIGHT / 2;

    private static class PortalParams {
        private Vec3 minVals = new Vec3();
        private Vec3 maxVals = new Vec3();
        private boolean northOrSouth;

        private PortalParams() {
            this.maxVals = new Vec3();
            this.minVals = new Vec3();
            this.northOrSouth = false;
        }

        private PortalParams(Vec3 minVals, Vec3 maxVals, boolean northOrSouth) {
            this.minVals = minVals;
            this.maxVals = maxVals;
            this.northOrSouth = northOrSouth;
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
                        gl.glDisable(GL2.GL_TEXTURE_2D);
                        if (isBlue){
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
                        gl.glColor3f(1, 1, 1);
                        gl.glEnable(GL2.GL_TEXTURE_2D);
                    }

                    @Override
                    public void timePassed() {
                        // Nothing.
                    }
                });
    }

    public Portal(RadiusCollider rc, Collidable c, boolean isBlue) {
        this(AABBCollisionResolver.getInstance(), AABBCollisionDetector.getInstance(), rc, c, isBlue);
    }


    @Override
    public boolean detectCollision(Collidable player) {
        return super.detectCollision(player);
    }

    private static Vec3 initPortal(RadiusCollider rc, Collidable c, boolean isBlue) {
        PortalParams params = isBlue ? bluePortalData : orangePortalData;
        Vec3 minVals = params.getMinVals(),
                maxVals = params.getMaxVals();
        float minX = c.getMinVals().getX(), maxX = c.getMaxVals().getX(),
                minZ = c.getMinVals().getZ(), maxZ = c.getMaxVals().getZ(),
                posX = rc.getPos().getX(), posZ = rc.getPos().getZ();
        boolean north = posZ < minZ,
                south = posZ > maxZ,
                east = posX > maxX,
                west = posX < minX;
        float halfPortalWidth = portalWidth / 2;
        System.out.println(posX + ", " + posZ);

        maxVals.setY(Player.PLAYER_HEIGHT);
        minVals.setY(0);
        if (!(west || east)) {
            float v1 = Math.min(posZ, (minZ + maxZ) / 2),
                    v2 = v1 == posZ ? (minZ + maxZ) / 2 : posZ;
            params.setNorthOrSouth(true);
            minVals.setZ(v1);
            maxVals.setZ(v2);
            minVals.setX(posX - halfPortalWidth);
            maxVals.setX(posX + halfPortalWidth);
        } else if (!(north || south)) {
            float v1 = Math.min(posX, (minX + maxX) / 2),
                    v2 = v1 == posX ? (minX + maxX) / 2 : posX;
            params.setNorthOrSouth(false);
            minVals.setX(v1);
            maxVals.setX(v2);
            minVals.setZ(posZ - halfPortalWidth);
            maxVals.setZ(posZ + halfPortalWidth);
        }
        return maxVals;
    }
}
