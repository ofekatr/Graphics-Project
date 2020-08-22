/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Drawables;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import javax.media.opengl.GL2;
import java.io.File;
import java.io.IOException;

public class TexturedDrawable implements Drawable {

    protected Texture texture;
    protected Drawable drawable;

    public TexturedDrawable(Texture texture, Drawable drawable) {
        this.texture = texture;
        this.drawable = drawable;
    }

    public TexturedDrawable(String filename, Drawable drawable) {
        try {
            this.texture = TextureIO.newTexture(new File(filename), true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        this.drawable = drawable;
    }

    @Override
    public void draw(GL2 gl) {
        this.texture.bind(gl);
        this.texture.enable(gl);
        this.drawable.draw(gl);
        this.texture.disable(gl);
    }

    @Override
    public void timePassed() {
        // Nothing.
    }
}
