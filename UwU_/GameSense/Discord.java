package UwU_.GameSense;

import net.arikia.dev.drpc.*;


public class Discord {

    private boolean running = true;
    private static long created = 0;

    public void start() {

        this.created = System.currentTimeMillis();

        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(user -> {
            update("paid client.", "best bots.");
        }).build();

        DiscordRPC.discordInitialize("1018825698007318619", handlers, true);
        new Thread("Discord RPC Callback") {

            @Override
            public void run() {

                while(running) {
                    DiscordRPC.discordRunCallbacks();
                }

            }

        }.start();


    }

    public void shutdown() {
        running = false;
        DiscordRPC.discordShutdown();
    }

    public static void update(String firstLine, String secondLine) {
        DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(secondLine);

        b.setBigImage("https://c.tenor.com/sVNO62-MYV0AAAAC/zxc-cat.gif", "botting.");
        b.setSmallImage("https://c.tenor.com/elj6q-hfJnIAAAAd/cat-%D0%BB%D0%B0%D0%B4%D0%BD%D0%BE.gif", "made by salam4ik.");
        b.setDetails(firstLine);
        b.setStartTimestamps(created);


        DiscordRPC.discordUpdatePresence(b.build());
    }
}