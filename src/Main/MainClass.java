package Main;

import java.awt.*;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import com.jogamp.newt.Window;
import com.jogamp.newt.event.KeyAdapter;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.awt.AWTKeyAdapter;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;


public class MainClass extends KeyAdapter implements GLEventListener {
    static final int width = 1280;
    static final int height = 720;
    private final CameraInputAdapter inputHandler;

//    private float xrot = 0;
//    private float yrot = 0;
//    private float zrot = 0;
//    private float xpos = 0;
//    private float ypos = 0;
//    private float zpos = 0;
//    private final float step = 0.3f;
//    private final float rotStep = 2f;

    private Texture texture;

    static GLU glu = new GLU();
    static GLCanvas canvas = new GLCanvas();
    static Frame frame = new Frame("Jogl 3D Shape/Rotation");
    static Animator animator = new Animator(canvas);

    public MainClass() {
        this.inputHandler = new CameraInputAdapter();
//        this.initSchedueler();
    }

    public void display(GLAutoDrawable drawable) {
        float material[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float position0[] = {10f, 0f, -30f, 1.0f};        // red light on the right side (light 0)
        float position1[] = {-10f, 0f, -5f, 1.0f};    // blue light on the left side (light 1)

        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
//        gl.glRotatef(this.xrot, 1f, 0f, 0f);
//        gl.glRotatef(this.yrot, 0f, 1f, 0f);
//        gl.glRotatef(this.zrot, 0f, 0f, 1f);
//        gl.glTranslatef(this.xpos, this.ypos, this.zpos);
//        glu.gluLookAt(this.xpos, this.ypos, this.zpos, 0, 0, -1, 0, 1, 0);
        float[] camPos = this.inputHandler.getCamera().getPos().getArray();
        float[] camLookAt = this.inputHandler.getLookAt().getArray();
        float[] camUp = this.inputHandler.getCamera().getUp().getArray();
//        System.out.println(camPos[0] + " " + camPos[1] + " " + camPos[2]);
        glu.gluLookAt(camPos[0], camPos[1], camPos[2], camLookAt[0], camLookAt[1], camLookAt[2],
                camUp[0], camUp[1], camUp[2]);


        // Light
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, position0, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, position1, 0);

        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 0.0f, -5.0f);
//
//        gl.glRotatef(xrot, 1.0f, 0.0f, 0.0f);
//        gl.glRotatef(yrot, 0.0f, 1.0f, 0.0f);
//        gl.glRotatef(zrot, 0.0f, 0.0f, 1.0f);

        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_T, GL.GL_REPEAT);
        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
//        gl.glTexParameteri( GL.GL_TEXTURE_2D,GL.GL_TEXTURE_WRAP_S, GL.GL_CLAMP );
        texture.bind(gl);

        BoxShapeObject b = new BoxShapeObject();
        b.draw(gl);
        gl.glPopMatrix();

//        xrot += 0.03f;
//        yrot += 0.05f;
        //zrot += 0.04f;
    }

    public void displayChanged(GLAutoDrawable drawable,
                               boolean modeChanged, boolean deviceChanged) {
    }

    private void initSchedueler() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                inputHandler.fixCoordinates();
            }
        }, 3000, 3000);
    }

    public void setInvisibleCursor() {
        int[] pixels = new int[16 * 16];
        Image image = Toolkit.getDefaultToolkit().createImage(
                new MemoryImageSource(16, 16, pixels, 0, 16));
        Cursor transparentCursor =
                Toolkit.getDefaultToolkit().createCustomCursor
                        (image, new Point(0, 0), "invisibleCursor");
        canvas.setCursor(transparentCursor);
    }

    public void init(GLAutoDrawable drawable) {
        this.setInvisibleCursor();
        this.inputHandler.addKeyHandler((char) KeyEvent.VK_ESCAPE, () -> exit());
        canvas.addKeyListener(this.inputHandler);
        canvas.addMouseMotionListener(this.inputHandler);
        canvas.addMouseListener(this.inputHandler);
        final GL2 gl = drawable.getGL().getGL2();
        gl.glShadeModel(GL2.GL_SMOOTH);              // Enable Smooth Shading
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);    // Black Background
        gl.glClearDepth(1.0f);                      // Depth Buffer Setup
        gl.glEnable(GL2.GL_DEPTH_TEST);              // Enables Depth Testing
        gl.glDepthFunc(GL2.GL_LEQUAL);               // The Type Of Depth Testing To Do
        // Really Nice Perspective Calculations
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

        // Texture
        gl.glEnable(GL.GL_TEXTURE_2D);
        try {
            String filename = "resources/Picture1.jpg"; // the FileName to open
            texture = TextureIO.newTexture(new File(filename), true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);

        // Light
        float ambient[] = {0.1f, 0.1f, 0.1f, 1.0f};
        float diffuse0[] = {1f, 0f, 0f, 1.0f};
        float diffuse1[] = {0f, 0f, 1f, 1.0f};


        gl.glShadeModel(GL2.GL_SMOOTH);

        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambient, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuse0, 0);
        gl.glEnable(GL2.GL_LIGHT0);

        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, ambient, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, diffuse1, 0);
        gl.glEnable(GL2.GL_LIGHT1);

        gl.glEnable(GL2.GL_LIGHTING);

        // Keyboard
        if (drawable instanceof Window) {
            Window window = (Window) drawable;
            window.addKeyListener(this);
        } else if (GLProfile.isAWTAvailable() && drawable instanceof java.awt.Component) {
            java.awt.Component comp = (java.awt.Component) drawable;
            new AWTKeyAdapter(this, drawable).addTo(comp);
        }
    }


    public void reshape(GLAutoDrawable drawable, int x,
                        int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        if (height <= 0) {
            height = 1;
        }
        float h = (float) width / (float) height;
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(50.0f, h, 1.0, 1000.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void keyPressed(KeyEvent e) {
        char keyCh = Character.toLowerCase(e.getKeyChar());
        switch (keyCh) {
//            Movement
//            case 'w':
//                this.inputHandler.translatef(0f, 0f, 1);
//                break;
//            case 's':
//                this.inputHandler.translatef(0f, 0f, -1f);
//                break;
//            case 'd':
//                this.inputHandler.translatef(1f, 0f, 0f);
//                break;
//            case 'a':
//                this.inputHandler.translatef(-1f, 0f, 0f);
//                break;
            case 'e':
                this.inputHandler.translatef(0f, 1f, 0f);
                break;
            case 'q':
                this.inputHandler.translatef(0f, -1f, 0f);
                break;
//                Angle
            case 'i':
                this.inputHandler.rotatef(1, Axes.X);
                break;
            case 'k':
                this.inputHandler.rotatef(-1, Axes.X);
                break;
            case 'l':
                this.inputHandler.rotatef(1, Axes.Y);
                break;
            case 'j':
                this.inputHandler.rotatef(-1, Axes.Y);
                break;
            case 'o':
                this.inputHandler.rotatef(1, Axes.Z);
                break;
            case 'u':
                this.inputHandler.rotatef(-1, Axes.Z);
                break;
            case KeyEvent.VK_ESCAPE:
                exit();
        }
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
        canvas.addGLEventListener(new MainClass());
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