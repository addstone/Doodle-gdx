package net.digaly.doodle.test;

import com.badlogic.gdx.graphics.g2d.Sprite;
import net.digaly.doodle.Entity;
import net.digaly.doodle.events.listeners.UpdateListener;

/**
 * Created by Tom Dobbelaere on 3/10/2016.
 */
public class BulletEntity extends Entity implements UpdateListener
{
    private int speed;

    public BulletEntity(Sprite sprite, float x, float y, int angle, int speed)
    {
        super(sprite, x, y);
        setAngle(angle);
        this.speed = speed;
        setDepth(5);
    }

    @Override
    public void onUpdate()
    {
        getSprite().setPosition(getSprite().getX() + (float) (Math.cos(getAngle() * 0.017) * speed),
                getSprite().getY() + (float) (Math.sin(getAngle() * 0.017) * speed));

        getSprite().setAlpha(getSprite().getColor().a - 0.01f);

        if (getSprite().getColor().a <= 0) destroy();

    }
}
