package UwU_.GameSense.module.impl.Render;

import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.settings.options.ColorSetting;

import java.awt.*;

@ModuleInfo(name = "FogColor", type = Category.Render)
public class FogColor extends Module {

    public ColorSetting fogColor = new ColorSetting("Color", new Color(255, 255, 255).getRGB()).call(this);

}
