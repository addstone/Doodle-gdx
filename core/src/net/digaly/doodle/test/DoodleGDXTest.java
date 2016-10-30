package net.digaly.doodle.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import net.digaly.doodle.DoodleApplication;
import net.digaly.doodle.events.EventDispatcher;

public class DoodleGDXTest extends DoodleApplication
{
    @Override
    public void onApplicationStart()
    {
        setCurrentRoom(new TestRoom(this));
        Music menuMusic = Gdx.audio.newMusic(Gdx.files.internal("menu.ogg"));
        menuMusic.setLooping(true);
        menuMusic.play();
    }
}
