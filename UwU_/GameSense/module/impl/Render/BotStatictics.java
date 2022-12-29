package UwU_.GameSense.module.impl.Render;

import ru.salam4ik.bot.bot.Bot;
import ru.salam4ik.bot.bot.network.BotPlayClient;
import UwU_.GameSense.Main;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventDisplay;
import UwU_.GameSense.helpers.animation.Counter;
import UwU_.GameSense.helpers.font.Fonts;
import UwU_.GameSense.helpers.math.MathHelper;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;

import java.util.List;
import java.util.stream.Collectors;

@ModuleInfo(name = "BotStatictics", type = Category.Render)
public class BotStatictics extends Module {


    public Counter timer = new Counter();

    public double proxy;
    public double balance;

    @EventTarget
    public void onRender(EventDisplay e) {


        proxy = Main.proxy.proxies.size();
        balance = Main.balance;
        if (timer.hasReached(30000)) {
            balance();
        }

        List<String> botsname = Bot.bots.stream().map(n -> n.getBot().getName()).collect(Collectors.toList());
        int count = (int) mc.getConnection().getPlayerInfoMap().stream().filter(name -> botsname.contains(name.getGameProfile().getName())).count();
        Fonts.pix.drawCenteredStringWithOutline("[ GameSense§b.lua§f ]", e.sr.getScaledWidth() / 2f, e.sr.getScaledHeight() - 70, -1);
        Fonts.pix.drawCenteredStringWithOutline("Server brand: §b" + mc.player.getServerBrand() + "§f  |  " + "User name: §b" + mc.getSession().getUsername(), e.sr.getScaledWidth() / 2f, e.sr.getScaledHeight() - 65, -1);
        Fonts.pix.drawCenteredStringWithOutline("Bots total: §b" + Bot.bots.size() + "§f  |  " + "On this server: §b" + count + "§f  |  " + "Proxy: §b" + (int) proxy, e.sr.getScaledWidth() / 2f, e.sr.getScaledHeight() - 60, -1);
        Fonts.pix.drawCenteredStringWithOutline("Balance: §b" + (int) balance + "§f  |  " + "Last captcha: §b" + BotPlayClient.lastSolved, e.sr.getScaledWidth() / 2f, e.sr.getScaledHeight() - 55, -1);

        List<Long> longs = Bot.bots.stream().map(Bot::getTime).collect(Collectors.toList());

        long average = longs.stream().mapToLong(Long::longValue).sum() / longs.size();
        Fonts.pix.drawCenteredStringWithOutline("Average time: §b" + MathHelper.format(average), e.sr.getScaledWidth() / 2f, e.sr.getScaledHeight() - 50, -1);

    }

    public void balance() {
        new Thread(() -> {
//            Document site = null;
//            try {
//                site = Jsoup.connect("http://api.captcha.guru/res.php?action=getbalance&key=" + Main.apiKey).get();
//                Main.balance = Double.parseDouble(site.text());
//            } catch (IOException | NumberFormatException ignored) {
//
//            }
//
        }).start();
    }

}
