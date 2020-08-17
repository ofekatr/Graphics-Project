package Main;

import java.awt.*;
import java.awt.image.MemoryImageSource;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import CollidableDrawables.CollidableDrawable;
import CollidableDrawables.TranslatedCollidableDrawable;
import Collidables.Collidable;
import Drawables.*;
import InputHandlers.CameraInputHandler;
import ObjectLoading.ObjectLoader;
import com.jogamp.newt.Window;
import com.jogamp.newt.event.KeyAdapter;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.awt.AWTKeyAdapter;
import com.jogamp.opengl.util.Animator;


public class Game extends KeyAdapter implements GLEventListener {
    public static int width = 1280;
    public static int height = 720;
    private final Player player;
    private final CollidablesAndDrawablesManager entitiesManager;

    static GLU glu = new GLU();
    static GLCanvas canvas = new GLCanvas();
    static Frame frame = new Frame("Portal");
    static Animator animator = new Animator(canvas);

    public Game() {
        List<Collidable> lst = new ArrayList<>();
        this.player = new Player(lst);
        this.entitiesManager = new CollidablesAndDrawablesManager(lst);
    }

    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        float[] camPos = this.player.getCamera().getPos().getArray(),
                camLookAt = this.player.getLookAt().getArray(),
                camUp = this.player.getCamera().getUp().getArray();
        glu.gluLookAt(camPos[0], camPos[1], camPos[2], camLookAt[0], camLookAt[1], camLookAt[2],
                camUp[0], camUp[1], camUp[2]);

        this.entitiesManager.draw(gl);

        gl.glPopMatrix();

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
        CameraInputHandler inputHandler = this.player.getCamera().getInputHandler();
        this.setInvisibleCursor();
        inputHandler.addKeyHandler((char) KeyEvent.VK_ESCAPE, () -> exit());
        canvas.addKeyListener(inputHandler);
        canvas.addMouseMotionListener(inputHandler);
        canvas.addMouseListener(inputHandler);
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

        EntitiesCreator.createEntities(this.player.getPos(), this.entitiesManager, gl);

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

    public static Point calcMidPoint() {
        return new Point(canvas.getWidth() / 2, canvas.getHeight() / 2);
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
    }
}