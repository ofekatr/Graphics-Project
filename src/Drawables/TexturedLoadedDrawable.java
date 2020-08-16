/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Drawables;

import com.jogamp.opengl.util.texture.Texture;

import javax.media.opengl.GL2;

public class TexturedLoadedDrawable extends TexturedDrawable {

    private LoadedDrawable loaded;

    public TexturedLoadedDrawable(String txtrPath, int id) {
        super(txtrPath);
        loaded = new LoadedDrawable(id);
    }

    public TexturedLoadedDrawable(Texture texture, int id) {
        super(texture);
        loaded = new LoadedDrawable(id);
    }

    @Override
    public void draw(GL2 gl) {
        super.draw(gl);
        this.loaded.draw(gl);
    }
}
