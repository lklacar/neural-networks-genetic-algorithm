package rs.ac.uns.ftn.nansi.world.inputprocessors;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import rs.ac.uns.ftn.nansi.world.GameWorld;

public class WorldInputProcessor implements InputProcessor {

    private GameWorld world;

    public WorldInputProcessor(GameWorld world) {
        this.world = world;
    }

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == Keys.P) {
            world.tooglePause();
        }
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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

}
