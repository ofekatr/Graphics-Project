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
    private Texture texture;

    public TexturedLoadedCollidableDrawable(String texturePath, CollisionResolver cResolver, CollisionDetector cDetector, Vec3 minVals, Vec3 maxVals, int id) {
        super(cResolver, cDetector, minVals, maxVals, id);
        try {
            this.texture = TextureIO.newTexture(new File(texturePath), true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public TexturedLoadedCollidableDrawable(String texturePath, Vec3 minVals, Vec3 maxVals, int id) {
        super(minVals, maxVals, id);
        try {
            this.texture = TextureIO.newTexture(new File(texturePath), true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void draw(GL2 gl) {
        this.texture.enable(gl);
        this.texture.bind(gl);
        gl.glCallList(super.id);
    }

    @Override
    public void timePassed() {
        // Nothing.
    }
}
