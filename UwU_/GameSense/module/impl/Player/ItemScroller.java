package UwU_.GameSense.module.impl.Player;

import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.settings.options.SliderSetting;

@ModuleInfo(name = "ItemScroller", type = Category.Player)
public class ItemScroller extends Module {
    public SliderSetting delay = new SliderSetting("Delay", 10F, 0F, 500F, 1F).call(this);


}
