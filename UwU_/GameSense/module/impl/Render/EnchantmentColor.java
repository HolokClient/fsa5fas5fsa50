package UwU_.GameSense.module.impl.Render;

import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.settings.options.ColorSetting;

import java.awt.*;
@ModuleInfo(name = "EnchantmentColor", type = Category.Render)
public class EnchantmentColor extends Module {
    public ColorSetting enchantColor = new ColorSetting("Color", new Color(120, 210, 210).getRGB()).call(this);
}
