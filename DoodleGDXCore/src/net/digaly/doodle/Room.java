package net.digaly.doodle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import javafx.scene.input.MouseEvent;
import net.digaly.doodle.events.listeners.KeyEventListener;
import net.digaly.doodle.events.listeners.MouseEventListener;
import net.digaly.doodle.events.listeners.UpdateListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Tom Dobbelaere on 2/10/2016.
 */
public class Room
{
    private List<Entity> entities;
    private Sprite background;
    private Dimension size;
    private ApplicationContext applicationContext;

    public Room(int width, int height, ApplicationContext context) {
        this.entities = new CopyOnWriteArrayList<>();
        this.size = new Dimension(width, height);
        this.background = new Sprite();
        this.applicationContext = context;
    }

    public void sortEntitiesByDepth() {
        entities.sort(Comparator.comparing(Entity::getDepth));
    }

    public void addEntity(Entity entity) {
        entity.setRoom(this);
        this.entities.add(entity);

        if (entity instanceof KeyEventListener) {
            applicationContext.getEventDispatcher().addKeyEventListener((KeyEventListener) entity);
        }

        if (entity instanceof MouseEventListener) {
            applicationContext.getEventDispatcher().addMouseEventListener((MouseEventListener) entity);
        }

        if (entity instanceof UpdateListener) {
            applicationContext.getEventDispatcher().addUpdateListener((UpdateListener) entity);
        }

        sortEntitiesByDepth();
    }

    public void removeEntity(Entity entity) {
        entity.setRoom(new NoRoom());
        this.entities.remove(entity);

        if (entity instanceof KeyEventListener) {
            applicationContext.getEventDispatcher().removeKeyEventListener((KeyEventListener) entity);
        }

        if (entity instanceof MouseEventListener) {
            applicationContext.getEventDispatcher().removeMouseEventListener((MouseEventListener) entity);
        }

        if (entity instanceof UpdateListener) {
            applicationContext.getEventDispatcher().removeUpdateListener((UpdateListener) entity);
        }

        sortEntitiesByDepth();

        entity = null;
    }

    public Entity findEntity(Class search) {
        for (Entity entity : entities) {
            if (entity.getClass() == search) {
                return entity;
            }
        }

        return null;
    }

    public List<Entity> findEntities(Class search) {
        List<Entity> entityList = new ArrayList<>();

        for (Entity entity : entities) {
            if (entity.getClass() == search) {
                entityList.add(entity);
            }
        }

        return entityList;
    }

    public List<Entity> getEntities() {
        return this.entities;
    }

    public Sprite getBackground()
    {
        return background;
    }

    public void setBackgroundFromFilename(String filename)
    {
        Texture backgroundTexture = new Texture(filename);
        backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        int xRepeat = (int) Math.ceil(size.getWidth() / backgroundTexture.getWidth());
        int yRepeat = (int) Math.ceil(size.getHeight() / backgroundTexture.getHeight());

        Sprite backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setRegion(0, 0, backgroundTexture.getWidth() * xRepeat, backgroundTexture.getHeight() * yRepeat);
        backgroundSprite.setSize(backgroundTexture.getWidth() * xRepeat, backgroundTexture.getHeight() * yRepeat);

        this.background = backgroundSprite;
    }

    public void destroy() {
        for (Entity e : entities) {
            removeEntity(e);
        }

        entities = null;
    }

    public Dimension getSize()
    {
        return size;
    }

    public ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext)
    {
        this.applicationContext = applicationContext;
    }
}
