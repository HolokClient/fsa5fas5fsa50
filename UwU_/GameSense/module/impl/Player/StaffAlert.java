package UwU_.GameSense.module.impl.Player;

import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.network.play.server.SPacketPlayerListItem;
import UwU_.GameSense.Main;
import UwU_.GameSense.editor.Drag;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventPlayer;
import UwU_.GameSense.helpers.render.Translate;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.notifications.NotificationType;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@ModuleInfo(name = "StaffAlert", type = Category.Player)
public class StaffAlert extends Module {

    public ArrayList<NetworkPlayerInfo> staff = new ArrayList<>();

    public Translate translate = new Translate(0,0);
    public Drag drag = Main.createDrag(this, "staff", 20, 60);

    @Override
    public void onDisable() {
        super.onDisable();
        staff.clear();
    }
    @EventTarget
    public void onPower(EventPlayer eventPlayer) {
        if (eventPlayer.getAction() == SPacketPlayerListItem.Action.ADD_PLAYER && check(eventPlayer.getPlayerData().getDisplayName().getUnformattedText().toLowerCase())) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (check(mc.player.connection.getPlayerInfo(eventPlayer.getPlayerData().getProfile().getId()).getDisplayName().getUnformattedText().toLowerCase()))
                    Main.notify.call("Staff Alert", eventPlayer.getPlayerData().getDisplayName().getFormattedText() + " joined!", NotificationType.INFO);
            }).start();
        }

        if (eventPlayer.getAction() == SPacketPlayerListItem.Action.REMOVE_PLAYER) {
            for (NetworkPlayerInfo info : staff) {
                if (info.getGameProfile().getId().equals(eventPlayer.getPlayerData().getProfile().getId())) {
                    if (mc.player.connection.getPlayerInfo(eventPlayer.getPlayerData().getProfile().getId()).getGameProfile().getName() == null) {
                        staff.remove(info);
                        Main.notify.call("Staff Alert", eventPlayer.getPlayerData().getDisplayName().getFormattedText() + " leaved!", NotificationType.INFO);
                    }
                    else {
                        Main.notify.call("Staff Alert", eventPlayer.getPlayerData().getDisplayName().getFormattedText() + " spectator!", NotificationType.INFO);
                    }
                    break;
                }
            }
        }

    }


    public boolean check(String name) {
        return name.contains("helper") || name.contains("moder") || name.contains("admin") || name.contains("owner") || name.contains("curator") || name.contains("хелпер") || name.contains("модер") || name.contains("админ") || name.contains("куратор");
    }
}
