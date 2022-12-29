package UwU_.GameSense.event.events;

public abstract class EventStoppable implements Event {

    private boolean stopped;

    public void stop() {
        stopped = true;
    }

    public boolean isStopped() {
        return stopped;
    }

}
