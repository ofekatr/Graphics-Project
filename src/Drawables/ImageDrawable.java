/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Drawables;

import Main.Game;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;
import com.jogamp.opengl.util.texture.TextureIO;

import javax.media.opengl.GL2;
import java.io.File;
import java.io.IOException;

import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

public class ImageDrawable implements Drawable {

    private Texture texture;
    private float x;
    private float y;
    private float width;
    private float height;

    public ImageDrawable(float x, float y, float width, float height, Texture texture) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public ImageDrawable(float x, float y, float width, float height, String texPath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = this.loadTex(texPath);
    }

    public ImageDrawable(float width, float height, String texPath) {
        this.width = width > Game.width ? Game.width : width;
        this.height = height > Game.height ? Game.height : height;
        this.x = (Game.width - width) / 2;
        this.y = (Game.height - height) / 2;
        this.texture = this.loadTex(texPath);
    }

    private Texture loadTex(String texPath) {
        Texture texture;
        try {
            texture = TextureIO.newTexture(new File(texPath),
                    true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return texture;
    }

    @Override
    public void draw(GL2 gl) {
        gl.glMatrixMode(GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glOrtho(0.0f, Game.width, Game.height, 0, 0, 1);

        gl.glMatrixMode(GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glLoadIdentity();

        texture.enable(gl);
        texture.bind(gl);
        gl.glBegin(GL2.GL_QUADS);
        TextureCoords texcoords = texture.getImageTexCoords();

        gl.glTexCoord2f(texcoords.left(), texcoords.top());
        gl.glVertex2f(x, y);

        gl.glTexCoord2f(texcoords.left(), texcoords.bottom());
        gl.glVertex2f(x, y + height);

        gl.glTexCoord2f(texcoords.right(), texcoords.bottom());
        gl.glVertex2f(x + width, y + height);

        gl.glTexCoord2f(texcoords.right(), texcoords.top());
        gl.glVertex2f(x + width, y);

        gl.glEnd();
        texture.disable(gl);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPopMatrix();
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPopMatrix();
    }

    @Override
    public void timePassed() {

    }
}
