package net.digaly.doodle;

import com.badlogic.gdx.ApplicationAdapter;

/**
 * Created by Tom Dobbelaere on 29/10/2016.
 */
public abstract class DoodleApplication extends ApplicationAdapter
{
    private Room currentRoom;
    private Renderer renderer;

    @Override
    public void create()
    {
        this.renderer = new Renderer();

        setCurrentRoom(new Room(800, 600));

        onApplicationStart();
    }

    @Override
    public void resize(int width, int height)
    {
        this.renderer.resize(width, height);
    }

    @Override
    public void render()
    {
        this.renderer.render();
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void dispose()
    {

    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
        this.renderer.setRoom(this.currentRoom);
        this.renderer.setTargetWidth((int) this.currentRoom.getSize().getWidth());
    }

    public abstract void onApplicationStart();
}
