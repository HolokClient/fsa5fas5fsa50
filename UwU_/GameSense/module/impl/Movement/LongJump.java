package UwU_.GameSense.module.impl.Movement;

import org.lwjgl.input.Keyboard;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventMotion;
import UwU_.GameSense.helpers.MovementUtil;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.settings.options.ModeSetting;

@ModuleInfo(name = "LongJump", type = Category.Movement)
public class LongJump extends Module {

    public ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Vanilla", "Air Jump").call(this);

    @EventTarget
    public void onUpdate(EventMotion e) {
        if (mode.is("Air Jump")) {
            if ((!mc.world.getCollisionBoxes(mc.player, mc.player.getEntityBoundingBox().offset(0, -1, 0).expand(-1f, 0, -1f).expand(1, 0, 1)).isEmpty())) {
                if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                    mc.player.jumpTicks = 0;
                    mc.player.fallDistance = 0;
                    e.setOnGround(true);
                    mc.player.onGround = true;
                }
            }
        }
        else if (mode.is("Vanilla")) {
            if (!mc.player.onGround) {
                MovementUtil.setSpeed((float) (MovementUtil.getPlayerMotion() + 0.03f));
            }
        }
    }

}
