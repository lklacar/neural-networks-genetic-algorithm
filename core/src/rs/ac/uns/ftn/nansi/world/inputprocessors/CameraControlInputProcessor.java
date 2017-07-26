package rs.ac.uns.ftn.nansi.world.inputprocessors;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class CameraControlInputProcessor extends InputAdapter {

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
}
