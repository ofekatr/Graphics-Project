/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Main;

import CollidableDrawables.*;
import Drawables.*;
import ObjectLoading.ObjectLoader;

import javax.media.opengl.GL2;

public class EntitiesCreator {
    private static final String objPathPrefix = "resources/obj/";
    private static final String texturePathPrefix = "resources/textures/";
    public static final float floorSize = 50;
    public static final float wallHeight = 20;
    private static final float wallThickness = 0.01f;
    private static final float secondFloorSize = 20;
    private static final Vec3 wallRatios = new Vec3(3, 2, 3);
    private static final Vec3 ceilingRatios = new Vec3(10, 1, 10);
    private static final Vec3 floorRatios = new Vec3(3, 1, 10);


    private static CollidablesAndDrawablesManager entitiesManager;

    private static Drawable help;
    private static Drawable click2Play;
    private static Drawable youWin;

    enum WallTextures {
        WOOD(texturePathPrefix + "cube/cube.png"),
        SPIKES(texturePathPrefix + "spikes/spikes.jpg"),
        TILES(texturePathPrefix + "wall/white-tile-texture.jpg");

        private String texturePath;

        WallTextures(String texturePath) {
            this.texturePath = texturePath;
        }

        private String getTexturePath() {
            return this.texturePath;
        }
    }

    public static void setEntitiesManager(CollidablesAndDrawablesManager manager) {
        entitiesManager = manager;
    }

    private static void createBb8(GL2 gl, Vec3 pos){
        CollidableDrawable r = ObjectLoader.loadCollidableDrawable(gl, objPathPrefix + "bb8/bb8.obj",
                texturePathPrefix + "bb8/bb8.jpg");
        entitiesManager.addCollidableDrawable(new ScaledCollidableDrawable(r.getMinVals(),
                r.getMaxVals(), r.getDrawable(), 0.02f));
    }

    private static void createImageDrawables(GL2 gl, boolean isSecondLevel) {
        help = new ImageDrawable(Game.width, Game.height,
                texturePathPrefix + "help/help.png");
        entitiesManager.addDrawable(new Crosshair());
        if (!isSecondLevel){
            click2Play = new ImageDrawable(Game.width / 2, 83,
                    texturePathPrefix + "click/click.png");
            changeClick2PlayVisibilitStatus();
        }
        youWin = new ImageDrawable(Game.width, Game.height,
                texturePathPrefix + "congrats/congrats.png");
    }

    public static void createFirstLevel(GL2 gl, Vec3 playerPos) {
        entitiesManager.emptyEntities();
        createFirstLevelWalls(gl);
        createImageDrawables(gl, false);
        createBb8(gl, new Vec3());
        createGun(playerPos, gl);
        createTarget(new Vec3(floorSize * 0.9f, 0, 0));
    }

    private static void createFirstLevelWalls(GL2 gl) {
        // North
        createWallFromPercentages(gl, false, true, new Vec3(0, 0, -wallThickness),
                new Vec3(0.35f, 1, 0), wallRatios, WallTextures.TILES);
        createWallFromPercentages(gl, false, true, new Vec3(0.4f, 0, -wallThickness),
                new Vec3(0.6f, 1, 0), wallRatios, WallTextures.TILES);
        createWallFromPercentages(gl, false, true, new Vec3(0.65f, 0, -wallThickness),
                new Vec3(1, 1, 0), wallRatios, WallTextures.TILES);
        // South
        createWall(gl, true, true, new Vec3(-floorSize, 0, floorSize - wallThickness),
                new Vec3(floorSize, wallHeight, floorSize),
                new Vec3(10, 2, 10), WallTextures.WOOD);
        // West
        createWall(gl, false, true, new Vec3(-floorSize - wallThickness, 0, -floorSize),
                new Vec3(-floorSize, wallHeight, floorSize), new Vec3(10, 2, 10), WallTextures.TILES);
        // East
        createWall(gl, false, true, new Vec3(floorSize - wallThickness, 0, -floorSize),
                new Vec3(floorSize, wallHeight, floorSize),
                new Vec3(10, 2, 10), WallTextures.TILES);
        // Floor1
        createWallFromPercentages(gl, true, false, new Vec3(0, -wallThickness, 0),
                new Vec3(0.4f, 0, 1), floorRatios, WallTextures.TILES);

        // Floor2
        createWallFromPercentages(gl, true, false, new Vec3(0.6f, -wallThickness, 0),
                new Vec3(1, 0, 1), floorRatios, WallTextures.SPIKES);

        // Floor3
        createWallFromPercentages(gl, true, false, new Vec3(0.4f, -wallThickness, 0),
                new Vec3(0.6f, 0, 1), floorRatios, WallTextures.SPIKES);

        // Barrier1
        createWallFromPercentages(gl, false, true, new Vec3(0.35f, 0, 0),
                new Vec3(0.4f, 0.4f, 1), ceilingRatios, WallTextures.TILES);

        // Barrier2
        createWallFromPercentages(gl, false, true, new Vec3(0.6f, 0, 0),
                new Vec3(0.65f, 0.4f, 1), ceilingRatios, WallTextures.TILES);
        // Ceiling
        createWall(gl, true, false,
                new Vec3(-floorSize, wallHeight, -floorSize),
                new Vec3(floorSize, wallHeight + wallThickness, floorSize),
                new Vec3(10, 1, 10), WallTextures.TILES);
    }

    private static void createSecondLevelWalls(GL2 gl){
        for (int i = 0; i < 3; i++) {
            // Floor
            createWallFromPercentages(gl, true, false, new Vec3(0.5f * i, -wallThickness, -1 + i * 0.5f),
                    new Vec3(0.5f * i + .4f, 0, 1 + i * 0.5f), floorRatios, WallTextures.SPIKES);
            // Ceiling
            createWallFromPercentages(gl, true, false, new Vec3(0.5f * i, 1, -1 + i * 0.5f),
                    new Vec3(0.5f * i + .4f, 1 + wallThickness, 1 + i * 0.5f), floorRatios, WallTextures.TILES);
            // South
            createWallFromPercentages(gl, false, true, new Vec3(0.5f * i, 0, 1 + 0.5f * i - wallThickness),
                    new Vec3(0.5f * i + .4f, 1, 1 + i *0.5f), floorRatios, WallTextures.TILES);
            createWallFromPercentages(gl, true, true, new Vec3(0.5f * i, 0, 1 + 0.5f * i),
                    new Vec3(0.5f * i + .4f, 1, 1 + i *0.5f + wallThickness), floorRatios, WallTextures.WOOD);
            // Wood
            createWallFromPercentages(gl, true, true, new Vec3(0.5f * i, 0, -1 + i * 0.5f),
                    new Vec3(0.5f * i + .4f, 0.5f, 0.6f + i * 0.5f), floorRatios, WallTextures.WOOD);

            // Barriers
            createWallFromPercentages(gl, true, true, new Vec3(0.5f * i + .39f, 0, -1 + i * 0.5f),
                    new Vec3(0.5f * i + .4f, 0.1f, 1 + i * 0.5f), floorRatios, WallTextures.TILES);
            createWallFromPercentages(gl, true, true, new Vec3(0.5f * i + -0.01f, 0, -1 + i * 0.5f),
                    new Vec3(0.5f * i + 0f, 0.1f, 1 + i * 0.5f), floorRatios, WallTextures.TILES);
        }
    }

    public static void createSecondLevel(GL2 gl, Vec3 playerPos) {
        entitiesManager.emptyEntities();
        createSecondLevelWalls(gl);
        createImageDrawables(gl, true);
        createGun(playerPos, gl);
        createTarget(new Vec3(1.5f *floorSize, 0, 2.7f*floorSize));
    }

    private static void createWallFromPercentages(GL2 gl, boolean isNotPortalSurface, boolean isCollidable, Vec3 minVals,
                                                  Vec3 maxVals, Vec3 ratios, WallTextures wallTextures) {
        Vec3 newMinVals = Vec3.multByScalar(Vec3.sum(Vec3.multByScalar(minVals, 2), new Vec3(-1, 0, -1)),
                floorSize);
        Vec3 newMaxVals = Vec3.multByScalar(Vec3.sum(Vec3.multByScalar(maxVals, 2), new Vec3(-1, 0, -1)),
                floorSize);
        newMinVals.setY(minVals.getY() * wallHeight);
        newMaxVals.setY(maxVals.getY() * wallHeight);
        createWall(gl, isNotPortalSurface, isCollidable, newMinVals, newMaxVals, ratios, wallTextures);

    }

    private static void createWall(GL2 gl, boolean isNotPortalSurface, boolean isCollidable, Vec3 minVals, Vec3 maxVals, Vec3 ratios,
                                   WallTextures wallTexture) {
        if (isNotPortalSurface) {
            if (isCollidable) {
                entitiesManager.addCollidableDrawable(new CollidableDrawable(
                        minVals, maxVals, new BoxShapeObject(wallTexture.getTexturePath(),
                        minVals, maxVals, ratios)));
            } else {
                entitiesManager.addDrawable(new BoxShapeObject(wallTexture.getTexturePath(),
                        minVals, maxVals, ratios));
            }

        } else {
            entitiesManager.createAndAddPortalSurface(
                    minVals,
                    maxVals,
                    new BoxShapeObject(wallTexture.getTexturePath(),
                            minVals, maxVals, ratios)
            );
        }

    }

    private static void createGun(Vec3 playerPos, GL2 gl) {
        entitiesManager.addDrawable(new TranslatedPinnedDrawable(ObjectLoader.loadDrawable(gl,
                objPathPrefix + "gun/Portal Gun.obj", texturePathPrefix + "gun/portalgun_col.jpg"),
                new Vec3(0.8f,
                        -playerPos.getY() / 4,
                        -2.5f)));
    }

    private static void createCube(GL2 gl) {
        CollidableDrawable cube = ObjectLoader.loadCollidableDrawable(gl,
                objPathPrefix + "cube/2-foot_Weighted_Companion_Cube_OBJ.obj",
                texturePathPrefix + "cube/cube.png");
        cube.setDrawable(new TranslationDrawableDecorator(cube.getDrawable(), new Vec3(0, 2, 0)));
        entitiesManager.addCollidableDrawable(cube);
    }

    private static void createTarget(Vec3 pos) {
        entitiesManager.addCollidableDrawable(new Target(pos));
    }

    public static void changeHelpVisibilitStatus() {
        if (entitiesManager.containsDrawable(help)) {
            entitiesManager.removeDrawable(help);
        } else {
            entitiesManager.addDrawable(help);
        }
    }

    public static void changeClick2PlayVisibilitStatus() {
        if (entitiesManager.containsDrawable(click2Play)) {
            entitiesManager.removeDrawable(click2Play);
        } else {
            entitiesManager.addDrawable(click2Play);
        }
    }



    public static void changeYouWinVisibilitStatus() {
        if (entitiesManager.containsDrawable(youWin)) {
            entitiesManager.removeDrawable(youWin);
        } else {
            entitiesManager.addDrawable(youWin);
        }
    }

}
