package net.digaly.doodle.events;

/**
 * Created by Tom Dobbelaere on 30/10/2016.
 */
public class KeyEvent
{
    private int keyCode;
    private KeyState keyState;

    public KeyEvent(int keyCode, KeyState keyState) {
        this.keyCode = keyCode;
        this.keyState = keyState;
    }

    public int getKeyCode()
    {
        return keyCode;
    }

    public KeyState getKeyState()
    {
        return keyState;
    }
}
