import InputHandlers.CameraInputAdapter;
import Drawables.Crosshair;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.texture.Texture;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;

import javax.media.opengl.glu.GLU;
import java.awt.*;

public class Rhombus implements GLEventListener {
    public static final int width = 400;
    public static final int height = 400;
    private final CameraInputAdapter inputHandler;

//    private float xrot = 0;
//    private float yrot = 0;
//    private float zrot = 0;
//    private float xpos = 0;
//    private float ypos = 0;
//    private float zpos = 0;
//    private final float step = 0.3f;
//    private final float rotStep = 2f;


    static GLU glu = new GLU();
    static GLCanvas canvas = new GLCanvas();
    static Frame frame = new Frame("Jogl 3D Shape/Rotation");
    static Animator animator = new Animator(canvas);
    private Texture crosshairTxtr;

    public Rhombus() {
        this.inputHandler = new CameraInputAdapter();
    }

    @Override
    public void display( GLAutoDrawable drawable ) {
        final GL2 gl = drawable.getGL().getGL2();
        new Crosshair().draw(gl);
    }

    @Override
    public void dispose( GLAutoDrawable arg0 ) {
        //method body
    }

    @Override
    public void init(GLAutoDrawable arg0 ) {
        GL2 gl = arg0.getGL().getGL2();
        gl.glEnable(GL.GL_TEXTURE_2D);
//        try {
//            String filename = "Crosshair.png"; // the FileName to open
//            this.crosshairTxtr = TextureIO.newTexture(new File(filename), true);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
    }

    @Override
    public void reshape( GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4 ) {
        // method body
    }

    public static void main( String[] args ) {

        canvas.addGLEventListener(new Rhombus());
        frame.add(canvas);
        frame.setSize(width, height);
//		frame.setUndecorated(true);
//		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        frame.setVisible(true);

//        Dimension actualSize = frame.getContentPane().getSize();
//        int wDiff = actualSize.width - width, hDiff = actualSize.height - height;
//        System.out.println(wDiff + ", " + hDiff);
//        frame.setSize(width - wDiff, height - hDiff);
        animator.start();
        canvas.requestFocus();
    }

}