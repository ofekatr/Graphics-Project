package Examples;

import java.awt.Frame;
import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

public class Circle implements GLEventListener {

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glPushMatrix();
        gl.glColor3f(0, 0.633f, 0.957f);
        // clear the window
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glBegin(GL2.GL_POLYGON);
        {
            for (int i = 0; i < 360; i++) {
                float rad = i * 3.14159f / 180.0f;
                gl.glVertex2f((float) Math.cos(rad) * 0.5f,
                        (float) Math.sin(rad) * 0.25f);
            }
        }
        gl.glEnd();
        gl.glPopMatrix();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        //method body
    }

    @Override
    public void init(GLAutoDrawable drawable) {
    }

    @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
                        int arg4) {
        // method body
    }

    public static void main(String... args) {
        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
//        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(new Circle());
        glcanvas.setSize(400, 400);
        //creating frame
        final JFrame frame = new JFrame("Circle");
        //adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//end of main
}//end of classimport com.jogamp.opengl.GL2;