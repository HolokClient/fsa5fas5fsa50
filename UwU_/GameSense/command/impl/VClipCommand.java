package UwU_.GameSense.command.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import UwU_.GameSense.command.Command;
import UwU_.GameSense.command.CommandInfo;
import UwU_.GameSense.helpers.ChatUtil;

@CommandInfo(name = "vclip")
public class VClipCommand extends Command {

    @Override
    public void execute(String[] args) {
        super.execute(args);
        mc.player.setPosition(mc.player.posX, mc.player.posY + Double.parseDouble(args[1]), mc.player.posZ);
        ChatUtil.print("Clipped to " + args[1] + " blocks.");
    }

    @Override
    public void onError() {
        super.onError();
            ChatUtil.print(ChatFormatting.RED + "Ошибка в команде -> vclip <значение>");
    }
}
