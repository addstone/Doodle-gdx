package net.digaly.doodle.test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import net.digaly.doodle.ApplicationContext;
import net.digaly.doodle.Room;

/**
 * Created by Tom Dobbelaere on 27/10/2016.
 */
public class TestRoom extends Room
{
    public TestRoom(ApplicationContext context)
    {
        super(800, 600, context);

        setBackgroundFromFilename("bg-crumplesquare.png");

        this.addEntity(new PlayerEntity(64, 64));
    }
}
