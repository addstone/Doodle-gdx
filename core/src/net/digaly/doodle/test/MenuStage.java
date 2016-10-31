package net.digaly.doodle.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import net.digaly.doodle.DoodleStage;

/**
 * Created by Tom Dobbelaere on 31/10/2016.
 */
public class MenuStage extends DoodleStage
{
    public MenuStage() {
        super(new ScreenViewport());

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Quikhand.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        BitmapFont font12 = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!


        Skin skin = new Skin();
        skin.add(
                "background",
                new NinePatch(new Texture("ninepatch.png"), 16, 16, 16, 16));
        //skin.add("cursor", this.game.manager.get("data/cursor.png"));

        TextField.TextFieldStyle tStyle = new TextField.TextFieldStyle();
        tStyle.font = font12; //here i get the font
        tStyle.fontColor = Color.BLACK;
        tStyle.background = skin.getDrawable("background");
        //tStyle.cursor = skin.newDrawable("cursor", Color.GREEN);
        //tStyle.cursor.setMinWidth(2f);
        tStyle.selection = skin.newDrawable("background", 0.5f, 0.5f, 0.5f,
                0.5f);

        TextField textField = new TextField("Sex", tStyle);
        addActor(textField);
    }
}
