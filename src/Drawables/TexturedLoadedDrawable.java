/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Drawables;

import com.jogamp.opengl.util.texture.Texture;

import javax.media.opengl.GL2;

public class TexturedLoadedDrawable extends TexturedDrawable {

    public TexturedLoadedDrawable(String txtrPath, int id) {
        super(txtrPath, new LoadedDrawable(id));
    }

    public TexturedLoadedDrawable(Texture texture, int id) {
        super(texture, new LoadedDrawable(id));
    }

    @Override
    public void draw(GL2 gl) {
        super.draw(gl);
    }
}
