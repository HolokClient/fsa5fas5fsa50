package UwU_.GameSense.module.impl.Misc;

import net.minecraft.network.play.server.SPacketPlayerListItem;
import net.minecraft.util.text.TextComponentString;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventPlayer;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;

@ModuleInfo(name = "PlayerLogger", type = Category.Misc)
public class PlayerLogger extends Module {

    @EventTarget
    public void onPacket(EventPlayer e) {
        if (e.getAction() == SPacketPlayerListItem.Action.UPDATE_GAME_MODE) {
            mc.ingameGUI.getChatGUI().printChatMessage(new TextComponentString(("&8[&e/&8] &r" + mc.getConnection().getPlayerInfo(e.getPlayerData().getProfile().getId()).getDisplayName().getFormattedText() + "&8 → &e" + e.getPlayerData().gamemode.getName().toUpperCase()).replaceAll("&", "§")));
        }
    }

}
