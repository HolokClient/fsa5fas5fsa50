package UwU_.GameSense.module.impl.Player;

import net.minecraft.network.play.server.SPacketPlayerPosLook;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventPacket;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;

@ModuleInfo(name = "NoRotationSet", type = Category.Player)
public class NoRotationSet extends Module {

    @EventTarget
    public void onRotate(EventPacket e) {
        if (e.getPacket() instanceof SPacketPlayerPosLook) {
            SPacketPlayerPosLook packet = (SPacketPlayerPosLook) e.getPacket();
            packet.yaw = mc.player.rotationYaw;
            packet.pitch = mc.player.rotationPitch;
        }
    }

}
