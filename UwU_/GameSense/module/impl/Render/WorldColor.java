package UwU_.GameSense.module.impl.Render;

import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.settings.options.ColorSetting;

import java.awt.*;

@ModuleInfo(name = "WorldColor", type = Category.Render)
public class WorldColor extends Module {

    public ColorSetting color = new ColorSetting("Color", new Color(255, 255, 255).getRGB()).call(this);

}
