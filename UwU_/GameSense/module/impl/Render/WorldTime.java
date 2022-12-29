package UwU_.GameSense.module.impl.Render;

import net.minecraft.network.play.server.SPacketTimeUpdate;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventPacket;
import UwU_.GameSense.event.events.impl.EventUpdate;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.settings.options.SliderSetting;

@ModuleInfo(name = "WorldTime", type = Category.Render)
public class WorldTime extends Module {

    public SliderSetting time = new SliderSetting("Time", 1000, 0, 24000, 100).call(this);

    @EventTarget
    public void onUpdate(EventUpdate e) {
        setSuffix(String.valueOf(time.get()));
        mc.world.setWorldTime((long) time.get());
    }

    @EventTarget
    public void onPacket(EventPacket e) {
        if (e.getPacket() instanceof SPacketTimeUpdate) {
            e.cancel();;
        }
    }

}
