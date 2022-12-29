package UwU_.GameSense.settings.options;

import UwU_.GameSense.module.Module;
import UwU_.GameSense.settings.Setting;

import java.util.function.Supplier;

public class ColorSetting extends Setting {

    public int color = -1;
    public boolean dragging;

    public boolean slid;
    public boolean ifSlidingAlpha;
    public float alphaSlider;


    public ColorSetting(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public int get() {
        return color;
    }

    public ColorSetting setHidden(Supplier<Boolean> hidden) {
        this.hidden = hidden;
        return this;
    }

    public ColorSetting call(Module module) {
        module.addSettings(this);
        return this;
    }


}
