package rs.ac.uns.ftn.nansi.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public final class Intersector {

    private static Vector3 tmp = new Vector3();
    private static Vector3 tmp1 = new Vector3();
    private static Vector3 tmp2 = new Vector3();
    private static Vector3 tmp3 = new Vector3();

    public static boolean intersectSegmentCircle(Vector2 start, Vector2 end,
                                                 Vector2 center, float squareRadius) {
        tmp.set(end.x - start.x, end.y - start.y, 0);
        tmp1.set(center.x - start.x, center.y - start.y, 0);
        float l = tmp.len();
        float u = tmp1.dot(tmp.nor());
        if (u <= 0) {
            tmp2.set(start.x, start.y, 0);
        } else if (u >= l) {
            tmp2.set(end.x, end.y, 0);
        } else {
            tmp3.set(tmp.scl(u));
            tmp2.set(tmp3.x + start.x, tmp3.y + start.y, 0);
        }

        float x = center.x - tmp2.x;
        float y = center.y - tmp2.y;

        return x * x + y * y <= squareRadius;
    }

    public static boolean intersectSegments(Vector2 p1, Vector2 p2, Vector2 p3,
                                            Vector2 p4, Vector2 intersection) {
        float x1 = p1.x, y1 = p1.y, x2 = p2.x, y2 = p2.y, x3 = p3.x, y3 = p3.y, x4 = p4.x, y4 = p4.y;

        float d = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        if (d == 0)
            return false;

        float yd = y1 - y3;
        float xd = x1 - x3;
        float ua = ((x4 - x3) * yd - (y4 - y3) * xd) / d;
        if (ua < 0 || ua > 1)
            return false;

        float ub = ((x2 - x1) * yd - (y2 - y1) * xd) / d;
        if (ub < 0 || ub > 1)
            return false;

        if (intersection != null)
            intersection.set(x1 + (x2 - x1) * ua, y1 + (y2 - y1) * ua);
        return true;
    }

    public static boolean intersect(Vector2 p1, Vector2 p2, Vector2 p3,
                                    Vector2 p4, Vector2 intersection) {
        float x1 = p1.x, y1 = p1.y, x2 = p2.x, y2 = p2.y, x3 = p3.x, y3 = p3.y, x4 = p4.x, y4 = p4.y;
        if (x1 == x2 && x3 == x4) {
            if (x1 == x3) {
                if ((y2 >= Math.min(y1, y3) && y2 <= Math.max(y1, y3)) || (y4 >= Math.min(y1, y3) && y4 <= Math.max(y1, y3))) {
                    if (intersection != null) {
                        intersection.set(x1, (y1 + y2 + y3 + y4) / 4);
                    }
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (x1 == x2) {
            if ((x3 < x1 && x4 < x1) || (x3 > x1 && x4 > x1)) {
                return false;
            }

            float k = (y4 - y3) / (x4 - x3);
            float n = y4 - k * x4;

            if (intersection != null) {
                intersection.set(x1, k * x1 + n);
            }
            return true;
        } else if (x3 == x4) {
            if ((x1 < x3 && x2 < x3) || (x1 > x3 && x2 > x3)) {
                return false;
            }

            float k = (y2 - y1) / (x2 - x1);
            float n = y2 - k * x2;
            if (intersection != null) {
                intersection.set(x3, k * x3 + n);
            }
            return true;

        } else {
            float k1 = (y2 - y1) / (x2 - x1);
            float n1 = y2 - k1 * x2;
            float k2 = (y4 - y3) / (x4 - x3);
            float n2 = y4 - k2 * x4;

            float[][] A = new float[2][];
            A[0] = new float[2];
            A[1] = new float[2];

            float[] b = new float[2];

            A[0][0] = -k1;
            A[0][1] = 1;
            A[1][0] = -k2;
            A[1][1] = 1;

            b[0] = n1;
            b[1] = n2;

            float x0 = x1;
            float y0 = y1;

            float x[] = new float[2];
            x[0] = x0;
            x[1] = y0;

            float[] asd = gz(A, b, x);
            x0 = asd[0];
            y0 = asd[1];

            if (x0 >= Math.min(x1, x2) && x0 <= Math.max(x1, x2) && x0 >= Math.min(x3, x4) && x0 <= Math.max(x3, x4) &&
                    y0 >= Math.min(y1, y2) && y0 <= Math.max(y1, y2) && y0 >= Math.min(y3, y4) && y0 <= Math.max(y3, y4)) {
                if (intersection != null) {
                    intersection.set(x0, y0);
                }
                System.out.println(x0 + "  " + y0);
                return true;
            } else {
                return false;
            }

        }


    }

    private static float[] gz(float[][] a, float[] b, float[] x0) {
        int n = a.length;
        float x[] = new float[n];
        x[0] = 0;
        x[1] = 0;
        float s;
        for (int it = 0; it < 10; ++it) {
            for (int i = 0; i < n; ++i) {
                s = 0;
                for (int j = 0; j < i; ++j) {
                    s += a[i][j] * x[j];
                }
                for (int j = i; j < n; ++j) {
                    s += a[i][j] * x0[j];
                }
                x[i] = (b[i] - s) / a[i][i];
            }
            float err = 0;
            for (int i = 0; i < n; ++i) {
                err += Math.pow(x[i] - x0[i], 2);
            }
            if (err < 0.0002) {
                return x;
            }
            for (int i = 0; i < n; ++i) {
                x0[i] = x[i];
            }
        }
        return x;
    }

}