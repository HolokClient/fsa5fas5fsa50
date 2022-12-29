package UwU_.GameSense.notifications;

import UwU_.GameSense.helpers.render.Translate;

public class Notification {

    public String text;
    public NotificationType type;
    public String header;

    public long startTime;

    public Translate translate = new Translate(0, 0);

    public Notification(String text, NotificationType type, String header) {
        this.text = text;
        this.type = type;
        this.header = header;
        startTime = System.currentTimeMillis();
    }

}
