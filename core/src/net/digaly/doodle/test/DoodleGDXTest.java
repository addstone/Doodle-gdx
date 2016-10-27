package net.digaly.doodle.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.digaly.doodle.Entity;
import net.digaly.doodle.Room;

public class DoodleGDXTest extends ApplicationAdapter implements InputProcessor
{
    SpriteBatch batch;
    Room currentRoom;
    OrthographicCamera camera;
    //FitViewport viewport;

    @Override
    public void create()
    {
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        //viewport = new FitViewport(800, 600, camera);
        camera.setToOrtho(true, 1920, 1080);
        currentRoom = new TestRoom();
        currentRoom.addEntity(new PlayerEntity(64, 64));
        Music music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.play();

        Gdx.graphics.setContinuousRendering(true);
    }

    @Override
    public void resize(int width, int height)
    {
        //viewport.update(width, height);
    }

    @Override
    public void render()
    {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        if (currentRoom.getBackground() != null) {
            currentRoom.getBackground().draw(batch);
        }

        for (Entity entity : currentRoom.getEntities())
        {
            camera.position.set(new Vector2(entity.getSprite().getX(), entity.getSprite().getY()), 0f);
            //camera. = new Vector3(entity.getSprite().getX(), entity.getSprite().getY(), 0);

            //viewport.setScreenPosition((int) entity.getSprite().getX(), (int) entity.getSprite().getY());

            entity.update();
            entity.getSprite().draw(batch);
            //batch.setColor(entity.getSprite().getColor());
            /*batch.draw(entity.getSprite().getTexture(), entity.getPosition().x, entity.getPosition().y, entity.getWidth() / 2, entity.getHeight() / 2,
                    entity.getWidth(), entity.getHeight(), 1, 1, entity.getAngle(),
                    0, 0, (int) entity.getWidth(), (int) entity.getHeight(), false, false);*/
        }

        batch.end();
    }

    @Override
    public void dispose()
    {
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode)
    {
        for (Entity entity : currentRoom.getEntities())
        {
            if (entity instanceof InputProcessor) {
                ((InputProcessor) entity).keyDown(keycode);
            }
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        for (Entity entity : currentRoom.getEntities())
        {
            if (entity instanceof InputProcessor) {
                ((InputProcessor) entity).keyUp(keycode);
            }
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character)
    {
        for (Entity entity : currentRoom.getEntities())
        {
            if (entity instanceof InputProcessor) {
                ((InputProcessor) entity).keyTyped(character);
            }
        }

        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        for (Entity entity : currentRoom.getEntities())
        {
            if (entity instanceof InputProcessor) {
                ((InputProcessor) entity).touchDown(screenX, screenY, pointer, button);
            }
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        for (Entity entity : currentRoom.getEntities())
        {
            if (entity instanceof InputProcessor) {
                ((InputProcessor) entity).touchUp(screenX, screenY, pointer, button);
            }
        }

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        for (Entity entity : currentRoom.getEntities())
        {
            if (entity instanceof InputProcessor) {
                ((InputProcessor) entity).touchDragged(screenX, screenY, pointer);
            }
        }

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        for (Entity entity : currentRoom.getEntities())
        {
            if (entity instanceof InputProcessor) {
                ((InputProcessor) entity).mouseMoved(screenX, screenY);
            }
        }

        return true;
    }

    @Override
    public boolean scrolled(int amount)
    {
        for (Entity entity : currentRoom.getEntities())
        {
            if (entity instanceof InputProcessor) {
                ((InputProcessor) entity).scrolled(amount);
            }
        }

        return true;
    }
}
