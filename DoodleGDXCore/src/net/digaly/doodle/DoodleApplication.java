package net.digaly.doodle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import net.digaly.doodle.events.EventDispatcher;
import net.digaly.doodle.input.InputManager;
import net.digaly.doodle.rendering.Renderer;

/**
 * Created by Tom Dobbelaere on 29/10/2016.
 */
public abstract class DoodleApplication extends ApplicationAdapter implements ApplicationContext
{
    private Room currentRoom;
    private DoodleStage currentStage;
    private Renderer renderer;
    private EventDispatcher eventDispatcher;
    private InputManager inputManager;

    @Override
    public void create()
    {
        this.renderer = new Renderer();
        this.eventDispatcher = new EventDispatcher();
        this.inputManager = new InputManager(this.eventDispatcher, this.renderer);
        setInputMode(InputMode.ROOM);

        setCurrentRoom(new Room(800, 600));

        onApplicationStart();
    }

    @Override
    public void resize(int width, int height)
    {
        this.renderer.resize(width, height);
    }

    @Override
    public void render()
    {
        this.eventDispatcher.notifyUpdateListeners();
        this.inputManager.notifyHeldKeys();
        this.inputManager.notifyHeldMouseButtons();
        this.renderer.render();
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void dispose()
    {

    }

    @Override
    public void setInputMode(InputMode inputMode)
    {
        switch (inputMode)
        {
            case STAGE:
                Gdx.input.setInputProcessor(this.currentStage);
                break;
            case ROOM:
                Gdx.input.setInputProcessor(this.inputManager);
                break;
            case STAGE_AND_ROOM:
                Gdx.input.setInputProcessor(new InputMultiplexer(this.currentStage, this.inputManager));
                break;
        }
    }

    @Override
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
        this.currentRoom.setApplicationContext(this);
        this.renderer.setRoom(this.currentRoom);
        this.renderer.setTargetWidth((int) this.currentRoom.getSize().getWidth());
    }

    @Override
    public void setCurrentStage(DoodleStage stage)
    {
        this.currentStage = stage;
        this.currentStage.setApplicationContext(this);
        this.renderer.setStage(this.currentStage);
    }

    @Override
    public void setRenderStage(boolean value)
    {
        this.renderer.setRenderingStage(value);
    }

    @Override
    public void setRenderRoom(boolean value)
    {
        this.renderer.setRenderingRoom(value);
    }

    @Override
    public Renderer getRenderer()
    {
        return this.renderer;
    }

    @Override
    public EventDispatcher getEventDispatcher()
    {
        return this.eventDispatcher;
    }

    public abstract void onApplicationStart();
}
