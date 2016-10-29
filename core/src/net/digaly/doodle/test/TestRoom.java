package net.digaly.doodle.test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import net.digaly.doodle.Room;

/**
 * Created by Tom Dobbelaere on 27/10/2016.
 */
public class TestRoom extends Room
{
    public TestRoom()
    {
        super(100, 100);

        Texture bgTexture = new Texture("bg-crumplesquare.png");
        bgTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        Sprite backgroundSprite = new Sprite(bgTexture);
        backgroundSprite.setRegion(0, 0, bgTexture.getWidth() * 5, bgTexture.getHeight() * 5);
        backgroundSprite.setSize(bgTexture.getWidth() * 5, bgTexture.getHeight() * 5);

        setBackground(backgroundSprite);

        this.addEntity(new PlayerEntity(64, 64));
    }
}
