package Examples;/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import CollidableDrawables.TranslatedCollidableDrawable;
import Collidables.Collidable;
import Main.Vec3;

public class Testings {
    public static void main(String[] args) {
        foo(new String(), new String());
    }

    private static void foo(Object... objects) {
        System.out.println("what");
        System.out.println(objects.getClass().getName());
        System.out.println(objects.getClass().getSimpleName());

    }
}
