/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Main;

import CollidableDrawables.CollidableDrawable;
import CollidableDrawables.TranslatedCollidableDrawable;
import Drawables.TranslatedPinnedDrawable;
import ObjectLoading.ObjectLoader;

import javax.media.opengl.GL2;

public class EntitiesCreator {
    public static void createEntities(Vec3 playerPos, CollidablesAndDrawablesManager entitiesManager, GL2 gl) {
        createBox(entitiesManager);
        createGun(playerPos, gl, entitiesManager);
//        createPortal(gl, entitiesManager);
    }

    private static void createPortal(GL2 gl, CollidablesAndDrawablesManager entitiesManager) {
        entitiesManager.addCollidableDrawable(ObjectLoader.loadCollidableDrawable(gl,
                "resources/Cylinder.obj", "resources/textures/portal/Diffuse.jpg"));
    }

    private static void createGun(Vec3 playerPos, GL2 gl, CollidablesAndDrawablesManager entitiesManager) {
        entitiesManager.addDrawable(new TranslatedPinnedDrawable(ObjectLoader.loadDrawable(gl,
                "resources/Portal Gun.obj", "resources/textures/portalgun_col.jpg"),
                new Vec3(playerPos.getX() + 0.75f,
                        -playerPos.getY() / 4,
                        playerPos.getZ() - 2.7f)));
    }

    private static void createBox(CollidablesAndDrawablesManager entitiesManager) {
        CollidableDrawable box = new TranslatedCollidableDrawable(new Vec3(-1, -1, -1),
                new Vec3(1, 1, 1),
                new BoxShapeObject(),
                new Vec3(0, 0, -4));
        entitiesManager.addCollidableDrawable(box);
        entitiesManager.addProjectileCollidable(box);

    }

}
