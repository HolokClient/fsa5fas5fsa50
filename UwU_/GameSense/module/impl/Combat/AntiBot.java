package UwU_.GameSense.module.impl.Combat;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventMotion;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.UUID;
@ModuleInfo(name = "AntiBot", type = Category.Combat)
public class AntiBot extends Module {

    public static ArrayList<Entity> isBot = new ArrayList<>();

    @EventTarget
    public void onMotion(EventMotion e) {
        for (Entity entity : mc.world.loadedEntityList) {

            if (!entity.getUniqueID().equals(UUID.nameUUIDFromBytes(("OfflinePlayer:" + entity.getName()).getBytes(StandardCharsets.UTF_8))) && entity instanceof EntityOtherPlayerMP) {
                if (!isBot.contains(entity)) {
                    isBot.add(entity);
                }
            }
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        isBot.clear();
    }

}
