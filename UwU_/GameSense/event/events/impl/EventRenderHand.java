package UwU_.GameSense.event.events.impl;

import net.minecraft.util.EnumHandSide;
import UwU_.GameSense.event.events.Event;

public class EventRenderHand implements Event {

    public EnumHandSide e;

    public EventRenderHand(EnumHandSide e) {
        this.e = e;
    }


}
