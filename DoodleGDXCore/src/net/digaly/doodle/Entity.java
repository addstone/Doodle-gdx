package net.digaly.doodle;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public abstract class Entity
{
    private Sprite sprite;
    private int angle;
    private boolean visible;
    private double alpha;
    private int depth;
    private float width;
    private float height;
    private Room room;

    public Entity(Sprite sprite, float x, float y) {
        setSprite(sprite);
        this.getSprite().setPosition(x, y);
        this.width = getSprite().getWidth();
        this.height = getSprite().getHeight();
        this.angle = 0;
        this.visible = true;
        this.alpha = 1;
        this.room = new NoRoom();
    }

    public Sprite getSprite()
    {
        return sprite;
    }

    public void setSprite(Sprite sprite)
    {
        this.sprite = sprite;
    }

    public int getAngle()
    {
        return angle;
    }

    public void setAngle(int angle)
    {
        if (angle < 0) {
            while (angle <= -360) angle += 360;
        } else {
            while (angle > 360) angle -= 360;
        }

        this.angle = angle;
        this.getSprite().setRotation(angle);
    }

    public boolean isVisible()
    {
        return visible;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }


    public double getAlpha()
    {

        return alpha;
    }

    public void setAlpha(double alpha)
    {
        if (alpha > 1) alpha = 1;
        if (alpha < 0) alpha = 0;

        this.alpha = alpha;
    }

    public void destroy() {
        getRoom().removeEntity(this);
    }

    public int getDepth()
    {
        return depth;
    }

    public void setDepth(int depth)
    {
        this.depth = depth;
    }

    public Room getRoom()
    {
        return room;
    }

    public void setRoom(Room room)
    {
        this.room = room;
    }

    public float getWidth()
    {
        return width;
    }

    public void setWidth(float width)
    {
        this.width = width;
    }

    public float getHeight()
    {
        return height;
    }

    public void setHeight(float height)
    {
        this.height = height;
    }
}
