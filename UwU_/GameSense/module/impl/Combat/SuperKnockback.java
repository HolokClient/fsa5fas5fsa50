package UwU_.GameSense.module.impl.Combat;

import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketUseEntity;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventPacket;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;

@ModuleInfo(name = "SuperKnockBack", type = Category.Combat)
public class SuperKnockback extends Module {

    @EventTarget
    public void onSendPacket(EventPacket event) {
            if (event.getPacket() instanceof CPacketUseEntity) {
                CPacketUseEntity packet = (CPacketUseEntity) event.getPacket();
                if (packet.getAction() == CPacketUseEntity.Action.ATTACK) {
                    mc.player.setSprinting(false);
                    mc.player.connection.sendPacket(
                            new CPacketEntityAction(mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
                    mc.player.setSprinting(true);
                    mc.player.connection.sendPacket(
                            new CPacketEntityAction(mc.player, CPacketEntityAction.Action.START_SPRINTING));
                }
            }
    }
}
