/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Main;

import CollidableDrawables.CollidableDrawable;
import CollidableDrawables.Portal;
import CollidableDrawables.TranslatedCollidableDrawable;
import Drawables.Drawable;
import Drawables.ImageDrawable;
import Drawables.TexturedDrawable;
import Drawables.TranslatedPinnedDrawable;
import ObjectLoading.ObjectLoader;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;
import com.jogamp.opengl.util.texture.TextureIO;

import javax.media.opengl.GL2;
import java.io.File;
import java.io.IOException;

import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

public class EntitiesCreator {
    public static final String objPathPrefix = "resources/obj/";
    public static final String texturePathPrefix = "resources/textures/";

    private static CollidablesAndDrawablesManager entitiesManager;

    private static Drawable help;

    public static void setEntitiesManager(CollidablesAndDrawablesManager manager){
        entitiesManager = manager;
    }

    public static void createEntities(Vec3 playerPos, GL2 gl) {
//        createBox(entitiesManager);
        createWall(gl);
        createGun(playerPos, gl);
        help = new ImageDrawable(Game.width, Game.height,
                texturePathPrefix + "help/help.png");
    }

    private static void createWall(GL2 gl) {
        Drawable d = new BoxShapeObject(texturePathPrefix + "wall/white-tile-texture.jpg");
//        entitiesManager.addCollidableDrawable(box);
        entitiesManager.createAndAddPortalSurface(
                new Vec3(-1, -1, -1),
                new Vec3(1, 1, 1),
                d
        );
    }

    private static void createGun(Vec3 playerPos, GL2 gl) {
        entitiesManager.addDrawable(new TranslatedPinnedDrawable(ObjectLoader.loadDrawable(gl,
                objPathPrefix + "gun/Portal Gun.obj", texturePathPrefix + "gun/portalgun_col.jpg"),
                new Vec3(playerPos.getX() + 0.75f,
                        -playerPos.getY() / 4,
                        playerPos.getZ() - 2.7f)));
    }

    private static void createBox() {
        CollidableDrawable box = new TranslatedCollidableDrawable(new Vec3(-1, -1, -1),
                new Vec3(1, 1, 1),
                new BoxShapeObject(texturePathPrefix + "box/Picture1.jpg"),
                new Vec3(0, 0, -4));
        entitiesManager.addCollidableDrawable(box);
        entitiesManager.addProjectileCollidable(box);

    }

    public static void changeHelpVisibilitStatus() {
        if (entitiesManager.containsDrawable(help)){
            entitiesManager.removeDrawable(help);
        } else {
            entitiesManager.addDrawable(help);
        }
    }

}
