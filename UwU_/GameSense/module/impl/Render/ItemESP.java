package UwU_.GameSense.module.impl.Render;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventRender;
import UwU_.GameSense.helpers.render.RenderUtil;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;

import java.awt.*;

@ModuleInfo(name = "ItemESP", type = Category.Render)
public class ItemESP extends Module {

    @EventTarget
    public void onRender(EventRender e) {
        for (EntityItem item : mc.world.getEntities(EntityItem.class, Entity::isEntityAlive)) {
            RenderUtil.renderItem(item, new Color(255, 255, 255, 100), e.pt);
        }
    }

}
