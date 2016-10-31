package net.digaly.doodle;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Tom Dobbelaere on 31/10/2016.
 */
public class NoDoodleStage extends DoodleStage
{
    public NoDoodleStage()
    {
        super(new FitViewport(0, 0));
    }
}
