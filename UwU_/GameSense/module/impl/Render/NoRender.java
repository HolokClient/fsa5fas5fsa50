package UwU_.GameSense.module.impl.Render;

import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.settings.options.ListSetting;
@ModuleInfo(name = "NoRender", type = Category.Render)
public class NoRender extends Module {

    public ListSetting listSetting = new ListSetting("Modes", "Fire", "Block").call(this);

}
