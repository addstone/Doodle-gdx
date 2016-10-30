package net.digaly.doodle.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import net.digaly.doodle.Entity;
import net.digaly.doodle.NoRoom;
import net.digaly.doodle.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom Dobbelaere on 29/10/2016.
 */
public class Renderer
{
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private int targetWidth;
    private Room room;

    public Renderer()
    {
        this.targetWidth = 800;
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(true);
        this.viewport = new FitViewport(this.targetWidth, this.targetWidth / ((float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight()), camera);
        viewport.apply();
        this.camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        this.room = new NoRoom();
    }

    public void render()
    {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        room.getBackground().draw(batch);

        for (Entity entity : room.getEntities())
        {
            entity.getSprite().draw(batch);
        }

        batch.end();
    }

    public void resize(int width, int height)
    {
        viewport.update(width, height);
        updateViewportSize();
    }

    public void setTargetWidth(int targetWidth)
    {
        this.targetWidth = targetWidth;
        updateViewportSize();
    }

    private void updateViewportSize() {
        viewport.setWorldSize(targetWidth, targetWidth / ((float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight()));
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    public OrthographicCamera getCamera()
    {
        return camera;
    }

    public void setRoom(Room room)
    {
        this.room = room;
    }
}
