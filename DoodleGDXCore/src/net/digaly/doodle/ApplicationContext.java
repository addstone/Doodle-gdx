package net.digaly.doodle;

import net.digaly.doodle.events.EventDispatcher;

/**
 * Created by Tom Dobbelaere on 30/10/2016.
 */
public interface ApplicationContext
{
    void setCurrentRoom(Room room);
    void setCurrentStage(DoodleStage stage);
    void setRenderStage(boolean value);
    void setRenderRoom(boolean value);
    void setInputMode(InputMode inputMode);
    EventDispatcher getEventDispatcher();
}
