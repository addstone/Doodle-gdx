package net.digaly.doodle.test;

import net.digaly.doodle.DoodleApplication;

public class DoodleGDXTest extends DoodleApplication
{
    @Override
    public void onApplicationStart()
    {
        setCurrentRoom(new TestRoom());
    }
}
