package rs.ac.uns.ftn.nansi;

import rs.ac.uns.ftn.nansi.world.GameWorld;
import rs.ac.uns.ftn.nansi.world.InformationDisplay;
import rs.ac.uns.ftn.nansi.world.KeyboardShortcutsDisplay;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class NansiProject extends Game {

	private ShapeRenderer shapeRenderer;
	private Box2DDebugRenderer debugRenderer;

	private GameWorld gameWorld;

	public void setGameWorld(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
	}

	private void addShortcuts() {
		KeyboardShortcutsDisplay.addRow("Arrows -    Move Camera");
		KeyboardShortcutsDisplay.addRow("Space  -    New Generation");
		KeyboardShortcutsDisplay.addRow("P      -    Pause");
	}

	@Override
	public void create() {
		shapeRenderer = new ShapeRenderer();
		gameWorld = GameWorld.getInstance();
		debugRenderer = new Box2DDebugRenderer();
		InformationDisplay.setColor(Color.WHITE);
		KeyboardShortcutsDisplay.setColor(Color.WHITE);

		addShortcuts();

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gameWorld.draw(shapeRenderer, debugRenderer);
		InformationDisplay.render();
		KeyboardShortcutsDisplay.render();

	}

}
