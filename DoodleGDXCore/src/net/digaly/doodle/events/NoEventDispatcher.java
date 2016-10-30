package net.digaly.doodle.events;

import net.digaly.doodle.events.listeners.KeyEventListener;
import net.digaly.doodle.events.listeners.MouseEventListener;
import net.digaly.doodle.events.listeners.UpdateListener;

/**
 * Created by Tom Dobbelaere on 30/10/2016.
 */
public class NoEventDispatcher extends EventDispatcher
{
    @Override
    public void addUpdateListener(UpdateListener listener)
    {

    }

    @Override
    public void removeUpdateListener(UpdateListener listener)
    {

    }

    @Override
    public void notifyUpdateListeners()
    {

    }

    @Override
    public void addKeyEventListener(KeyEventListener listener)
    {

    }

    @Override
    public void removeKeyEventListener(KeyEventListener listener)
    {

    }

    @Override
    public void notifyKeyEventListeners(KeyEvent keyEvent)
    {

    }

    @Override
    public void addMouseEventListener(MouseEventListener listener)
    {

    }

    @Override
    public void removeMouseEventListener(MouseEventListener listener)
    {

    }

    @Override
    public void notifyMouseEventListeners(MouseEvent mouseEvent)
    {

    }
}
