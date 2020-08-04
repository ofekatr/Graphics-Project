package Main;

import java.awt.*;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import Drawables.CollidableDrawable;
import InputHandlers.CameraInputAdapter;
import ObjectLoading.WavefrontObjectLoader_DisplayList;
import com.jogamp.newt.Window;
import com.jogamp.newt.event.KeyAdapter;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.awt.AWTKeyAdapter;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;


public class Game extends KeyAdapter implements GLEventListener {
    public static int width = 1280;
    public static int height = 720;
    private final CameraInputAdapter inputHandler;

    private Texture texture;
    private CollidableDrawable gun;

    static GLU glu = new GLU();
    static GLCanvas canvas = new GLCanvas();
    static Frame frame = new Frame("Jogl 3D Shape/Rotation");
    static Animator animator = new Animator(canvas);

    public Game() {
        this.inputHandler = new CameraInputAdapter();
//        this.initSchedueler();
    }

    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        float[] camPos = this.inputHandler.getCamera().getPos().getArray(),
                camLookAt = this.inputHandler.getLookAt().getArray(),
                camUp = this.inputHandler.getCamera().getUp().getArray();
        glu.gluLookAt(camPos[0], camPos[1], camPos[2], camLookAt[0], camLookAt[1], camLookAt[2],
                camUp[0], camUp[1], camUp[2]);

        gl.glTranslatef(-0.33097804f, 0.8000004f, -2.705684f);

        this.gun.draw(gl);
    }

    public void displayChanged(GLAutoDrawable drawable,
                               boolean modeChanged, boolean deviceChanged) {
    }


    private void setInvisibleCursor() {
        int[] pixels = new int[16 * 16];
        Image image = Toolkit.getDefaultToolkit().createImage(
                new MemoryImageSource(16, 16, pixels, 0, 16));
        Cursor transparentCursor =
                Toolkit.getDefaultToolkit().createCustomCursor
                        (image, new Point(0, 0), "invisibleCursor");
        canvas.setCursor(transparentCursor);
    }

    public void init(GLAutoDrawable gLDrawable) {
        this.setInvisibleCursor();
        this.inputHandler.addKeyHandler((char) KeyEvent.VK_ESCAPE, () -> exit());
        canvas.addKeyListener(this.inputHandler);
        canvas.addMouseMotionListener(this.inputHandler);
        canvas.addMouseListener(this.inputHandler);
        final GL2 gl = gLDrawable.getGL().getGL2();
        gl.glShadeModel(GL2.GL_SMOOTH);              // Enable Smooth Shading
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);    // Black Background
        gl.glClearDepth(1.0f);                      // Depth Buffer Setup
        gl.glEnable(GL2.GL_DEPTH_TEST);              // Enables Depth Testing
        gl.glDepthFunc(GL2.GL_LEQUAL);               // The Type Of Depth Testing To Do
        // Really Nice Perspective Calculations
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);

        this.gun = WavefrontObjectLoader_DisplayList.loadWavefrontObjectAsDisplayList(gl,
                "resources/Portal Gun.obj", "resources/textures/portalgun_col.jpg");

        // Keyboard
        if (gLDrawable instanceof Window) {
            Window window = (Window) gLDrawable;
            window.addKeyListener(this);
        } else if (GLProfile.isAWTAvailable() && gLDrawable instanceof java.awt.Component) {
            java.awt.Component comp = (java.awt.Component) gLDrawable;
            new AWTKeyAdapter(this, gLDrawable).addTo(comp);
        }
    }


    public void reshape(GLAutoDrawable drawable, int x,
                        int y, int width1, int height1) {
        height = height1;
        width = width1;
        GL2 gl = drawable.getGL().getGL2();
        if (height1 <= 0) {
            height1 = 1;
        }
        float h = (float) width1 / (float) height1;
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(50.0f, h, 1.0, 1000.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void keyPressed(KeyEvent e) {
        char keyCh = e.getKeyChar();
        if (keyCh == KeyEvent.VK_ESCAPE)
            exit();
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public static void exit() {
        animator.stop();
        frame.dispose();
        System.exit(0);
    }

    public static void main(String[] args) {
        canvas.addGLEventListener(new Game());
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


    @Override
    public void dispose(GLAutoDrawable arg0) {
        // TODO Auto-generated method stub

    }
}