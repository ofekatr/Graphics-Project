/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package CollidableDrawables;

import Collidables.CollisionDetector;
import Collidables.CollisionResolver;
import Drawables.Drawable;
import Drawables.TexturedLoadedDrawable;
import Main.Vec3;
import com.jogamp.opengl.util.texture.Texture;

import javax.media.opengl.GL2;

public class Portal extends TranslatedCollidableDrawable {
    public static int portalId;
    public static Vec3 minVals;
    public static Vec3 maxVals;
    public static Texture texture;

    public Portal(CollisionResolver cResolver, CollisionDetector cDetector, Vec3 transParams) {
        super(cResolver, cDetector, minVals, maxVals, transParams, new TexturedLoadedDrawable(texture, portalId));
    }

    public Portal(Vec3 transParams) {
        super(minVals, maxVals, new Drawable() {
            @Override
            public void draw(GL2 gl) {
                gl.glDisable(GL2.GL_TEXTURE_2D);
                gl.glColor4f(0, 0.633f, 0.957f, 1f);
                gl.glBegin(GL2.GL_POLYGON);
                {
                    for (int i = 0; i < 360; i++) {
                        float rad = i * 3.14159f / 180.0f;
                        gl.glVertex2f((float) Math.cos(rad) * 2,
                                (float) Math.sin(rad) * 1);
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
        }, transParams);
    }

    public static void setParams(Texture texture1, int portalId1, Vec3 minVals1, Vec3 maxVals1) {
        texture = texture1;
        portalId = portalId1;
        minVals = minVals1;
        maxVals = maxVals1;

    }

    @Override
    public void draw(GL2 gl) {
        gl.glDisable(GL2.GL_TEXTURE_2D);
        gl.glColor4f(0, 0.633f, 0.957f, 1f);
        gl.glBegin(GL2.GL_POLYGON);
        {
            for (int i = 0; i < 360; i++) {
                float rad = i * 3.14159f / 180.0f;
                gl.glVertex2f((float) Math.cos(rad) * 2,
                        (float) Math.sin(rad) * 1);
            }
        }
        gl.glEnd();
        gl.glColor3f(1, 1, 1);
        gl.glEnable(GL2.GL_TEXTURE_2D);
    }
}
