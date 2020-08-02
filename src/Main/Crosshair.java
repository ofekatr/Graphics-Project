/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Main;

import Collisions.Collidable;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Crosshair implements Drawable {
    private Texture texture;


    public Crosshair() {
        try {
            String filename = "resources/Crosshair.png"; // the FileName to open
            texture = TextureIO.newTexture(new File(filename), true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void draw(GL2 gl) {
        gl.glDisable(GL.GL_CULL_FACE);
        gl.glDisable(GL2.GL_DEPTH_TEST);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();
//        gl.glOrthof(0f, MainClass.width, MainClass.height, 0, 0, 100);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glLoadIdentity();
//
        this.drawCrosshair(gl);
//
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPopMatrix();
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPopMatrix();
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glEnable(GL.GL_CULL_FACE);
    }

    private void drawCrosshair(GL2 gl) {
        float offset = 0.075f;
        this.texture.enable(gl);
        this.texture.bind(gl);
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-offset , offset , 0);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-offset , -offset , 0);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(offset , -offset , 0);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(offset , offset , 0);
        gl.glEnd();
    }
}
