package net.digaly.doodle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import net.digaly.doodle.events.EventDispatcher;
import net.digaly.doodle.input.InputManager;
import net.digaly.doodle.rendering.Renderer;

/**
 * Created by Tom Dobbelaere on 29/10/2016.
 */
public abstract class DoodleApplication extends ApplicationAdapter implements ApplicationContext
{
    private Room currentRoom;
    private Renderer renderer;
    private EventDispatcher eventDispatcher;
    private InputManager inputManager;

    @Override
    public void create()
    {
        this.renderer = new Renderer();
        this.eventDispatcher = new EventDispatcher();
        this.inputManager = new InputManager(this.eventDispatcher, this.renderer);
        Gdx.input.setInputProcessor(this.inputManager);

        setCurrentRoom(new Room(800, 600, this));

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
        this.eventDispatcher.notifyUpdateListeners();
        this.inputManager.notifyHeldKeys();
        this.inputManager.notifyHeldMouseButtons();
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

    @Override
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
        this.currentRoom.setApplicationContext(this);
        this.renderer.setRoom(this.currentRoom);
        this.renderer.setTargetWidth((int) this.currentRoom.getSize().getWidth());
    }

    @Override
    public EventDispatcher getEventDispatcher()
    {
        return this.eventDispatcher;
    }

    public abstract void onApplicationStart();
}
