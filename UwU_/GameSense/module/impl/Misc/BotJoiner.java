package UwU_.GameSense.module.impl.Misc;

//import com.github.javafaker.Faker;

import org.apache.commons.lang3.RandomStringUtils;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventMotion;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;

@ModuleInfo(name = "BotJoiner", type = Category.Misc)
public class BotJoiner extends Module {

    @EventTarget
    public void onUpdate(EventMotion e) {
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
//                Faker faker = new Faker();
                //BotStarter.run(faker.name().firstName().toLowerCase() + getRandomString(MathHelper.getRandomNumberBetween(2, 5)), true);
            }).start();
        }
    }

    public static String getRandomString(int length) {
        return RandomStringUtils.random(length, "0123456789");
    }

}
