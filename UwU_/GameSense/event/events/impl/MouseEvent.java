package UwU_.GameSense.event.events.impl;

import UwU_.GameSense.event.events.Event;

public class MouseEvent implements Event {

    public int button;

    public MouseEvent(int button) {
        this.button = button;
    }

}
