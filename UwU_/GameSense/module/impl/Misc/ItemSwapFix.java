package UwU_.GameSense.module.impl.Misc;

import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.server.SPacketHeldItemChange;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventPacket;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;

@ModuleInfo(name = "ItemSwapFix", type = Category.Misc)
public class ItemSwapFix extends Module {

    @EventTarget
    public void onPacket(EventPacket e) {
        if (e.getPacket() instanceof SPacketHeldItemChange) {
            e.cancel();
            mc.player.connection.sendPacket(new CPacketHeldItemChange(mc.player.inventory.currentItem));
        }
    }

}
