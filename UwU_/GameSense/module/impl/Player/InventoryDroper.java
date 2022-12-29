package UwU_.GameSense.module.impl.Player;

import net.minecraft.inventory.ClickType;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;

@ModuleInfo(name = "InventoryDroper", type = Category.Player)
public class InventoryDroper extends Module {

    @Override
    public void onEnable() {
        super.onEnable();
        for (int o = 0; o < 46; ++o) {
            mc.playerController.windowClick(mc.player.inventoryContainer.windowId, o, 1, ClickType.THROW, mc.player);
        }
        toggle();
    }
}
