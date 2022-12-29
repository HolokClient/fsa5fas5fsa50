package UwU_.GameSense.module.impl.Render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumHandSide;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventRenderHand;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.settings.options.SliderSetting;

@ModuleInfo(name = "ViewModel", type = Category.Render)
public class ViewModel extends Module {

    public SliderSetting rightX = new SliderSetting("Right X", 0, -180, 180, 1);
    public SliderSetting rightY = new SliderSetting("Right Y", 0, -180, 180, 1);
    public SliderSetting rightZ = new SliderSetting("Right Z", 0, -180, 180, 1);
    public SliderSetting leftX = new SliderSetting("Left X", 0, -180, 180, 1);
    public SliderSetting leftY = new SliderSetting("Left Y", 0, -180, 180, 1);
    public SliderSetting leftZ = new SliderSetting("Left Z", 0, -180, 180, 1);

    public ViewModel() {
        addSettings(rightX, rightY, rightZ, leftX, leftY, leftZ);
    }

    @EventTarget
    public void onRender(EventRenderHand e) {
        if (e.e == EnumHandSide.RIGHT) {
            GlStateManager.translate(rightX.get() / 180, rightY.get() / 180, rightZ.get() / 180);
        }
        if (e.e == EnumHandSide.LEFT) {
            GlStateManager.translate(leftX.get() / 180, leftY.get() / 180, leftZ.get() / 180);
        }
    }

}
