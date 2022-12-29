package UwU_.GameSense.module.impl.Movement;

import net.minecraft.block.BlockWeb;
import net.minecraft.util.math.BlockPos;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventMotion;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.settings.options.SliderSetting;

@ModuleInfo(name = "WebLeave", type = Category.Movement)
public class WebLeave extends Module {

    public SliderSetting motion = new SliderSetting("Motion", 1, 0, 10, 0.1f).call(this);

    @EventTarget
    public void onMotion(EventMotion e) {
        if (mc.player.isInWeb) {
            mc.player.motionY = 1;
        }
        if (mc.world.getBlockState(new BlockPos(mc.player.posX, mc.player.posY - 0.1, mc.player.posZ)).getBlock() instanceof BlockWeb) {
            mc.player.motionY = motion.get();
        }
    }

}
