package rs.ac.uns.ftn.nansi.world;

import com.badlogic.gdx.math.Vector2;
import rs.ac.uns.ftn.nansi.desktop.settings.SimulationSettings;
import rs.ac.uns.ftn.nansi.util.*;

import java.util.ArrayList;

public class RoadFactory implements Factory<Road> {

    private InterpolationType type;

    /**
     * @param type (1 - linear, 2 - lagrange);
     */
    public RoadFactory(InterpolationType type) {
        this.type = type;
    }


    @Override
    public Road create() {
        double[] x = new double[37];
        double[] y = new double[37];

        for (int i = 0; i <= 36; i++) {
            x[i] = i * 10;
            y[i] = MathUtil.randInt(20, 30);
        }

        Interpolation interpolation;

        if (type == InterpolationType.LINEAR) {
            interpolation = new LinearInterpolation(x, y);
        } else {
            interpolation = new LagrangeInterpolation(x, y);
        }

        ArrayList<Vector2> innerRoadPoints = new ArrayList<Vector2>();
        ArrayList<Vector2> outerRoadPoints = new ArrayList<Vector2>();

        ArrayList<RoadLine> innerRoadLines = new ArrayList<RoadLine>();
        ArrayList<RoadLine> outerRoadLines = new ArrayList<RoadLine>();

        ArrayList<RoadLine> objectives = new ArrayList<RoadLine>();

        for (double i = 0; i < 360; i += 1) {

            Vector2 v = new Vector2(0f, (float) interpolation.value(i));
            Vector2 v2 = new Vector2(v);

            v2.add(0, SimulationSettings.getInstance().getRoadWidth());

            v.rotate((float) i);
            v2.rotate((float) i);

            innerRoadPoints.add(v.add(new Vector2(50, 38)));
            outerRoadPoints.add(v2.add(new Vector2(50, 38)));

            objectives.add(new RoadLine(v, v2));

        }

        innerRoadPoints.add(innerRoadPoints.get(0));
        outerRoadPoints.add(outerRoadPoints.get(0));

        for (int i = 0; i < innerRoadPoints.size() - 1; i++) {
            innerRoadLines.add(new RoadLine(innerRoadPoints.get(i),
                    innerRoadPoints.get(i + 1)));
            outerRoadLines.add(new RoadLine(outerRoadPoints.get(i),
                    outerRoadPoints.get(i + 1)));
        }

        innerRoadLines.addAll(outerRoadLines);

        return new Road(innerRoadLines, objectives);
    }
}
