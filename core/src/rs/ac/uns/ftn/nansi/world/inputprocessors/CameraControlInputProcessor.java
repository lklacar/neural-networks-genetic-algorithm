package rs.ac.uns.ftn.nansi.world.inputprocessors;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class CameraControlInputProcessor implements InputProcessor {

	private OrthographicCamera camera;

	public CameraControlInputProcessor(OrthographicCamera camera) {
		this.camera = camera;

	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.LEFT)
			camera.translate(new Vector2(-1, 0));
		if (keycode == Keys.RIGHT)
			camera.translate(new Vector2(1, 0));
		if (keycode == Keys.DOWN)
			camera.translate(new Vector2(0, -1));
		if (keycode == Keys.UP)
			camera.translate(new Vector2(0, 1));

		camera.update();

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

}
