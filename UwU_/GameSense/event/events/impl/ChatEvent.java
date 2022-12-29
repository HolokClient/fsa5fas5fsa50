package UwU_.GameSense.event.events.impl;

import UwU_.GameSense.event.events.Event;

public class ChatEvent implements Event {
    public String message;
    public ChatEvent(String message) {
        this.message = message;
    }
}
