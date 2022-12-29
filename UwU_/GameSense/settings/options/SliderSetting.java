package UwU_.GameSense.settings.options;

import UwU_.GameSense.module.Module;
import UwU_.GameSense.settings.Setting;

import java.util.function.Supplier;

public class SliderSetting extends Setting {
public float current, minimum, maximum, increment;
public float sliderWidth;
public boolean sliding;

public SliderSetting(String name, float current, float minimum, float maximum, float increment) {
    this.name = name;
    this.minimum = minimum;
    this.current = current;
    this.maximum = maximum;
    this.increment = increment;
}

public float get() {
    return current;
}

public SliderSetting setHidden(Supplier<Boolean> hidden) {
    this.hidden = hidden;
    return this;
}
public SliderSetting call(Module module) {
    module.addSettings(this);
    return this;
}
}
