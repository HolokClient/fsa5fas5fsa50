package UwU_.GameSense.module.impl.Player;

import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventUpdate;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;

@ModuleInfo(name = "AutoRespawn", type = Category.Player)
public class AutoRespawn extends Module {

    @EventTarget
    public void update(EventUpdate e){
        if (mc.player != null && mc.world != null) {
            if (mc.player.deathTime > 0) {
                mc.player.respawnPlayer();
            }
        }
    }
}
