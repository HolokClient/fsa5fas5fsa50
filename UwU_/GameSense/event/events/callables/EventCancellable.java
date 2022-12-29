package UwU_.GameSense.event.events.callables;

import UwU_.GameSense.event.events.Cancellable;
import UwU_.GameSense.event.events.Event;

public abstract class EventCancellable implements Event, Cancellable {

    private boolean cancelled;

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void cancel() {
        cancelled = true;
    }

}
