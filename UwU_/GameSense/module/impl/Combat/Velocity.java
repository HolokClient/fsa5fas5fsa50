package UwU_.GameSense.module.impl.Combat;

import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventPacket;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;

@ModuleInfo(name = "Velocity", type = Category.Combat)
public class Velocity extends Module {

    @EventTarget
    public void onReceive(EventPacket e) {
        if (e.getPacket() instanceof SPacketEntityVelocity) {
            SPacketEntityVelocity packet = (SPacketEntityVelocity) e.getPacket();
            if (packet.getEntityID() == mc.player.getEntityId()) {
                e.cancel();
            }
        }
        if (e.getPacket() instanceof SPacketExplosion) {
            e.cancel();
        }
    }

}
