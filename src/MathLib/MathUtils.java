/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package MathLib;

import java.util.Arrays;

import static java.lang.Float.NaN;

public final class MathUtils {
    private MathUtils() {
    }

    // Sum 2 vectors.
    public static float[] sumVectors(float[] a, float[] b) throws ArithmeticException {
        if (a.length != b.length)
            throw new ArithmeticException("Invalid vectors of different dimensions");
        float[] res = new float[a.length];
        for (int i = 0; i < a.length; i++) {
            res[i] = a[i] + b[i];
        }
        return res;
    }

    // Sum 2 matrices.
    public static float[][] sumMatrices(float[][] a, float[][] b) throws ArithmeticException {
        if (a.length != b.length || a[0].length != b[0].length)
            throw new ArithmeticException("Cannot sum matrices of different dimensions");
        float[][] res = new float[a.length][a[0].length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++)
                res[i][j] = a[i][j] + b[i][j];
        }
        return res;
    }

    // Dot product.
    public static float dotProduct(float[] a, float[] b) throws ArithmeticException {
        if (a.length != b.length)
            throw new ArithmeticException("Invalid vectors of different dimensions");
        float res = 0;
        for (int i = 0; i < a.length; i++)
            res += (a[i] * b[i]);
        return res;
    }

    // Cross product.
    public static float[] crossProduct3d(float[] a, float[] b) throws ArithmeticException {
        if (a.length != 3 || b.length != 3)
            throw new ArithmeticException("This method supports 3d vectors only.");
        float[] res = new float[a.length];
        res[0] = a[1] * b[2] - a[2] * b[1];
        res[1] = a[2] * b[0] - a[0] * b[2];
        res[2] = a[0] * b[1] - a[1] * b[0];
        return res;
    }

    // Vector length.
    public static float length(float[] a) {
        float res = 0;
        for (int i = 0; i < a.length; i++)
            res += Math.pow(a[i], 2);
        return (float) Math.sqrt(res);
    }

    // Normalize vector.
    public static float[] normalize(float[] a) {
        float len = length(a);
        float[] res = new float[a.length];
        for (int i = 0; i < a.length; i++)
            res[i] = a[i] / len;
        return res;
    }

    // Calculate angle between 2 vectors.
    public static float angleBetween(float[] a, float[] b) {
        return (float) Math.cos(dotProduct(a, b) / (length(a) * length(b)));
    }

    // Multiply vector by a scalar.
    public static float[] vectorScalarProduct(float[] vec, float scalar) {
        float[] temp = vec.clone();
        for (int i = 0; i < vec.length; i++)
            temp[i] *= scalar;
        return temp;
    }

    // Matrices multiplication
    public static float[][] product(float[][] a, float[][] b) throws ArithmeticException {
        if (a[0].length != b.length)
            throw new ArithmeticException("Invalid matrices dimensions.");
        float[][] res = new float[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                res[i][j] = 0;
                for (int k = 0; k < b.length; k++)
                    res[i][j] += a[i][k] * b[k][j];
            }
        }
        return res;
    }

    public static float[] product(float[][] a, float[] b) throws ArithmeticException {
        if (a[0].length != b.length) {
            throw new ArithmeticException("Invalid matrices dimensions.");
        }
        float[] res = new float[a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                res[i] += a[i][j] * b[j];
            }
        }
        return res;
    }

    public static float[][] generateZRotationMatrix(float alpha) {
        float[][] res = new float[4][4];
        res[0][0] = res[1][1] = (float) Math.cos(alpha);
        res[0][1] = res[1][0] = (float) Math.sin(alpha);
        res[0][1] *= -1;
        res[2][2] = res[3][3] = 1;
        return res;
    }

    public static float[][] generateYRotationMatrix(float alpha) {
        float[][] res = new float[4][4];
        res[0][0] = res[2][2] = (float) Math.cos(alpha);
        res[2][0] = res[0][2] = (float) Math.sin(alpha);
        res[2][0] *= -1;
        res[1][1] = res[3][3] = 1;
        return res;
    }

    public static float[][] generateXRotationMatrix(float alpha) {
        float[][] res = new float[4][4];
        res[2][2] = res[1][1] = (float) Math.cos(alpha);
        res[2][1] = res[1][2] = (float) Math.sin(alpha);
        res[2][1] *= -1;
        res[0][0] = res[3][3] = 1;
        return res;
    }

    public static float[][] generateCMatrix(float[] a) {
        float[][] c = new float[3][3];
        c[2][1] = a[0];
        c[0][2] = a[1];
        c[1][0] = a[2];
        c[1][2] = -a[0];
        c[2][0] = -a[1];
        c[0][1] = -a[2];
        return c;
    }

    public static float[][] generateIdentity() {
        float[][] id = new float[3][3];
        for (int i = 0; i < id.length; i++)
            id[i][i] = 1;
        return id;
    }

//    public static void printMatrix(float[][] matrix) {
//        for (int i = 0; i < matrix.length; i++) {
//            System.out.println(Arrays.toString(matrix[i]));
//        }
//        System.out.println("");
//    }

    public static float[][] generateVectorRotationMatrix(float[] a, float alpha) {
        float[][] c = MathUtils.generateCMatrix(a), id = MathUtils.generateIdentity(),
                c_sq = c.clone();
        c_sq = MathUtils.product(c_sq, c_sq);
        float[][] r = MathUtils.sumMatrices(id,
                MathUtils.matrixScalarProduct(c.clone(), (float) Math.sin(alpha)));
        r = MathUtils.sumMatrices(r,
                MathUtils.matrixScalarProduct(c_sq.clone(), 1 - (float) Math.cos(alpha)));
        return r;
    }

    public static boolean equals(float a, float b) {
        float threshold = 0.0001f;
        return Math.abs(a - b) < threshold;
    }

    public static float[][] matrixScalarProduct(float[][] m, float a) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                m[i][j] *= a;
            }
        }
        return m;
    }

    public static boolean sameVector(float[] a, float[] b) {
        int index = 0;
        while (index < a.length && a[index] == 0) {
            index++;
        }
        if (index == a.length)
            return Arrays.equals(a, b);
        float ratio = a[index] / b[index];
        for (int i = index + 1; i < a.length; i++) {
            float tempRatio = a[i] / b[i];
            if (tempRatio != ratio && !Float.isNaN(tempRatio)) {
                return false;
            }
        }
        return true;
    }
}
