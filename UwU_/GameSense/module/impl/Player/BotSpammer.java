package UwU_.GameSense.module.impl.Player;

import ru.salam4ik.bot.bot.Bot;
import UwU_.GameSense.command.impl.BotCommand;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventUpdate;
import UwU_.GameSense.helpers.animation.Counter;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.settings.options.SliderSetting;

@ModuleInfo(name = "BotSpammer", type = Category.Player)
public class BotSpammer extends Module {

    public SliderSetting delay = new SliderSetting("Delay", 5000F, 0F, 30000F, 500F).call(this);

    public Counter c = new Counter();

    @EventTarget
    public void onUpdate(EventUpdate e) {
        if (c.hasReached(delay.get())) {
            for (Bot bot : Bot.bots) {
               bot.getBot().sendChatMessage(BotCommand.text);
            }
            c.reset();
        }
    }

}
