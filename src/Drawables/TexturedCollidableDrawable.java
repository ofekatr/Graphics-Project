/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Drawables;

import Collisions.CollisionDetector;
import Collisions.CollisionResolver;
import Main.Vec3;

import javax.media.opengl.GL2;

public class TexturedCollidableDrawable extends CollidableDrawable {
    private TexturedDrawable textured;

    public TexturedCollidableDrawable(CollisionResolver cResolver, CollisionDetector cDetector, Vec3 minVals,
                                      Vec3 maxVals, TexturedDrawable textured) {
        super(cResolver, cDetector, minVals, maxVals);
        this.textured = textured;
    }

    public TexturedCollidableDrawable(CollisionResolver cResolver, CollisionDetector cDetector, Vec3 minVals,
                                      Vec3 maxVals, String textureStr) {
        super(cResolver, cDetector, minVals, maxVals);
        this.textured = new TexturedDrawable(textureStr);
    }

    public TexturedCollidableDrawable(Vec3 minVals, Vec3 maxVals, TexturedDrawable textured) {
        super(minVals, maxVals);
        this.textured = textured;
    }

    public TexturedCollidableDrawable(Vec3 minVals, Vec3 maxVals, String textureStr) {
        super(minVals, maxVals);
        this.textured = new TexturedDrawable(textureStr);
    }

    @Override
    public void draw(GL2 gl) {
        this.textured.draw(gl);
        super.draw(gl);
    }
}
