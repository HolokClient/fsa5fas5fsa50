package UwU_.GameSense.module.impl.Player;

import net.minecraft.world.EnumSkyBlock;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventLight;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;

@ModuleInfo(name = "AntiLagMachine", type = Category.Player)
public class AntiLagMachine extends Module {

    @EventTarget
    public void onWorldLight(EventLight event) {
            if (event.getEnumSkyBlock() == EnumSkyBlock.SKY) {
                event.cancel();
            }
    }

}
