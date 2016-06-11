package rs.ac.uns.ftn.nansi.world;

import java.io.Serializable;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Road implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1760275049580736530L;
	private ArrayList<RoadLine> roadLines;
	private ArrayList<RoadLine> objectives;

	public Road(ArrayList<RoadLine> roadLines, ArrayList<RoadLine> objectives) {
		super();
		this.roadLines = roadLines;
		this.objectives = objectives;

	}

	public Road() {
		super();
		this.roadLines = new ArrayList<RoadLine>();
		this.objectives = new ArrayList<RoadLine>();
	}

	public void draw(ShapeRenderer renderer) {
		for (RoadLine rl : roadLines) {
			rl.draw(renderer);
		}

		// for(RoadLine rl : objectives){
		// rl.draw(renderer);
		// }
	}

	public ArrayList<RoadLine> getRoadLines() {
		return roadLines;
	}

	public void setRoadLines(ArrayList<RoadLine> roadLines) {
		this.roadLines = roadLines;
	}

	public ArrayList<RoadLine> getObjectives() {
		return objectives;
	}

	public void setObjectives(ArrayList<RoadLine> objectives) {
		this.objectives = objectives;
	}

}
