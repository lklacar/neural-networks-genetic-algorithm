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

}