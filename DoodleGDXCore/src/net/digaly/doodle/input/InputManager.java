package net.digaly.doodle.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import net.digaly.doodle.events.*;
import net.digaly.doodle.rendering.Renderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tom Dobbelaere on 30/10/2016.
 */
public class InputManager implements InputProcessor
{
    private EventDispatcher eventDispatcher;
    private Renderer renderer;
    private List<Integer> heldKeys;
    private Map<Integer, MouseEvent> heldMouseButtons;

    public InputManager(EventDispatcher dispatcher, Renderer renderer) {
        this.eventDispatcher = dispatcher;
        this.renderer = renderer;
        this.heldKeys = new ArrayList<>();
        this.heldMouseButtons = new HashMap<>();
    }

    public void notifyHeldKeys() {
        for (Integer keyCode : heldKeys) {
            eventDispatcher.notifyKeyEventListeners(new KeyEvent(keyCode, KeyState.HOLDING));
        }
    }

    public void notifyHeldMouseButtons() {
        for (Integer mouseButton : heldMouseButtons.keySet()) {
            eventDispatcher.notifyMouseEventListeners(heldMouseButtons.get(mouseButton));
        }
    }

    @Override
    public boolean keyDown(int keycode)
    {
        heldKeys.add(keycode);
        eventDispatcher.notifyKeyEventListeners(new KeyEvent(keycode, KeyState.PRESSED));

        return true;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        heldKeys.remove((Integer) keycode);
        eventDispatcher.notifyKeyEventListeners(new KeyEvent(keycode, KeyState.RELEASED));

        return true;
    }

    @Override
    public boolean keyTyped(char character)
    {
        eventDispatcher.notifyKeyEventListeners(new KeyEvent(character, KeyState.TYPED));

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        Vector3 worldCoords = renderer.getCamera().unproject(new Vector3(screenX, screenY, 0));

        heldMouseButtons.put(button, new MouseEvent((int) worldCoords.x, (int) worldCoords.y, pointer, button, MouseState.HOLDING));
        eventDispatcher.notifyMouseEventListeners(
                new MouseEvent((int) worldCoords.x, (int) worldCoords.y, pointer, button, MouseState.PRESSED));

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        Vector3 worldCoords = renderer.getCamera().unproject(new Vector3(screenX, screenY, 0));

        heldMouseButtons.remove(button);
        eventDispatcher.notifyMouseEventListeners(
                new MouseEvent((int) worldCoords.x, (int) worldCoords.y, pointer, button, MouseState.RELEASED));

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        Vector3 worldCoords = renderer.getCamera().unproject(new Vector3(screenX, screenY, 0));
        eventDispatcher.notifyMouseEventListeners(
                new MouseEvent((int) worldCoords.x, (int) worldCoords.y, pointer, MouseState.DRAGGED));

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        Vector3 worldCoords = renderer.getCamera().unproject(new Vector3(screenX, screenY, 0));
        eventDispatcher.notifyMouseEventListeners(
                new MouseEvent((int) worldCoords.x, (int) worldCoords.y, MouseState.MOVED));

        return true;
    }

    @Override
    public boolean scrolled(int amount)
    {
        eventDispatcher.notifyMouseEventListeners(
                new MouseEvent(amount, MouseState.SCROLLED));

        return true;
    }
}
