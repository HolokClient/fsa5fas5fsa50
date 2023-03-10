package UwU_.GameSense.module.impl.Player;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import UwU_.GameSense.Main;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.ChatEvent;
import UwU_.GameSense.event.events.impl.EventMotion;
import UwU_.GameSense.helpers.animation.Counter;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.notifications.NotificationType;

@ModuleInfo(name = "BotSolver", type = Category.Player)
public class BotSolver extends Module {

    public Counter c = new Counter();

    @EventTarget
    public void onUpdate(EventMotion e) {
        int i = 0;
        final ContainerChest chest = (ContainerChest) mc.player.openContainer;
        for (ItemStack stack : mc.player.openContainer.getInventory()) {
            i++;
//            if (stack.getDisplayName().toLowerCase().contains("нажми") || stack.getDisplayName().toLowerCase().contains("click") || stack.getDisplayName().toLowerCase().contains("сюда") || stack.getDisplayName().toLowerCase().contains("here")) {
//                if (c.hasReached(400)) {
//                    System.out.println(i);
//                    mc.playerController.windowClick(mc.player.openContainer.windowId, i - 1, 0, ClickType.PICKUP, mc.player);
//                    c.reset();
//                }
//            }

            if (stack.getDisplayName().length() < 3) {
                if (c.hasReached(400)) {
                    System.out.println(i);
                    mc.playerController.windowClick(mc.player.openContainer.windowId, i - 1, 0, ClickType.PICKUP, mc.player);
                    c.reset();
                }
            }

            if (chest.getLowerChestInventory().getDisplayName().getUnformattedText().toLowerCase().contains("зелье")) {
                if (PotionUtils.getColor(stack) == 255) {
                    if (c.hasReached(400)) {
                        System.out.println(i);
                        mc.playerController.windowClick(mc.player.openContainer.windowId, i - 1, 0, ClickType.PICKUP, mc.player);
                        c.reset();
                    }
                }
            }
        }
    }

    @EventTarget
    public void solve(ChatEvent e) {
        getInfinitySolve(mc.player, e.message);
    }

    public void getInfinitySolve(EntityPlayerSP bot, String message) {
        String[] solve1 = new String[]{
                "①", "②", "③", "④", "⑤", "⑥", "⑦", "⑧", "⑨", "⑩", "⑪", "⑫", "⑬", "⑭", "⑮", "⑯", "⑰", "⑱", "⑲", "⑳"
        };


        String[] solve2 = new String[]{
                "⑴", "⑵", "⑶", "⑷", "⑸", "⑹", "⑺", "⑻", "⑼", "⑽", "⑾", "⑿", "⒀", "⒁", "⒂", "⒃", "⒄", "⒅", "⒆", "⒇"
        };
        String[] solve3 = new String[]{
                "⒈", "⒉", "⒊", "⒋", "⒌", "⒍", "⒎", "⒏", "⒐", "⒑", "⒒", "⒓", "⒔", "⒕", "⒖", "⒗", "⒘", "⒙", "⒚", "⒛"
        };
        String[] solve4 = new String[]{
                "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"
        };


        String[] solve5 = new String[]{
                "⓫", "⓬", "⓭", "⓮", "⓯", "⓰", "⓱", "⓲", "⓳", "⓴"
        };
        if (message.contains("Решите пример: ")) {

            String text = message.replaceAll("Решите пример: ", "");

            text = text.replaceAll("\\.", "");
            text = text.replaceAll("!", "");
            text = text.replaceAll(" ", "");
            text = text.replaceAll("_", "");
            text = text.replaceAll("-", "");
            text = text.replaceAll("_", "");
            String[] split1 = text.split("\\+");
            int solve = 0;
            for (String s : solve1) {
                solve++;
                if (split1[1].equalsIgnoreCase(s)) {
                    text = text.replaceAll(s, String.valueOf(solve));
                }
            }
            solve = 0;
            for (String s : solve2) {
                solve++;
                if (split1[1].equalsIgnoreCase(s)) {
                    text = text.replaceAll(s, String.valueOf(solve));
                }
            }
            solve = 0;
            for (String s : solve3) {
                solve++;
                if (split1[1].equalsIgnoreCase(s)) {
                    text = text.replaceAll(s, String.valueOf(solve));
                }
            }
            solve = 0;
            for (String s : solve4) {
                solve++;
                if (split1[1].equalsIgnoreCase(s)) {
                    text = text.replaceAll(s, String.valueOf(solve));
                }

            }
            solve = 10;
            for (String s : solve5) {
                solve++;
                if (split1[1].equalsIgnoreCase(s)) {
                    text = text.replaceAll(s, String.valueOf(solve));
                }
            }
            Main.notify.call("Solved", bot.getName() + " " + text , NotificationType.INFO);
            String[] split = text.split("\\+");
            System.out.println(message);
            bot.sendChatMessage(String.valueOf(Integer.parseInt(split[0]) + Integer.parseInt(split[1])));
        }
    }

}
