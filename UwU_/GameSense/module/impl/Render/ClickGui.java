package UwU_.GameSense.module.impl.Render;

import org.lwjgl.input.Keyboard;
import UwU_.GameSense.Main;
import UwU_.GameSense.helpers.file.ClickGuiSave;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.settings.options.ColorSetting;
@ModuleInfo(name = "ClickGui", type = Category.Render)
public class ClickGui extends Module {

    public ColorSetting color = new ColorSetting("Click Gui Color", -1);

    public ClickGui() {
        bind = Keyboard.KEY_RSHIFT;
        addSettings(color);
    }

@Override
public void onEnable() {
    super.onEnable();
    mc.displayGuiScreen(Main.s);
    ClickGuiSave.save();
    toggle();
}

public static int getColor() {
        return ((ClickGui) Main.m.getModule(ClickGui.class)).color.get();
}

}
//govnocode

