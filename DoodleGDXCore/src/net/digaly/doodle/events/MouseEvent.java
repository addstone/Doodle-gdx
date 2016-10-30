package net.digaly.doodle.events;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tom Dobbelaere on 30/10/2016.
 */
public class MouseEvent
{
    private int worldX;
    private int worldY;
    private int pointer;
    private int button;
    private int scrollAmount;
    private MouseState mouseState;

    public MouseEvent(int worldX, int worldY, int pointer, int button, MouseState mousestate) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.pointer = pointer;
        this.button = button;
        this.scrollAmount = 0;
        this.mouseState = mousestate;
    }

    public MouseEvent(int worldX, int worldY, int pointer, MouseState mousestate) {
        this(worldX, worldY, pointer, -1, mousestate);
    }

    public MouseEvent(int worldX, int worldY, MouseState mousestate) {
        this(worldX, worldY, 0, mousestate);
    }

    public MouseEvent(int scrollAmount, MouseState mousestate) {
        this(0, 0, mousestate);
        this.scrollAmount = scrollAmount;
    }

    public int getWorldX()
    {
        return worldX;
    }

    public int getWorldY()
    {
        return worldY;
    }

    public int getPointer()
    {
        return pointer;
    }

    public int getButton()
    {
        return button;
    }

    public int getScrollAmount()
    {
        return scrollAmount;
    }

    public MouseState getMouseState()
    {
        return mouseState;
    }

    public boolean isInBoundsOfRectangle(Rectangle rectangle) {
        return (getWorldX() >= rectangle.getX()
                && getWorldY() >= rectangle.getY()
                && getWorldX() <= rectangle.getX() + rectangle.getWidth()
                && getWorldY() <= rectangle.getY() + rectangle.getHeight());
    }
}
