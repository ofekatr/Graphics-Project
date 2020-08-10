package Examples;/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import CollidableDrawables.TranslatedCollidableDrawable;
import Collisions.Collidable;
import Main.Vec3;

public class Testings {
    public static void main(String[] args) {
        Vec3 minVals = new Vec3(-5, 0.33f, 40);
        Vec3 maxVals = new Vec3(5, 0.6f, 41);
        Vec3 transVals = new Vec3(-10.3f, 5, 15);

        Collidable c1 = new TranslatedCollidableDrawable(minVals, maxVals, null, transVals);
        System.out.println(c1.getMinVals());
        System.out.println(c1.getMaxVals());

        // It works properly.
    }
}
