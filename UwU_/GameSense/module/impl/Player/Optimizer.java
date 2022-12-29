package UwU_.GameSense.module.impl.Player;

import net.minecraft.world.EnumSkyBlock;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventLight;
import UwU_.GameSense.event.events.impl.EventUpdate;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.settings.options.BooleanSetting;
@ModuleInfo(name = "Optimizer", type = Category.Player)
public class Optimizer extends Module {

    public BooleanSetting light = new BooleanSetting("Light", true).call(this);
    public BooleanSetting entities = new BooleanSetting("Entities", true).call(this);

    @EventTarget
    public void onWorldLight(EventLight event) {
        if (light.get()) {
            if (event.getEnumSkyBlock() == EnumSkyBlock.SKY) {
                event.cancel();
            }
            if (event.getEnumSkyBlock() == EnumSkyBlock.BLOCK) {
                event.cancel();
            }
        }
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {

    }

}
