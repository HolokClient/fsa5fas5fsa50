package UwU_.GameSense.module.impl.Player;

import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventUpdate;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.settings.options.ListSetting;

@ModuleInfo(name = "NoDelay", type = Category.Player)
public class NoDelay extends Module {
    public ListSetting delay = new ListSetting("NoDelay", "Jump", "LeftClick", "Block", "RightClick").call(this);


    @EventTarget
    public void onMotion(EventUpdate e) {
        if (delay.selected.contains("Jump")) {
            mc.player.jumpTicks = 0;
        }
        if (delay.selected.contains("LeftClick")) {
            mc.leftClickCounter = 0;
        }
        if (delay.selected.contains("Block")) {
            mc.playerController.blockHitDelay = 0;
        }
        if (delay.selected.contains("RightClick")) {
            mc.rightClickDelayTimer = 0;
        }
    }
}