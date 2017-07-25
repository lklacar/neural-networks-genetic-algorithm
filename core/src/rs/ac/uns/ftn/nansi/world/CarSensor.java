package rs.ac.uns.ftn.nansi.world;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import rs.ac.uns.ftn.nansi.util.Intersector;

import java.util.ArrayList;
import java.util.Collections;

public class CarSensor {

    private Car car;
    private Road road;
    private double angleOffset;

    private Vector2 start;
    private Vector2 end;

    public CarSensor(Car car, Road road, double angleOffset) {
        super();
        this.car = car;
        this.road = road;
        this.angleOffset = angleOffset;

        this.start = new Vector2(car.getBody().getPosition());
        this.end = new Vector2(car.getBody().getPosition().x + 1000, car
                .getBody().getPosition().y);

    }

    public void update() {
        this.start = new Vector2(car.getBody().getPosition());

        this.start.x = car.getBody().getPosition().x;
        this.start.y = car.getBody().getPosition().y;

        this.end.setAngle((float) (Math.toDegrees(car.getBody().getAngle()) + angleOffset));

    }

    private Vector2 getEnd() {
        Vector2 result = new Vector2();

        ArrayList<Vector2> results = new ArrayList<Vector2>();
        ArrayList<Float> lens = new ArrayList<Float>();

        for (RoadLine rl : road.getRoadLines()) {

            if (Intersector.intersectSegments(rl.getP1(), rl.getP2(), start,
                    end, result)) {
                results.add(new Vector2(result));

                lens.add(start.dst(result));

            }

        }

        int index = 0;
        try {
            index = lens.indexOf(Collections.min(lens));
            return results.get(index);
        } catch (Exception ex) {
            return new Vector2(0, 0);
        }

    }

    public double getDistance() {
        return getEnd().dst(car.getBody().getPosition());

    }

    public void draw(ShapeRenderer renderer) {
        update();

        renderer.line(start, getEnd());

    }

}
