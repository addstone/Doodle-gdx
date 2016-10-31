package net.digaly.doodle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Tom Dobbelaere on 31/10/2016.
 */
public class DoodleStage extends Stage
{
    private ApplicationContext applicationContext;

    public DoodleStage(Viewport viewport)
    {
        super(viewport);
        this.applicationContext = new NoApplicationContext();
    }

    public DoodleStage(Viewport viewport, Batch batch)
    {
        super(viewport, batch);
        this.applicationContext = new NoApplicationContext();
    }

    public void setApplicationContext(ApplicationContext applicationContext)
    {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }
}
