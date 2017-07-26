package rs.ac.uns.ftn.nansi.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class InformationDisplay {

    private static SpriteBatch batch = new SpriteBatch();
    private static BitmapFont font = new BitmapFont();

    private static ArrayList<String> informationToDisplay = new ArrayList<String>();

    private static final int POS_X = 10;
    private static final int POS_Y = Gdx.graphics.getHeight() - 10;

    public static void render() {

        batch.begin();

        for (int i = 0; i < informationToDisplay.size(); i++)
            font.draw(batch, informationToDisplay.get(i), POS_X, POS_Y - i * 20);

        batch.end();
    }

    public static void setColor(Color c) {
        font.setColor(c);
    }

    public static void setRow(int i, String s) {
        try {
            informationToDisplay.set(i, s);
        } catch (Exception ex) {
            informationToDisplay.add(i, s);
        }
    }
}
