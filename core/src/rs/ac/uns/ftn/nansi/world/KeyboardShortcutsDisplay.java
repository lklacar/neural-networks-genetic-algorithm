package rs.ac.uns.ftn.nansi.world;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class KeyboardShortcutsDisplay {
	
	private static SpriteBatch batch = new SpriteBatch();
	private static BitmapFont font = new BitmapFont();

	private static ArrayList<String> informationToDisplay = new ArrayList<String>();

	private static final int POS_X = Gdx.graphics.getWidth() - 200;
	private static final int POS_Y = 30;
	

	public static void addRow(String s) {
		informationToDisplay.add(s);
	}

	public static String getRow(int i) {
		return informationToDisplay.get(i);
	}

	public static void clear() {
		informationToDisplay.clear();
	}

	public static void render() {

		batch.begin();

		for (int i = 0; i < informationToDisplay.size(); i++)
			font.draw(batch, informationToDisplay.get(i), POS_X, POS_Y + i * 20);

		batch.end();
	}

	public static void setColor(Color c) {
		font.setColor(c);
		;
	}

	public static void setRow(int i, String s) {
		try {
			informationToDisplay.set(i, s);
		} catch (Exception ex) {
			informationToDisplay.add(i, s);
		}

	}
	

}
