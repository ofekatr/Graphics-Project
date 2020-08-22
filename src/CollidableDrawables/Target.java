/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package CollidableDrawables;

import Collidables.*;
import Drawables.Drawable;
import Main.EntitiesCreator;
import Main.Game;
import Main.RadiusCollider;
import Main.Vec3;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import javax.media.opengl.GL2;
import java.io.File;
import java.io.IOException;

public class Target extends TranslatedCollidableDrawable {
    private static final float side = 2;
    private static final float height = 0.1f;
    private boolean initCollision = true;

    public Target(CollisionResolver cResolver, CollisionDetector cDetector, Vec3 params) {
        super(cResolver, cDetector, new Vec3(-side, 0, -side), new Vec3(side, height, side), params,
               new Drawable() {

                   Texture texture = this.loadTexture("resources/textures/target/target.png");

                    @Override
                    public void draw(GL2 gl) {
                        float minX = -side, minY = -0, minZ = -side,
                                maxX = side, maxY = height, maxZ = side;
                        gl.glColor3f(1, 0.6445f, 0.05859f);
                        texture.enable(gl);
                        texture.bind(gl);
                        gl.glBegin(GL2.GL_QUADS);
                        // Front Face
                        gl.glNormal3f(0, 0, 1);
                        gl.glVertex3f(minX, minY, maxZ);
                        gl.glVertex3f(maxX, minY, maxZ);
                        gl.glVertex3f(maxX, maxY, maxZ);
                        gl.glVertex3f(minX, maxY, maxZ);
                        // Back Face
                        gl.glNormal3f(0, 0, -1);
                        gl.glVertex3f(minX, minY, minZ);
                        gl.glVertex3f(minX, maxY, minZ);
                        gl.glVertex3f(maxX, maxY, minZ);
                        gl.glVertex3f(maxX, minY, minZ);
                        // Top Face
                        gl.glNormal3f(0, 1, 0);
                        gl.glTexCoord2f(0.0f, 1);
                        gl.glVertex3f(minX, maxY, minZ);
                        gl.glTexCoord2f(0.0f, 0.0f);
                        gl.glVertex3f(minX, maxY, maxZ);
                        gl.glTexCoord2f(1, 0.0f);
                        gl.glVertex3f(maxX, maxY, maxZ);
                        gl.glTexCoord2f(1, 1);
                        gl.glVertex3f(maxX, maxY, minZ);
                        // Bottom Face
                        gl.glNormal3f(0, -1, 0);
                        gl.glVertex3f(minX, minY, minZ);
                        gl.glVertex3f(maxX, minY, minZ);
                        gl.glVertex3f(maxX, minY, maxZ);
                        gl.glVertex3f(minX, minY, maxZ);
                        // Right face
                        gl.glNormal3f(1, 0, 0);
                        gl.glVertex3f(maxX, minY, minZ);
                        gl.glVertex3f(maxX, maxY, minZ);
                        gl.glVertex3f(maxX, maxY, maxZ);
                        gl.glVertex3f(maxX, minY, maxZ);
                        // Left Face
                        gl.glNormal3f(-1, 0, 0);
                        gl.glVertex3f(minX, minY, minZ);
                        gl.glVertex3f(minX, minY, maxZ);
                        gl.glVertex3f(minX, maxY, maxZ);
                        gl.glVertex3f(minX, maxY, minZ);
                        gl.glEnd();
                        gl.glColor3f(1, 1, 1);
                    }

                    @Override
                    public void timePassed() {

                    }

                    private Texture loadTexture(String texturePath){
                        Texture texture;
                        try {
                            texture = TextureIO.newTexture(new File(texturePath),
                                    true);
                        } catch (IOException e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }
                        return texture;
                    }
                });
    }

    public Target(Vec3 params) {
        this(AABBCollisionResolver.getInstance(), AABBCollisionDetector.getInstance(), params);
    }

    @Override
    public void resolveCollision(RadiusCollider rc, boolean isProjectile) {
        if (this.initCollision){
            EntitiesCreator.changeYouWinVisibilitStatus();
            this.initCollision = false;
        }
    }
}
