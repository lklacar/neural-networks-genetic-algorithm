package rs.ac.uns.ftn.nansi.world.inputprocessors;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import rs.ac.uns.ftn.nansi.world.GameWorld;

public class WorldInputProcessor extends InputAdapter {

    private GameWorld world;

    public WorldInputProcessor(GameWorld world) {
        this.world = world;
    }

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == Keys.P) {
            world.togglePause();
        }
        return false;
    }
}
