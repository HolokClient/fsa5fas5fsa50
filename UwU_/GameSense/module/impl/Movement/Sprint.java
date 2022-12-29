package UwU_.GameSense.module.impl.Movement;

import net.minecraft.network.play.client.CPacketEntityAction;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventMotion;
import UwU_.GameSense.event.events.impl.EventPacket;
import UwU_.GameSense.helpers.MovementUtil;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.settings.options.BooleanSetting;

@ModuleInfo(name = "Sprint", type = Category.Movement)
public class Sprint extends Module {

    public BooleanSetting allmode = new BooleanSetting("All - Direction", false).call(this);

@EventTarget
public void onUpdate(EventMotion e) {
    if (allmode.get())
        mc.player.setSprinting(MovementUtil.isMoving());
    else
        mc.player.setSprinting(mc.player.moveForward > 0);
}

@EventTarget
public void onUpdate(EventPacket e) {
    if (e.getPacket() instanceof CPacketEntityAction) {
        CPacketEntityAction packet = (CPacketEntityAction) e.getPacket();
        if (packet.getAction() == CPacketEntityAction.Action.START_SPRINTING) {
            e.cancel();
        }
        if (packet.getAction() == CPacketEntityAction.Action.STOP_SPRINTING) {
            e.cancel();
        }
       
    }
}

}
