package UwU_.GameSense.module.impl.Player;

import net.minecraft.network.play.client.CPacketPlayer;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventUpdate;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.settings.options.ModeSetting;

@ModuleInfo(name = "NoFall", type = Category.Player)
public class NoFall extends Module {

    public ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Vanilla").call(this);

    @EventTarget
    public void onUpdate(EventUpdate e) {
        if (mode.get().equals("Vanilla")) {
            if (mc.player.fallDistance >= 3) {
                mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY, mc.player.posZ, true));
                mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY, mc.player.posZ, false));
                mc.player.fallDistance = 0;
            }
        }

    }

}
