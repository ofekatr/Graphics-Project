/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Drawables;

import Collisions.CollisionDetector;
import Collisions.CollisionResolver;
import Main.Vec3;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import javax.media.opengl.GL2;
import java.io.File;
import java.io.IOException;

public class TexturedLoadedCollidableDrawable extends LoadedCollidableDrawable {
    private TexturedDrawable textured;

    public TexturedLoadedCollidableDrawable(CollisionResolver cResolver, CollisionDetector cDetector, Vec3 minVals,
                                            Vec3 maxVals, int id, TexturedDrawable textured) {
        super(cResolver, cDetector, minVals, maxVals, id);
        this.textured = textured;
    }

    public TexturedLoadedCollidableDrawable(CollisionResolver cResolver, CollisionDetector cDetector, Vec3 minVals,
                                            Vec3 maxVals, int id, String txtrPath) {
        super(cResolver, cDetector, minVals, maxVals, id);
        this.textured = new TexturedDrawable(txtrPath);
    }

    public TexturedLoadedCollidableDrawable(Vec3 minVals, Vec3 maxVals, int id, TexturedDrawable textured) {
        super(minVals, maxVals, id);
        this.textured = textured;
    }

    public TexturedLoadedCollidableDrawable(Vec3 minVals, Vec3 maxVals, int id, String txtrPath) {
        super(minVals, maxVals, id);
        this.textured = new TexturedDrawable(txtrPath);
    }

    @Override
    public void draw(GL2 gl) {
        this.textured.draw(gl);
        gl.glCallList(super.id);
    }

    @Override
    public void timePassed() {
        // Nothing.
    }
}
