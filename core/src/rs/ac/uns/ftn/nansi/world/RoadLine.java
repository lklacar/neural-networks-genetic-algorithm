package rs.ac.uns.ftn.nansi.world;

import java.io.Serializable;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class RoadLine implements Serializable {
	private Vector2 p1, p2;
	private static final long serialVersionUID = -1760275049580236530L;

	public RoadLine() {

	}

	public RoadLine(Vector2 p1, Vector2 p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public void draw(ShapeRenderer renderer) {
		renderer.line(p1, p2);
	}

	public Vector2 getP1() {
		return p1;
	}

	public void setP1(Vector2 p1) {
		this.p1 = p1;
	}

	public Vector2 getP2() {
		return p2;
	}

	public void setP2(Vector2 p2) {
		this.p2 = p2;
	}

}
