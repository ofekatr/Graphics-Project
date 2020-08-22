package Main;/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import CollidableDrawables.CollidableDrawable;
import Collidables.CollisionDetector;
import Collidables.CollisionResolver;
import Drawables.Drawable;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import javax.media.opengl.GL2;
import java.io.File;
import java.io.IOException;
import java.time.zone.ZoneRules;

public class BoxShapeObject implements Drawable {

    private Texture texture;
    private Vec3 minVals;
    private Vec3 maxVals;
    private Vec3 ratios;

    public BoxShapeObject(String texturePath, Vec3 minVals, Vec3 maxVals, Vec3 ratios) {
        this.maxVals = maxVals;
        this.minVals = minVals;

        try {
            this.texture = TextureIO.newTexture(new File(texturePath), true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        this.ratios = ratios;


    }

    @Override

    public void draw(GL2 gl) {
        float minX = this.minVals.getX(), minY = this.minVals.getY(), minZ = this.minVals.getZ(),
                maxX = this.maxVals.getX(), maxY = this.maxVals.getY(), maxZ = this.maxVals.getZ(),
                xRatio = this.ratios.getX(), yRatio = this.ratios.getY(), zRatio = this.ratios.getZ();
        float material[] = {0.8f, 0.8f, 0.8f, 1.0f};
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, material, 0);
        this.texture.enable(gl);
        this.texture.bind(gl);
        gl.glTexParameteri ( GL2.GL_TEXTURE_2D,GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT );
        gl.glTexParameteri( GL2.GL_TEXTURE_2D,GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT );

        gl.glBegin(GL2.GL_QUADS);
        // Front Face
        gl.glNormal3f(0, 0, 1);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(minX, minY, maxZ);
        gl.glTexCoord2f(xRatio, 0.0f);
        gl.glVertex3f(maxX, minY, maxZ);
        gl.glTexCoord2f(xRatio, yRatio);
        gl.glVertex3f(maxX, maxY, maxZ);
        gl.glTexCoord2f(0.0f, yRatio);
        gl.glVertex3f(minX, maxY, maxZ);
        // Back Face
        gl.glNormal3f(0, 0, -1);
        gl.glTexCoord2f(xRatio, 0.0f);
        gl.glVertex3f(minX, minY, minZ);
        gl.glTexCoord2f(xRatio, yRatio);
        gl.glVertex3f(minX, maxY, minZ);
        gl.glTexCoord2f(0.0f, yRatio);
        gl.glVertex3f(maxX, maxY, minZ);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(maxX, minY, minZ);
        // Top Face
        gl.glNormal3f(0, 1, 0);
        gl.glTexCoord2f(0.0f, zRatio);
        gl.glVertex3f(minX, maxY, minZ);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(minX, maxY, maxZ);
        gl.glTexCoord2f(xRatio, 0.0f);
        gl.glVertex3f(maxX, maxY, maxZ);
        gl.glTexCoord2f(xRatio, zRatio);
        gl.glVertex3f(maxX, maxY, minZ);
        // Bottom Face
        gl.glNormal3f(0, -1, 0);
        gl.glTexCoord2f(xRatio, zRatio);
        gl.glVertex3f(minX, minY, minZ);
        gl.glTexCoord2f(0.0f, zRatio);
        gl.glVertex3f(maxX, minY, minZ);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(maxX, minY, maxZ);
        gl.glTexCoord2f(xRatio, 0.0f);
        gl.glVertex3f(minX, minY, maxZ);
        // Right face
        gl.glNormal3f(1, 0, 0);
        gl.glTexCoord2f(zRatio, 0.0f);
        gl.glVertex3f(maxX, minY, minZ);
        gl.glTexCoord2f(zRatio, yRatio);
        gl.glVertex3f(maxX, maxY, minZ);
        gl.glTexCoord2f(0.0f, yRatio);
        gl.glVertex3f(maxX, maxY, maxZ);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(maxX, minY, maxZ);
        // Left Face
        gl.glNormal3f(-1, 0, 0);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(minX, minY, minZ);
        gl.glTexCoord2f(zRatio, 0.0f);
        gl.glVertex3f(minX, minY, maxZ);
        gl.glTexCoord2f(zRatio, yRatio);
        gl.glVertex3f(minX, maxY, maxZ);
        gl.glTexCoord2f(0.0f, yRatio);
        gl.glVertex3f(minX, maxY, minZ);
        gl.glEnd();
    }

    @Override
    public void timePassed() {
        // Nothing.
    }
}
