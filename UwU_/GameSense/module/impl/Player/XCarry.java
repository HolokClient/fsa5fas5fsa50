package UwU_.GameSense.module.impl.Player;

import net.minecraft.network.play.client.CPacketCloseWindow;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventPacket;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;

@ModuleInfo(name = "XCarry", type = Category.Player)
public class XCarry extends Module {

    @EventTarget
    public void onPacket(EventPacket e) {
        if (e.getPacket() instanceof CPacketCloseWindow) {
            e.cancel();
        }
    }

}
