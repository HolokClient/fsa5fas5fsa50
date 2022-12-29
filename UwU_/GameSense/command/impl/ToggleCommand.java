package UwU_.GameSense.command.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import UwU_.GameSense.Main;
import UwU_.GameSense.command.Command;
import UwU_.GameSense.command.CommandInfo;
import UwU_.GameSense.helpers.ChatUtil;
import UwU_.GameSense.module.Module;

import java.util.ArrayList;
import java.util.List;

@CommandInfo(name = "toggle")
public class ToggleCommand extends Command {

    @Override
    public void execute(String[] args) {
        super.execute(args);
        Main.m.getModule(args[1]).toggle();
    }

    @Override
    public void onError() {
        super.onError();
        ChatUtil.print(ChatFormatting.RED + "Ошибка в команде -> toggle <имя модуля>");
    }

    @Override
    public List<String> getSuggestions(String[] args) {
        List<String> modules = new ArrayList<>();
        for (Module m : Main.m.m) {
            modules.add(m.name);
        }
        return modules;
    }
}
