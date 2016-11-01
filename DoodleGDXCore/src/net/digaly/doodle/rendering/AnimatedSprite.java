package net.digaly.doodle.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Tom Dobbelaere on 31/10/2016.
 */
public class AnimatedSprite extends Sprite
{
    private Animation animation;
    private float elapsedTime;
    private boolean looping;

    public AnimatedSprite(Texture spriteSheet, int frameWidth, int frameHeight, int columnCount, int rowCount, float frameDuration, boolean loop)
    {
        TextureRegion[][] textureRegions = TextureRegion.split(spriteSheet, frameWidth, frameHeight);

        TextureRegion[] frames = new TextureRegion[columnCount * rowCount];

        int index = 0;
        for (int y = 0; y < rowCount; y++)
        {
            for (int x = 0; x < columnCount; x++)
            {
                frames[index] = textureRegions[y][x];
                index++;
            }
        }

        setSize(frameWidth, frameHeight);
        setOrigin(frameWidth / 2, frameHeight / 2);

        this.looping = loop;
        this.animation = new Animation(frameDuration, frames);
    }

    @Override
    public void draw(Batch batch)
    {
        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.draw(this.animation.getKeyFrame(elapsedTime, this.looping), getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), -getScaleY(), getRotation());

    }

    public boolean isLooping()
    {
        return looping;
    }

    public void setLooping(boolean looping)
    {
        this.looping = looping;
    }
}
