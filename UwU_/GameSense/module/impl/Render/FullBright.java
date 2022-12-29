package UwU_.GameSense.module.impl.Render;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventMotion;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;

@ModuleInfo(name = "FullBright", type = Category.Render)
public class FullBright extends Module {

    @EventTarget
    public void onUpdate(EventMotion e) {
        mc.player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 20 * (780 + 37)));
    }

    @Override
    public void onDisable() {
        super.onDisable();
        if (mc.player != null)
            mc.player.removeActivePotionEffect(MobEffects.NIGHT_VISION);
    }


}
