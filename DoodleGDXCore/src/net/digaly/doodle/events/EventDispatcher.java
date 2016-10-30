package net.digaly.doodle.events;

import net.digaly.doodle.events.listeners.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Tom Dobbelaere on 30/10/2016.
 */
public class EventDispatcher
{
    private List<UpdateListener> updateListeners;
    private List<KeyEventListener> keyEventListeners;
    private List<MouseEventListener> mouseEventListeners;

    public EventDispatcher() {
        updateListeners = new CopyOnWriteArrayList<>();
        keyEventListeners = new CopyOnWriteArrayList<>();
        mouseEventListeners = new CopyOnWriteArrayList<>();
    }

    public void addUpdateListener(UpdateListener listener) {
        updateListeners.add(listener);
    }

    public void removeUpdateListener(UpdateListener listener) {
        updateListeners.remove(listener);
    }

    public void notifyUpdateListeners() {
        for (UpdateListener listener : updateListeners) {
            listener.onUpdate();
        }
    }

    public void addKeyEventListener(KeyEventListener listener) {
        keyEventListeners.add(listener);
    }

    public void removeKeyEventListener(KeyEventListener listener) {
        keyEventListeners.remove(listener);
    }

    public void notifyKeyEventListeners(KeyEvent keyEvent) {
        for (KeyEventListener listener : keyEventListeners) {
            listener.onKeyEvent(keyEvent);
        }
    }

    public void addMouseEventListener(MouseEventListener listener) {
        mouseEventListeners.add(listener);
    }

    public void removeMouseEventListener(MouseEventListener listener) {
        mouseEventListeners.remove(listener);
    }

    public void notifyMouseEventListeners(MouseEvent mouseEvent) {
        for (MouseEventListener listener : mouseEventListeners) {
            listener.onMouseEvent(mouseEvent);
        }
    }

}
