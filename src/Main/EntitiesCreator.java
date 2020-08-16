/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Main;

import CollidableDrawables.CollidableDrawable;
import CollidableDrawables.Portal;
import CollidableDrawables.TranslatedCollidableDrawable;
import Drawables.Drawable;
import Drawables.TranslatedPinnedDrawable;
import ObjectLoading.ObjectLoader;

import javax.media.opengl.GL2;

public class EntitiesCreator {
    public static final String objPathPrefix = "resources/obj/";
    public static final String texturePathPrefix = "resources/textures/";

    public static void createEntities(Vec3 playerPos, CollidablesAndDrawablesManager entitiesManager, GL2 gl) {
//        createBox(entitiesManager);
        createWall(gl, entitiesManager);
        createGun(playerPos, gl, entitiesManager);
        createPortal(gl, entitiesManager);
    }

    private static void createPortal(GL2 gl, CollidablesAndDrawablesManager entitiesManager) {
        String texturePath = texturePathPrefix + "portal/Diffuse.jpg";
        String objPath = objPathPrefix + "portal/Cylinder.obj";
        ObjectLoader.loadPortal(gl, objPath, texturePath);
    }

    private static void createWall(GL2 gl, CollidablesAndDrawablesManager entitiesManager) {
        Drawable d = new BoxShapeObject(texturePathPrefix + "wall/white-tile-texture.jpg");
//        entitiesManager.addCollidableDrawable(box);
//        entitiesManager.addProjectileCollidable(box);
        entitiesManager.createAndAddPortalSurface(
                new Vec3(0, 0, -4),
                new Vec3(1, 1, 1),
                d
        );
    }

    private static void createGun(Vec3 playerPos, GL2 gl, CollidablesAndDrawablesManager entitiesManager) {
        entitiesManager.addDrawable(new TranslatedPinnedDrawable(ObjectLoader.loadDrawable(gl,
                objPathPrefix + "gun/Portal Gun.obj", texturePathPrefix + "gun/portalgun_col.jpg"),
                new Vec3(playerPos.getX() + 0.75f,
                        -playerPos.getY() / 4,
                        playerPos.getZ() - 2.7f)));
    }

    private static void createBox(CollidablesAndDrawablesManager entitiesManager) {
        CollidableDrawable box = new TranslatedCollidableDrawable(new Vec3(-1, -1, -1),
                new Vec3(1, 1, 1),
                new BoxShapeObject(texturePathPrefix + "box/Picture1.jpg"),
                new Vec3(0, 0, -4));
        entitiesManager.addCollidableDrawable(box);
        entitiesManager.addProjectileCollidable(box);

    }

}
