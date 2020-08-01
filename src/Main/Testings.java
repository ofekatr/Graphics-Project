package Main;/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/

import MathLib.MathUtils;

import java.util.Arrays;

public class Testings {
    public static void main(String[] args) {
        float[] vec = {0, 0, 1};
        vec = MathUtils.normalize(vec);
        float[][] r = MathUtils.generateVectorRotationMatrix(vec, (float) Math.toRadians(90));
        float[] point = {5, 0, 5};
        float[] res = MathUtils.product(r, point);
    }
}
