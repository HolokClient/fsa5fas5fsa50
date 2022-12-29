package UwU_.GameSense.module.impl.Player;

import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.settings.options.BooleanSetting;

@ModuleInfo(name = "Baritone", type = Category.Player)
public class Baritone extends Module {
    public BooleanSetting antirg = new BooleanSetting("AntiRG", true).call(this);
}
