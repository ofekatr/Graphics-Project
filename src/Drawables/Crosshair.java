/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Drawables;

import CollidableDrawables.Portal;
import Main.Game;
import Main.Player;
import Main.Vec3;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;
import com.jogamp.opengl.util.texture.TextureIO;
import sun.plugin2.message.GetAuthenticationMessage;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.io.File;
import java.io.IOException;

import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

public class Crosshair implements Drawable {

    private static final float longer = 12;
    private static final float shorter = 4;

    public Crosshair() {
    }

    @Override
    public void draw(GL2 gl) {

        float x = Game.width / 2, y = Game.height / 2;
        gl.glDisable(GL2.GL_LIGHTING);
        gl.glMatrixMode(GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glOrtho(0.0f, Game.width, Game.height, 0, 0, 1);

        gl.glMatrixMode(GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glDisable(GL2.GL_TEXTURE_2D);

        drawRect(gl, new Vec3(0, 0.633f, 0.957f), longer, shorter, x - 1.5f * shorter - longer, y - shorter / 2);
        drawRect(gl, new Vec3(0, 0.633f, 0.957f), shorter, longer, x - shorter / 2, y - 1.5f * shorter - longer);
        drawRect(gl, new Vec3(1f, 0.6445f, 0), longer, shorter, x + 1.5f * shorter, y - shorter / 2);
        drawRect(gl, new Vec3(1f, 0.6445f, 0), shorter, longer, x - shorter / 2, y + 1.5f * shorter);

        gl.glColor3f(1, 1, 1);
        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPopMatrix();
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPopMatrix();
        gl.glEnable(GL2.GL_LIGHTING);
    }

    private static void drawRect(GL2 gl, Vec3 color, float width, float height, float x, float y) {
        gl.glColor3f(color.getX(), color.getY(), color.getZ());
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(x, y);
        gl.glVertex2f(x, y + height);
        gl.glVertex2f(x + width, y + height);
        gl.glVertex2f(x + width, y);
        gl.glEnd();
    }

    @Override
    public void timePassed() {
        // Nothing.
    }
}
