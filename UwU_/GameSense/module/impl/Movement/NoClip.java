package UwU_.GameSense.module.impl.Movement;

import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventMotion;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.settings.options.ModeSetting;


@ModuleInfo(name = "NoClip", type = Category.Movement)
public class NoClip extends Module {

    public ModeSetting mode = new ModeSetting("Mode", "SunRise", "SunRise");

    @EventTarget
    public void onMotion(EventMotion e) {
        if (mc.player.collidedHorizontally) {
            mc.player.onGround = true;
            if (!mc.gameSettings.keyBindSneak.isKeyDown())
                mc.player.motionY = 0;
        }
    }

}
