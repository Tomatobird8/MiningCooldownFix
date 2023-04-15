package net.covenantturtle.miningcooldownfix;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "miningcooldownfix")
public class MyConfig implements ConfigData {
    public int mineDelay = 2;
    public boolean allowBreakReset = false;
    public boolean allowBreakCancel = true;
}