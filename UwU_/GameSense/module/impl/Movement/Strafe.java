package UwU_.GameSense.module.impl.Movement;

import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventMotion;
import UwU_.GameSense.helpers.MovementUtil;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;

@ModuleInfo(name = "Strafe", type = Category.Movement)
public class Strafe extends Module {

    public double boost;
    @EventTarget
    public void onUpdate(EventMotion e) {
        if (!mc.player.onGround && MovementUtil.getPlayerMotion() <= 0.22f) {
            MovementUtil.setSpeed(mc.player.isSneaking() ? (float) MovementUtil.getPlayerMotion() : 0.22f);
        }

        if (!mc.player.onGround && mc.player.motionY == -0.4448259643949201) {
            MovementUtil.setSpeed((float) MovementUtil.getPlayerMotion());
        }



    }


}
