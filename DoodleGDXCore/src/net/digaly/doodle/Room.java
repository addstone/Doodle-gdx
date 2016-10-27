package net.digaly.doodle;

import com.badlogic.gdx.graphics.g2d.Sprite;

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

    public Room() {
        this.entities = new CopyOnWriteArrayList<>();
    }

    public void sortEntitiesByDepth() {
        entities.sort(Comparator.comparing(Entity::getDepth));
    }

    public void addEntity(Entity entity) {
        entity.setRoom(this);
        this.entities.add(entity);

        sortEntitiesByDepth();
    }

    public void removeEntity(Entity entity) {
        entity.setRoom(new NoRoom());
        this.entities.remove(entity);

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

    public void setBackground(Sprite background)
    {
        this.background = background;
    }

    public void destroy() {
        for (Entity e : entities) {
            removeEntity(e);
        }

        entities = null;
    }
}
