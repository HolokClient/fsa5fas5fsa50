package UwU_.GameSense.settings.options;


import UwU_.GameSense.helpers.animation.Animation;
import UwU_.GameSense.helpers.animation.impl.EaseInOutQuad;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.settings.Setting;

import java.util.function.Supplier;

public class BooleanSetting extends Setting {

private boolean state;
public Animation animation = new EaseInOutQuad(150, 1);


public BooleanSetting(String name, boolean state) {
    this.name = name;
    this.state = state;
}


public boolean get() {
    return state;
}

public void set(boolean state) {
    this.state = state;
}

    public BooleanSetting setHidden(Supplier<Boolean> hidden) {
        this.hidden = hidden;
        return this;
    }
    public BooleanSetting call(Module module) {
        module.addSettings(this);
        return this;
    }

}
