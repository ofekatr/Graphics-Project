/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package CollidableDrawables;

import Collidables.Collidable;
import Collidables.CollisionDetector;
import Collidables.CollisionResolver;
import Drawables.Drawable;
import Main.Player;
import Main.RadiusCollider;
import Main.Vec3;

import javax.media.opengl.GL2;

public class Portal extends CollidableDrawable {
    //    public static int portalId;
    private static Vec3 minVals = new Vec3();
    private static Vec3 maxVals = new Vec3();
    private static boolean northOrSouth;
    private static Vec3 blueColor = new Vec3(0, 0.633f, 0.957f);
    //    public static Texture texture;
    private static float portalWidth = Player.PLAYER_HEIGHT / 2;

    public Portal(CollisionResolver cResolver, CollisionDetector cDetector, RadiusCollider rc, Collidable c) {
        super(cResolver, cDetector, minVals, initPortal(rc, c), new Drawable() {
            @Override
            public void draw(GL2 gl) {
                gl.glDisable(GL2.GL_TEXTURE_2D);
                gl.glColor3f(blueColor.getX(), blueColor.getY(), blueColor.getZ());
                gl.glBegin(GL2.GL_POLYGON);
                {
                    for (int i = 0; i < 360; i++) {
                        float rad = i * 3.14159f / 180.0f;
                        gl.glVertex2f((float) Math.cos(rad) * portalWidth / 2,
                                (float) Math.sin(rad) * Player.PLAYER_HEIGHT / 2);
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

    public Portal(RadiusCollider rc, Collidable c) {
        super(minVals, initPortal(rc, c), new Drawable() {
            @Override
            public void draw(GL2 gl) {
                Vec3 pos = rc.getPos();
                gl.glDisable(GL2.GL_TEXTURE_2D);
                gl.glColor3f(blueColor.getX(), blueColor.getY(), blueColor.getZ());
                gl.glBegin(GL2.GL_POLYGON);
                {
                    if (northOrSouth) {
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

//    public static void setParams(Texture texture1, int portalId1, Vec3 minVals1, Vec3 maxVals1) {
//        texture = texture1;
//        portalId = portalId1;
//        minVals = minVals1;
//        maxVals = maxVals1;
//    }

    @Override
    public boolean detectCollision(Collidable player) {
        return super.detectCollision(player);
    }

    private static Vec3 initPortal(RadiusCollider rc, Collidable c) {
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
            northOrSouth = true;
            minVals.setZ(v1);
            maxVals.setZ(v2);
            minVals.setX(posX - halfPortalWidth);
            maxVals.setX(posX + halfPortalWidth);
        } else if (!(north || south)) {
            float v1 = Math.min(posX, (minX + maxX) / 2),
                    v2 = v1 == posX ? (minX + maxX) / 2 : posX;
            northOrSouth = false;
            minVals.setX(v1);
            maxVals.setX(v2);
            minVals.setZ(posZ - halfPortalWidth);
            maxVals.setZ(posZ + halfPortalWidth);
        }
        return maxVals;
    }
}
