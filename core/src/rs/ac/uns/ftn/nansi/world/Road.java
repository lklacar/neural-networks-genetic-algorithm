package rs.ac.uns.ftn.nansi.world;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.io.Serializable;
import java.util.ArrayList;

public class Road implements Serializable {

    private static final long serialVersionUID = -1760275049580736530L;
    private ArrayList<RoadLine> roadLines;
    private ArrayList<RoadLine> objectives;

    public Road(ArrayList<RoadLine> roadLines, ArrayList<RoadLine> objectives) {
        super();
        this.roadLines = roadLines;
        this.objectives = objectives;

    }

    public void draw(ShapeRenderer renderer) {
        for (RoadLine rl : roadLines) {
            rl.draw(renderer);
        }
    }

    public ArrayList<RoadLine> getRoadLines() {
        return roadLines;
    }


    public ArrayList<RoadLine> getObjectives() {
        return objectives;
    }


}
