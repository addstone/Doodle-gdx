package net.digaly.doodle;

import net.digaly.doodle.events.EventDispatcher;
import net.digaly.doodle.events.NoEventDispatcher;
import net.digaly.doodle.rendering.Renderer;

import javax.management.RuntimeErrorException;

/**
 * Created by Tom Dobbelaere on 30/10/2016.
 */
public class NoApplicationContext implements ApplicationContext
{
    private EventDispatcher noEventDispatcher;

    public NoApplicationContext() {
        this.noEventDispatcher = new NoEventDispatcher();
    }

    @Override
    public void setCurrentRoom(Room room)
    {

    }

    @Override
    public void setCurrentStage(DoodleStage stage)
    {

    }

    @Override
    public void setRenderStage(boolean value)
    {

    }

    @Override
    public void setRenderRoom(boolean value)
    {

    }

    @Override
    public void setInputMode(InputMode inputMode)
    {

    }

    /*
    We allow attaching events to NoEventDispatchers because this is commonly done in the constructor of a room
    When the application context changes, events are rebound anyway
    */
    @Override
    public EventDispatcher getEventDispatcher()
    {
        return noEventDispatcher;
    }

    @Override
    public Renderer getRenderer()
    {
        throw new RuntimeException("Cannot get renderer because there is no application context!");
    }
}
