package UwU_.GameSense.event.events.impl;

import net.minecraft.client.gui.ScaledResolution;
import UwU_.GameSense.event.events.Event;

public class EventDisplay implements Event {
    public float ticks;
    public ScaledResolution sr;

    public EventDisplay(float t, ScaledResolution sr) {
        this.sr = sr;
        ticks = t;
    }
}
