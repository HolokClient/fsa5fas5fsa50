package UwU_.GameSense.event.events.impl;

import net.minecraft.network.Packet;
import UwU_.GameSense.event.events.Event;
import UwU_.GameSense.event.events.callables.EventCancellable;

public class EventPacket extends EventCancellable implements Event {

    private Packet packet;

    public EventPacket(Packet packet) {
        this.packet = packet;
    }

    public Packet getPacket() {
        return packet;
    }



}
